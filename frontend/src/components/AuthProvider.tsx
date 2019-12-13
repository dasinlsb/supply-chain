import React, {createContext, useContext, useReducer, useState} from "react";
import config from "../config";
import * as querystring from "querystring";
import {OrgType} from "../types";

export interface AuthInfoType {
  orgName: string;
  orgType: OrgType;
  username: string;
}

export interface AuthState {
  isAuthenticated: boolean;
  info: AuthInfoType;
}

const initialState: AuthState = {
  isAuthenticated: false,
  info: {
    orgName: 'unknown organization name',
    orgType: undefined,
    username: 'unknown username',
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

  const login= (data: AuthInfoType) => dispatch({ type: 'login', data, });

  const logout = () => dispatch({ type: 'logout', });

  return ({
    state,
    login,
    logout,
  })
};
