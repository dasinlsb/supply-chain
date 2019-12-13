import * as React from 'react';
import AppBar from '@material-ui/core/AppBar';
import Button from '@material-ui/core/Button';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import ForumTwoToneIcon from '@material-ui/icons/ForumTwoTone';
import PeopleAltTwoToneIcon from '@material-ui/icons/PeopleAltTwoTone';
import LocalOfferTwoToneIcon from '@material-ui/icons/LocalOfferTwoTone';
import {useAuth} from "../AuthProvider";
import {Link} from "react-router-dom";

const useStyles = makeStyles(theme => ({
  '@global': {
    ul: {
      margin: 0,
      padding: 0,
    },
    li: {
      listStyle: 'none',
    },
  },
  appBar: {
    borderBottom: `1px solid ${theme.palette.divider}`,
  },
  toolbar: {
    flexWrap: 'wrap',
  },
  toolbarTitle: {
    flexGrow: 1,
  },
  link: {
    margin: theme.spacing(1, 1.5),
  },
  heroContent: {
    padding: theme.spacing(8, 0, 6),
  },
  cardHeader: {
    backgroundColor:
      theme.palette.type === 'dark' ? theme.palette.grey[700] : theme.palette.grey[200],
  },
  cardPricing: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'baseline',
    marginBottom: theme.spacing(2),
  },
  footer: {
    borderTop: `1px solid ${theme.palette.divider}`,
    marginTop: theme.spacing(8),
    paddingTop: theme.spacing(3),
    paddingBottom: theme.spacing(3),
    [theme.breakpoints.up('sm')]: {
      paddingTop: theme.spacing(6),
      paddingBottom: theme.spacing(6),
    },
  },
}));

const HomeNav = () => {
  const classes = useStyles();
  const auth = useAuth();
  return (
    <AppBar position="static" color="default" elevation={0} className={classes.appBar}>
      <Toolbar className={classes.toolbar}>
        <Typography variant="h6" color="inherit" noWrap className={classes.toolbarTitle}>
          Mirror of Emacs China
        </Typography>
        <nav>
          <Link to="/article">
            <ForumTwoToneIcon>Article</ForumTwoToneIcon>
          </Link>
          <Link to="/user">
            <PeopleAltTwoToneIcon>User</PeopleAltTwoToneIcon>
          </Link>
          <Link to="/tag">
            <LocalOfferTwoToneIcon>Tag</LocalOfferTwoToneIcon>
          </Link>
        </nav>
        <Button color="primary" variant="outlined" className={classes.link} onClick={() => {
          auth.logout().then(() => window.location.reload());
        }}>
          Logout
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default HomeNav;
