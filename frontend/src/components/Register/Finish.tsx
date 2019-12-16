import React from "react";
import {Typography} from "@material-ui/core";

type FinishProps = {
  success: boolean;
}

export default function Finish(props: FinishProps) {
  return (
    <React.Fragment>
      <Typography variant="h5" gutterBottom>
        {props.success ? (
          "感谢注册"
        ) : (
          "注册失败"
        )}
      </Typography>
      <Typography variant="subtitle1">
        {props.success ? (
          "您已经完成注册, 请选择回到主页或者去往控制台"
        ) : (
          "抱歉, 服务出现了一些问题, 请稍后重新注册或联系 hamdppyy@gmail.com"
        )}

      </Typography>
    </React.Fragment>
  )
}
