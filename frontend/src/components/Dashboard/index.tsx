import React from 'react';
import { createStyles, Theme, makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import DashboardAppbar from "./DashboardBorder/DashboardAppbar";
import DashboardDrawer from "./DashboardBorder/DashboardDrawer";

import UsersPage from "./UsersPage";
import { Switch, Route } from 'react-router-dom';
import QueryPage from "./QueryPage";
import PrivatePage from "./PrivatePage";
import RegisterPage from "./RegisterPage";
import {useAuth} from "../AuthProvider";

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      display: 'flex',
    },
    toolbar: theme.mixins.toolbar,
    content: {
      flexGrow: 1,
      backgroundColor: theme.palette.background.default,
      padding: theme.spacing(3),
    },
  }),
);

export default function Dashboard() {
  const classes = useStyles();
  const auth = useAuth();

  return (
    <div className={classes.root}>
      <CssBaseline />
      <DashboardAppbar isAdmin={auth.state.isAdmin} account={auth.state.info.account}/>
      <DashboardDrawer isAdmin={auth.state.isAdmin}/>
      <main className={classes.content}>
        <div className={classes.toolbar} />
        <Switch>
          <Route exact path="/dashboard" component={PrivatePage} />
          <Route path="/dashboard/mine" component={PrivatePage} />
          <Route path="/dashboard/users" component={UsersPage} />
          <Route path="/dashboard/query" component={QueryPage} />
          <Route path="/dashboard/register" component={RegisterPage} />
        </Switch>
      </main>
    </div>
  );
}
