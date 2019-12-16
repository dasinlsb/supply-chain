import React, {createContext, useContext, useReducer} from "react";
import {OrgType} from "../types";

export interface AuthInfoType {
  account: string;
  orgType: OrgType;
  orgId: string;
}

export interface AuthState {
  isAuthenticated: boolean;
  info: AuthInfoType;
}

const initialState: AuthState = {
  isAuthenticated: false,
  info: {
    account: '',
    orgType: undefined,
    orgId: '',
  }
};

export type ReducerAction =
  | { type: 'login'; data: AuthInfoType; }
  | { type: 'logout'; }

const reducer = (state: AuthState, action: ReducerAction) => {
  switch (action.type) {
    case "login":
      return {
        ...state,
        info: action.data,
        isAuthenticated: true,
      };
    case "logout":
      return initialState;
    default:
      return state;
  }
};

interface IAuthContext {
  state: AuthState;
  login: (data: AuthInfoType) => void;
  logout: () => void;
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
  const [state, dispatch] = useReducer(reducer, initialState);

  const login= (data: AuthInfoType) => {
    localStorage.setItem('account', data.account);
    dispatch({ type: 'login', data, });
  };

  const logout = () => {
    localStorage.removeItem('account');
    dispatch({ type: 'logout', });
  };

  return ({
    state,
    login,
    logout,
  })
};
