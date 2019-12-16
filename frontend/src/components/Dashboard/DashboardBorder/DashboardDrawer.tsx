import React from 'react';
import { createStyles, Theme, makeStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import {Receipt} from "@material-ui/icons";
import GroupIcon from '@material-ui/icons/Group';
import ListIcon from '@material-ui/icons/List';
import {Link} from "react-router-dom";

const drawerWidth = 240;

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      display: 'flex',
    },
    drawer: {
      width: drawerWidth,
      flexShrink: 0,
    },
    drawerPaper: {
      width: drawerWidth,
    },
    toolbar: theme.mixins.toolbar,
    content: {
      flexGrow: 1,
      backgroundColor: theme.palette.background.default,
      padding: theme.spacing(3),
    },
  }),
);

export default function DashboardDrawer() {
  const classes = useStyles();
  return (
    <div className={classes.root}>
      <Drawer
        className={classes.drawer}
        variant="permanent"
        classes={{
          paper: classes.drawerPaper,
        }}
        anchor="left"
      >
        <div className={classes.toolbar} />
        <Divider />
        <List>
          <ListItem button component={Link} to="/dashboard/mine">
            <ListItemIcon><Receipt/></ListItemIcon>
            <ListItemText primary={'我的白条'} />
          </ListItem>
          <ListItem button component={Link} to="/dashboard/users">
            <ListItemIcon><GroupIcon/></ListItemIcon>
            <ListItemText primary={'用户列表'} />
          </ListItem>
          <ListItem button component={Link} to="/dashboard/query">
            <ListItemIcon><ListIcon/></ListItemIcon>
            <ListItemText primary={'交易查询'} />
          </ListItem>
        </List>
      </Drawer>
    </div>
  );
}
