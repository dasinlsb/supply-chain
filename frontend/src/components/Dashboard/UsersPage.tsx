import React from "react";
import {createStyles, Paper, Theme} from "@material-ui/core";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import {useGet} from "restful-react/lib";

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      width: "90%",
      overflowX: "auto"
    },
    table: {
      minWidth: 700
    }
  })
);

interface UserState {
  orgAddr: string;
  orgId: string;
  orgType: string;
}

export default function UsersPage() {
  const classes = useStyles();
  const { data: users, } = useGet({
    path: '/users',
    resolve: data => data.users,
  });
  return (
    <>
      <Paper className={classes.root}>
        <Table className={classes.table}>
          <TableHead>
            <TableRow>
              <TableCell>组织地址</TableCell>
              <TableCell>组织编号</TableCell>
              <TableCell>组织类型</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {(users || []).map((user: UserState, index: number) => (
              <TableRow key={index}>
                <TableCell>{user.orgAddr}</TableCell>
                <TableCell>{user.orgId}</TableCell>
                <TableCell>{user.orgType}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>
    </>
  )
}
