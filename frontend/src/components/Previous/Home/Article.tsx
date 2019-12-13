import React, {useContext, useEffect, useReducer} from 'react';
import AppBar from '@material-ui/core/AppBar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import Box from '@material-ui/core/Box';
import ForumTwoToneIcon from '@material-ui/icons/ForumTwoTone';
import PeopleAltTwoToneIcon from '@material-ui/icons/PeopleAltTwoTone';
import LocalOfferTwoToneIcon from '@material-ui/icons/LocalOfferTwoTone';
import {Paper} from "@material-ui/core";
import {Redirect} from "react-router";
import {useAuth} from "../AuthProvider";
import {Link} from "react-router-dom";
import Grid from "@material-ui/core/Grid";


const useStyles = makeStyles(theme => ({
  articlePaper: {
    padding: theme.spacing(3, 2),
    marginBottom: theme.spacing(3),
  },
}));

interface ArticleData {
  title: string;
  content: string;
  floors: number;
}

interface ArticleProps {
  location: any,
}

type ReducerAction =
  |{ type: 'goto_page';
     target: number;
  }
  |{ type: 'refresh_data';
     status: boolean;
     data: ArticleData[];
  }

interface ArticleState {
  hasFetched: boolean;
  articleList: ArticleData[];
  page: number;
}

const initState: ArticleState = {
  hasFetched: false,
  articleList: ['?', '?'].map(s => ({
    'title': s,
    'content': s,
    'floors': -1,
  })),
  page: 1,
};

function reducer(state: ArticleState, action: ReducerAction) {
  switch (action.type) {
    case 'goto_page':
      return { hasFetched: false, articleList: [], page: action.target, };
    case 'refresh_data':
      return { ...state, hasFetched: action.status, articleList: action.data, };
    default:
      return state;
  }
}

const Article: React.FC<ArticleProps> = (props: any) => {
  const classes = useStyles();
  const [state, dispatch] = useReducer(reducer, initState);
  const auth = useAuth();
  const fetchData = () => {
    auth.fetchArticleList(state.page)
      .then(
        (articles: ArticleData[]) => {
          dispatch({ type: 'refresh_data', status: true, data: articles });
        }
      )
      .catch(e => {
        console.log('Fetch article data failed: ', e);
        dispatch({ type: 'refresh_data', status: true, data: initState.articleList });
      })
  };

  useEffect(() => {
    //if (!state.hasFetched) {
      fetchData();
    //}
  }, []);

  if (!auth.state.isAuthenticated) {
    return <Redirect to={'/login'} />
  }

  const article = state.articleList.map(({title, content, floors}: ArticleData, index: number) => (
    <Paper className={classes.articlePaper} key={index} onClick={() => console.log('go')}>
      <Grid container>
        <Grid item xs={8}>
          <Typography variant="h5" component="h3">
            {title}
          </Typography>
        </Grid>
        <Grid item xs={4}>
          <Typography component="p">
            {floors}
          </Typography>
        </Grid>
      </Grid>
    </Paper>
  ));
  return (
    <React.Fragment>
      <CssBaseline />
      {article}
    </React.Fragment>
  );
};

export default Article;
