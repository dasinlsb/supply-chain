import axios from './axios'
import {RegisterInfoType} from '../components/Dashboard/RegisterPage';
import config from "../config";
import qs from "qs";

/**
 * RegisterPage an organization
 * POST
 * /users
 */
function register(data: RegisterInfoType) {
  return axios.post(config.api_server_url + '/users')
}

/**
 * Login with `usrname` and `password`
 * GET
 * /login
 */
function login(data: {username: string; password: string; }) {
  return axios.post(config.api_server_url + '/login', data)
}

function logout() {
  localStorage.removeItem('token')
}


/**
 * Authenticate with token in localStorage
 * GET
 * /users/me
 */
function authWithToken<T={orgName: string; orgType: string; username: string; token: string}>(): Promise<T> {
  let token = localStorage.getItem('token');
  if (!token) {
    return new Promise<T>((resolve, reject) => reject());
  }
  return axios.get(`${config.api_server_url}/users/me?${qs.stringify({token: token})}`);
}

function refreshToken() {

}

export default {
  login,
  logout,
  authWithToken,
}
