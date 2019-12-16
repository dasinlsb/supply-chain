import React from "react";
import {useGet} from "restful-react/lib";
import IouModal from "./IouModal";
import IouTable from "../IouTable";
import IouTrans from "./IouTrans";
import IouPay from "./IouPay";
import {createStyles, Theme, Toolbar} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    button: {
      marginRight: theme.spacing(3),
    },
  })
);

export default function PrivatePage() {

  const classes = useStyles();

  const { data: ious, } = useGet({
    path: '/ious/me',
    resolve: data => data.ious,
  });
  return (
    <>
      <Toolbar>
        <div className={classes.button}>
          <IouModal />
        </div>
        <div className={classes.button}>
          <IouTrans/>
        </div>
        <div className={classes.button}>
          <IouPay/>
        </div>
      </Toolbar>
      <IouTable ious={ious}/>
    </>
  )
}
