import makeStyles from "@material-ui/core/styles/makeStyles";
import Avatar from "@material-ui/core/Avatar";
import React, {useEffect, useState} from "react";
import Typography from "@material-ui/core/Typography";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Box from "@material-ui/core/Box";
import Copyright from "../Copyright";
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import {AuthInfoType, useAuth} from "../AuthProvider";
import qs from "qs";
import config from "../../config";
import Deploy from "./Deploy";
import Dropzone from "react-dropzone-uploader";
import 'react-dropzone-uploader/dist/styles.css';

const useStyles = makeStyles(theme => ({
  root: {
    height: '100vh',
  },
  paper: {
    margin: theme.spacing(8, 4),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

interface EntryState {
  openDeploy: boolean;
  account: string;
}

const initialState: EntryState = {
  openDeploy: false,
  account: '',
};

export default function Login() {
  const classes = useStyles();
  const [state, setState] = useState(initialState);
  const auth = useAuth();

  const login = (account: string) => {
    fetch(config.api_server_url + '/login?' + qs.stringify({account}), {
      method: 'GET',
      mode: 'cors',
    }).then(res => res.json())
    .then((data: { info: AuthInfoType, isAdmin: boolean, }) => {
      if (!data.info || !data.info.account) {
        throw new Error("")
      }
      localStorage.setItem('account', account);
      auth.login(data);
    })
    .catch(err => {
      localStorage.removeItem('account');
      console.log('Login failed:', err)
    })
  };

  useEffect(() => {
    let account = localStorage.getItem('account');
    if (account) {
      console.log('found existing account:', account);
      login(account);
    }
  }, []);

  return (
    <div className={classes.paper}>
      <Avatar className={classes.avatar}>
        <LockOutlinedIcon />
      </Avatar>
      <Typography component="h1" variant="h5">
        登录
      </Typography>
      <div className={classes.form}>
        <TextField
          value={state.account}
          onChange={(e) => setState({...state, account: e.target.value})}
          variant="outlined"
          margin="normal"
          required
          fullWidth
          id="account"
          label="账户地址"
          name="account"
          autoComplete="account"
          autoFocus
        />
        {/*<Dropzone*/}
        {/*  getUploadParams={() => ({*/}
        {/*    url: config.api_server_url + '/upload?' + qs.stringify({type: 'pem',})*/}
        {/*  })}*/}
        {/*  onSubmit={(files, allFiles) => allFiles.forEach(f => f.remove())}*/}
        {/*  inputContent={(file, extra) => (extra.reject ? '只允许上传以.pem后缀的文件' : '请点击或拖拽上传pem私钥文件')}*/}
        {/*  accept=".pem"*/}
        {/*/>*/}
        <Button
          type="submit"
          fullWidth
          variant="contained"
          color="primary"
          className={classes.submit}
          onClick={() => login(state.account)}
        >
          登录
        </Button>
        <Deploy/>
        <Box mt={5}>
          <Copyright />
        </Box>
      </div>
    </div>
  );
}
