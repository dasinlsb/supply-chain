import React, {useContext, useEffect, useReducer} from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import {makeStyles} from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';

import {Redirect, useHistory, useLocation} from "react-router";
import {useAuth} from "./AuthProvider";
import {Dialog} from "@material-ui/core";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";


function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © dasin'}
      {' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const useStyles = makeStyles(theme => ({
  paper: {
    marginTop: theme.spacing(8),
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

type ReducerAction =
  | {
  type: 'change';
  field: string;
  target: string; }
  | { type: 'login_success'; }
  | { type: 'login_fail_begin'; }
  | { type: 'login_fail_end'; }

interface LoginState {
  username: string;
  password: string;
  willRedirect: boolean;
  willTellFailure: boolean;
}

const initState: LoginState = {
  username: '',
  password: '',
  willRedirect: false,
  willTellFailure: false,
};

function reducer(state: LoginState, action: ReducerAction) {
  switch (action.type) {
    case 'change':
      return {...state, [action.field]: action.target};
    case 'login_success':
      return {...initState, willRedirect: true};
    case 'login_fail_begin':
      return {...initState, willTellFailure: true};
    case "login_fail_end":
      return {...initState, willTellFailure: false};
    default:
      return state;
  }
}

interface LoginProps {
  location: any,
}

const Login: React.FC<LoginProps> = (props: any) => {
  const classes = useStyles();
  const [state, dispatch] = useReducer(reducer, initState);
  const auth = useAuth();
  const history = useHistory();
  const location = useLocation();
  let { from } = location.state || { from: { pathname: '/' }};

  const handleLogin = (username: string, password: string) => {
    auth.login(username, password)
    .then(
      (data: {username: string; jwt: string; }) => {
        auth.dispatch({ ...data, type: 'login', });
        history.replace(from)
      },
    ).catch((err: any) => {
        console.log('Login with username and password failed: ', err);
        dispatch({ type: 'login_fail_begin' })
    })
  };
  useEffect(() => {
    if (!auth.state.isAuthenticated) {
      auth.auth()
      .then((data: { username: string, jwt: string }) => {
        auth.dispatch({...data, type: 'login'});
        history.replace(from)
      })
      .catch(err => {
        console.log('Authentication error: ', err, ', will goto Login page')
      });
    }
  });


    return (
      <Container component="main" maxWidth="xs">
        <CssBaseline/>
        <div className={classes.paper}>
          <Avatar className={classes.avatar}>
            <LockOutlinedIcon/>
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <form className={classes.form} noValidate>
            <TextField
              value={state.username}
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="username"
              label="Username"
              name="username"
              autoComplete="username"
              autoFocus
              onChange={e => dispatch({
                type: 'change',
                field: 'username',
                target: e.target.value,
              })}
            />
            <TextField
              value={state.password}
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
              onChange={e => dispatch({
                type: 'change',
                field: 'password',
                target: e.target.value,
              })}
            />
            <FormControlLabel
              control={<Checkbox value="remember" color="primary"/>}
              label="Remember me"
            />
            <Button
              fullWidth
              variant="contained"
              color="primary"
              className={classes.submit}
              onClick={() => handleLogin(state.username, state.password)}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item xs>
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <Link href="#" variant="body2">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </form>
        </div>
        <Box mt={8}>
          <Copyright/>
        </Box>
        <Dialog
          open={state.willTellFailure}
          onClose={() => dispatch({type: 'login_fail_end',})}
          aria-labelledby="alert-dialog-title"
          aria-describedby="alert-dialog-description"
        >
          <DialogTitle id="alert-dialog-title">{'>_<'}</DialogTitle>
          <DialogContent>
            <DialogContentText>
              Login Failed
            </DialogContentText>
          </DialogContent>
          <DialogActions>
            <Button
              onClick={() => dispatch({type: 'login_fail_end',})}
              color="primary"
              variant="contained">
              Confirm
            </Button>
          </DialogActions>
        </Dialog>
      </Container>
    )
};

export default Login;

