import * as React from "react";
import {Route, Switch} from "react-router";
import Article from "./Article";
import User from "./User";
import Tag from "./Tag";
import HomeNav from "./HomeNav";
import {Container} from "@material-ui/core";
import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import makeStyles from "@material-ui/core/styles/makeStyles";

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

const Home = () => {
  const classes = useStyles();
  return (
    <>
      <HomeNav/>
      <Switch>
        <Route exact path="/" component={Article} />
        <Route path="/article" component={Article} />
        <Route path="/user" component={User} />
        <Route path="/tag" component={Tag} />
      </Switch>
      <Container maxWidth="md" component="footer" className={classes.footer}>
        <Box mt={5}>
          <Copyright />
        </Box>
      </Container>
    </>
  )
};

export default Home;