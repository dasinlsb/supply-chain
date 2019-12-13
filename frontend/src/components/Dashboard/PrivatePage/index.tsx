import React from "react";
import {useGet} from "restful-react/lib";
import IouModal from "./IouModal";
import IouTable from "../IouTable";
import IouTrans from "./IouTrans";
import IouPay from "./IouPay";
import {Toolbar} from "@material-ui/core";


export default function PrivatePage() {

  const { data: ious, } = useGet({
    path: '/ious/me',
    resolve: data => data.ious,
  });
  return (
    <>
      <Toolbar>
        <IouModal/>
        <IouTrans/>
        <IouPay/>
      </Toolbar>
      <IouTable ious={ious}/>
    </>
  )
}
