import React from 'react';
import { createStyles, Theme, makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import {Button} from "@material-ui/core";
import {useAuth} from "../../AuthProvider";

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      display: 'flex',
    },
    appBar: {
      zIndex: theme.zIndex.drawer + 1,
    },
    title: {
      flexGrow: 1,
    },
  }),
);

export default function DashboardAppbar() {
  const classes = useStyles();

  const auth = useAuth();

  return (
    <div className={classes.root}>
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <Typography variant="h6" noWrap className={classes.title}>
            {"供应链金融平台"}
          </Typography>
          <Button color="inherit" onClick={auth.logout}>登出</Button>
        </Toolbar>
      </AppBar>
    </div>
  );
}
