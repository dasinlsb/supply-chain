import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import Checkbox from '@material-ui/core/Checkbox';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import {List} from "@material-ui/core";
import ListItem from "@material-ui/core/ListItem";

const useStyles = makeStyles({
  root: {
    width: '100%',
  },
});

interface UserData {
  name: string;
  articleList: string[];
}

export default function ActionsInExpansionPanelSummary() {
  const classes = useStyles();

  const data: UserData[] = [
    {name: 'bob', articleList: ['1', '2'], },
    {name: 'alice', articleList: ['3', '4'], },
  ];
  const users = data.map((user , userIndex) =>
    <ExpansionPanel key={userIndex}>
      <ExpansionPanelSummary aria-controls="panel1d-content" id="panel1d-header">
        <Typography>{user.name}</Typography>
      </ExpansionPanelSummary>
      <ExpansionPanelDetails>
        <List>
          {user.articleList.map((article, articleIndex) => (
            <ListItem key={articleIndex}>
              <Typography variant="body1" key={articleIndex}>
                {article}
              </Typography>
            </ListItem>
          ))}
        </List>
      </ExpansionPanelDetails>
    </ExpansionPanel>
  );

  return (
    <div className={classes.root}>
      {users}
    </div>
  );
}