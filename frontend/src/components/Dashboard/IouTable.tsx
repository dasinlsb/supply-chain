import React, {ComponentProps} from "react";
import {createStyles, Paper, Theme} from "@material-ui/core";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";

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

export interface IouState {
  iouId: number;
  createTime: string;
  due: string;
  toOrgAddr: string;
  amount: number;
  remain: number;
}

export default function IouTable(props: {ious: IouState[]} ) {
  const classes = useStyles();

  return (
      <Paper className={classes.root}>
        <Table className={classes.table}>
          <TableHead>
            <TableRow>
              <TableCell>白条编号</TableCell>
              <TableCell>创建时间</TableCell>
              <TableCell>截止时间</TableCell>
              <TableCell>目标组织地址</TableCell>
              <TableCell>金额</TableCell>
              <TableCell>未还金额</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {(props.ious || []).map((iou: IouState, index: number) => (
              <TableRow key={index}>
                <TableCell>{iou.iouId}</TableCell>
                <TableCell>{iou.createTime}</TableCell>
                <TableCell>{iou.due}</TableCell>
                <TableCell>{iou.toOrgAddr}</TableCell>
                <TableCell>{iou.amount}</TableCell>
                <TableCell>{iou.remain}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>

  )
}
