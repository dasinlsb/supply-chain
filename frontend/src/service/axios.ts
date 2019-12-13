import axios from 'axios';


axios.interceptors.request.use(
  config => config,
  error => {
    Promise.reject(error);
  },
);


axios.interceptors.response.use(
  (response: any) => {
      return response;
    },
  error => Promise.reject(error),
);


export default axios;
