import React from "react";
import {Typography} from "@material-ui/core";

type FinishProps = {
  account: string;
  success: boolean;
}

export default function Finish(props: FinishProps) {
  return (
    <React.Fragment>
      <Typography variant="h5" gutterBottom>
        {props.success ? (
          "注册成功"
        ) : (
          "注册失败"
        )}
      </Typography>
      <Typography variant="subtitle1">
        {props.success ? (
          "账户地址: " + props.account
        ) : (
          "请仔细检查信息是否填写错误"
        )}

      </Typography>
    </React.Fragment>
  )
}
