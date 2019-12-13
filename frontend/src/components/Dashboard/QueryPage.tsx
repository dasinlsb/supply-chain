import React, {useState} from "react";
import {createStyles, Theme} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import {useGet} from "restful-react/lib";
import InputLabel from "@material-ui/core/InputLabel";
import Input from "@material-ui/core/Input";
import InputAdornment from "@material-ui/core/InputAdornment";
import IconButton from "@material-ui/core/IconButton";
import FormControl from "@material-ui/core/FormControl";
import SearchIcon from '@material-ui/icons/Search';
import IouTable, {IouState} from "./IouTable";
import qs from "qs";
const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      width: "90%",
      overflowX: "auto"
    },
  })
);

interface QueryState {
  fromAddr: string;
  ious: IouState[];
}

const initialState: QueryState = {
  fromAddr: '',
  ious: [],
}

export default function QueryPage() {
  const classes = useStyles();
  const [state, setState] = useState(initialState);

  const updateInfo = (event: React.ChangeEvent<{ name?: string; value: unknown }>) => {
    setState({...state, [event.target.name as string]: event.currentTarget.value,})
  };

  const { data: query, refetch } = useGet({
    path: '/ious?'+qs.stringify({addr: state.fromAddr}),
    resolve: data => {
      console.log("query result: ", data);
      return data.ious;
    },
    lazy: true,
  });
  return (
    <div className={classes.root}>
      <FormControl>
        <InputLabel htmlFor="borrower">借入组织地址</InputLabel>
        <Input
          value={state.fromAddr}
          onChange={updateInfo}
          id="borrower"
          name="fromAddr"
          endAdornment={
            <InputAdornment position="end">
              <IconButton onClick={() => refetch()}>
                <SearchIcon />
              </IconButton>
            </InputAdornment>
          }
        />
      </FormControl>
      <IouTable ious={query||[]}/>
    </div>
  )
}
