import React, {createContext, useContext, useReducer} from "react";
import {OrgType} from "../types";

export interface AuthInfoType {
  account: string;
  orgType: OrgType;
  orgId: string;
}

export interface AuthState {
  isAdmin: boolean;
  isAuthenticated: boolean;
  info: AuthInfoType;
}

const initialInfo: AuthInfoType = {
  account: '',
  orgType: '',
  orgId: '',
};

const initialState: AuthState = {
  isAdmin: false,
  isAuthenticated: false,
  info: initialInfo,
};

export type ReducerAction =
  | { type: 'login'; data: { info: AuthInfoType; isAdmin: boolean; } }
  | { type: 'logout'; }

const reducer = (state: AuthState, action: ReducerAction) => {
  switch (action.type) {
    case "login":
      return {
        ...state,
        info: action.data.info || initialInfo,
        isAdmin: action.data.isAdmin || false,
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
  login: (data: {info: AuthInfoType, isAdmin: boolean,}) => void;
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

  const login= (data: {info: AuthInfoType, isAdmin: boolean,}) => {
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
