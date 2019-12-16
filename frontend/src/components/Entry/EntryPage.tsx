import React from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import { makeStyles } from '@material-ui/core/styles';
import Login from "./Login";
import {useAuth} from "../AuthProvider";
import {Redirect} from "react-router";

const useStyles = makeStyles(theme => ({
  root: {
    height: '100vh',
  },
  image: {
    backgroundImage: 'url(https://camo.githubusercontent.com/fc162fb0024699c85f00eae769085a5fe528153e/68747470733a2f2f7777772e61687374617469632e636f6d2f70686f746f732f636974792f76692d76363837315f30305f31343030783434322e6a7067)',
    backgroundRepeat: 'no-repeat',
    backgroundColor:
      theme.palette.type === 'dark' ? theme.palette.grey[900] : theme.palette.grey[50],
    backgroundSize: 'cover',
    backgroundPosition: 'center',
  },
}));

export default function EntryPage() {
  const classes = useStyles();
  const auth = useAuth();
  if (auth.state.isAuthenticated) {
    console.log('EntryPage will be redirected to dashboard');
  }
  return auth.state.isAuthenticated ? <Redirect to="/dashboard"/> :
    <Grid container component="main" className={classes.root}>
      <CssBaseline />
      <Grid item xs={false} sm={4} md={6} className={classes.image} />
      <Grid item xs={12} sm={8} md={6} component={Paper} elevation={6} square>
        <Login />
      </Grid>
    </Grid>;
}
