import React, {useReducer} from 'react';
import { withStyles } from '@material-ui/core/styles';
import { green } from '@material-ui/core/colors';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox, { CheckboxProps } from '@material-ui/core/Checkbox';
import {ExpansionPanel} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import ExpansionPanelSummary from "@material-ui/core/ExpansionPanelSummary";
import ExpansionPanelDetails from "@material-ui/core/ExpansionPanelDetails";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";

interface TagState {
  checks: boolean[];
}

const initialState: TagState = {
  checks: [],
};

type ReducerAction =
  | { type: 'do_check', id: number, }

function reducer (state: TagState, action: ReducerAction) {
  switch (action.type) {
    case "do_check":
      return { ...state, checks: state.checks.map((x, i) => (i === action.id ? !x : x))}
    default:
      return state;
  }
}

export default function CheckboxLabels() {
  const [state, dispatch] = useReducer(reducer, initialState);
  const handleChange = (index: number) => (event: React.ChangeEvent<HTMLInputElement>) => {

  };

  const data = [1, 2].map((x) => ({ label: "tag" + x, }));
  const tags = (
    <List>{
      data.map((tag, index) =>
        <ListItem>
          <FormControlLabel
            control={
              <Checkbox
                checked={state.checks[index]}
                onChange={handleChange(index)}
                color="primary"
              />
            }
            label={tag.label}
          />
        </ListItem>
      )}
    </List>
  );


  return (
    <>
      <ExpansionPanel>
        <ExpansionPanelSummary aria-controls="panel1d-content" id="panel1d-header">
          <Typography>{'FILTER'}</Typography>
        </ExpansionPanelSummary>
        <ExpansionPanelDetails>
          <FormGroup row>
            {tags}
          </FormGroup>
        </ExpansionPanelDetails>
      </ExpansionPanel>

    </>
  );
}