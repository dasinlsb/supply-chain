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

export default function Login() {
  const classes = useStyles();
  const [account, setAccount] = useState('');
  const auth = useAuth();

  const login = (account: string) => {
    fetch('http://localhost:8080/api/v1/login?' + qs.stringify({
      account,
      seed: Math.random(),
    }), {
      method: 'GET',
      mode: 'cors',
    }).then(res => res.json())
      .then((data: { info: AuthInfoType, }) => auth.login(data.info))
      .catch(err => console.log('Login failed:', err))
  };

  useEffect(() => {
    let account = localStorage.getItem('account');
    if (account) {
      login(account);
    }
  }, [login]);

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
          value={account}
          onChange={(e) => setAccount(e.target.value)}
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
        <Button
          type="submit"
          fullWidth
          variant="contained"
          color="primary"
          className={classes.submit}
          onClick={() => login(account)}
        >
          登录
        </Button>
        <Box mt={5}>
          <Copyright />
        </Box>
      </div>
    </div>
  );
}
