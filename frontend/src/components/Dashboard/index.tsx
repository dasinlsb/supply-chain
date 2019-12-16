import React from 'react';
import { createStyles, Theme, makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import DashboardAppbar from "./DashboardBorder/DashboardAppbar";
import DashboardDrawer from "./DashboardBorder/DashboardDrawer";

import UsersPage from "./UsersPage";
import { Switch, Route } from 'react-router-dom';
import QueryPage from "./QueryPage";
import PrivatePage from "./PrivatePage";

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

  return (
    <div className={classes.root}>
      <CssBaseline />
      <DashboardAppbar />
      <DashboardDrawer />
      <main className={classes.content}>
        <div className={classes.toolbar} />
        <Switch>
          <Route exact path="/dashboard" component={PrivatePage} />
          <Route path="/dashboard/mine" component={PrivatePage} />
          <Route path="/dashboard/users" component={UsersPage} />
          <Route path="/dashboard/query" component={QueryPage} />
        </Switch>
      </main>
    </div>
  );
}
