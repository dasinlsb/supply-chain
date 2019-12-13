const api_server_url = 'http://localhost:8080/api';
const auth_server_url = api_server_url + '/auth';

type RequestInitMode =  'cors' | 'no-cors'

const request_init: { mode: RequestInitMode }= {
  mode: "cors",
};

export default {
  api_server_url,
  auth_server_url,
  request_init,
}