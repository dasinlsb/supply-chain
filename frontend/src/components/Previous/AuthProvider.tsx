import React, {createContext, useContext, useReducer} from "react";
import config from "../../config";
import * as querystring from "querystring";

export interface AuthState {
  isAuthenticated: boolean;
  currentUser: string;
  jwt: string;
}

const anchor = () => {
  console.log('It is trying to reset isAuthenticated');
  return false;
};

const initState = {
  isAuthenticated: anchor(),
  currentUser: '',
  jwt: '',
};

export type ReducerAction =
  | { type: 'login'; username: string; jwt: string; }
  | { type: 'logout'; }

const reducer = (state: AuthState, action: ReducerAction) => {
  switch (action.type) {
    case "login":
      return {
        ...state,
        isAuthenticated: true,
        currentUser: action.username,
        jwt: action.jwt,
      };
    case "logout":
      return initState;
    default:
      return state;
  }
};

interface IAuthContext {
  state: AuthState;
  dispatch: React.Dispatch<ReducerAction>;
  login: ((username: string, password: string)
    => Promise<{ username: string; jwt: string; }>);
  logout: (() => Promise<any>);
  auth: (() => Promise<{ username: string; jwt: string; }>);
  fetchArticleList: ((page: number) => Promise<{title: string; content: string; floors: number}[]>);
}

const AuthContext = createContext<IAuthContext>({} as IAuthContext);

export const AuthProvider = ({ children }: any) => {
  const auth = useProvideAuth();
  return (
    <AuthContext.Provider value={auth}>
      {children}
    </AuthContext.Provider>
  )
};

export const useAuth = () => {
  return useContext(AuthContext)
};

const useProvideAuth = () => {
  const [state, dispatch] = useReducer(reducer, initState);

  function login(username: string, password: string) {
    return fetch(config.api_server_url + '/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password }),
      mode: config.request_init.mode,
    }).then(res => {
      if (!res.ok) throw new Error("login failed: " + res.status);
      return res.json()
    })
    .then((data: { success: boolean; username: string; jwt: string; }) => {
      if (!data.success) throw data;
      localStorage.setItem('jwt', data.jwt);
      localStorage.setItem('username', username);
      return (data);
    })
  }

  function logout() {
    return new Promise((resolve, reject) => {
      localStorage.removeItem('username');
      localStorage.removeItem('jwt');
      resolve();
    });
  }

  function auth() {
    const jwt: string = localStorage.getItem('jwt') || '';
    const username: string = localStorage.getItem('username') || '';
    if (username === '' || jwt === '') {
      return new Promise<{ username: string; jwt: string; }>(
        (resolve, reject) => reject()
      );
    }
    return fetch(config.auth_server_url + '/validate', {
      method: 'GET',
      headers: { 'Authorization': 'Bear ' + jwt },
      mode: config.request_init.mode,
    }).then(res => {
      if (!res.ok) throw new Error("auth failed: " + res.status);
      return res.json();
    })
    .then(() => ({ username, jwt }))
    .catch(err => {
      console.log('JWT is expired: ', err);
      localStorage.removeItem('username');
      localStorage.removeItem('jwt');
      throw err;
    })
  }

  function fetchArticleList(page: number) {
    return fetch(config.auth_server_url + '/article?' + querystring.stringify({ page }), {
      method: 'GET',
      headers: { 'Authorization': 'Bear ' + state.jwt },
      mode: config.request_init.mode,
    }).then((res => {
      if (!res.ok) throw new Error("fetch article data failed: " + res.status);
      return res.json();
    })).then(res => res.data)
  }

  return ({
    state,
    dispatch,
    login,
    logout,
    auth,
    fetchArticleList,
  })
};
