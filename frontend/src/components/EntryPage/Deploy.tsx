import React, {useState} from "react";
import {Button} from "@material-ui/core";
import Dialog from "@material-ui/core/Dialog";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContentText from "@material-ui/core/DialogContentText";
import axios from "axios";
import config from "../../config";
import Typography from "@material-ui/core/Typography";

interface DeployRespose {
  accountAddr: string;
  contractAddr: string;
}

interface EntryState {
  openDeploy: boolean;
  deployed: boolean;
  lastSuccess: boolean;
  deployResponse: DeployRespose;
}

const initialState: EntryState = {
  deployed: false,
  lastSuccess: true,
  openDeploy: false,
  deployResponse: {accountAddr: '', contractAddr: '',}
};

export default function Deploy() {
  const [state, setState] = useState(initialState);
  const handleClose = () => setState(initialState);
  const handleOpen = () => setState({ ...state, openDeploy: true, });

  const handleDeploy = () => {
    axios.post<DeployRespose>(config.api_server_url + '/deploy')
      .then(data => {
        setState({...state, deployResponse: data.data,
          deployed: true, lastSuccess: true, })
      }).catch(err => {
        setState({...state, deployed: true, lastSuccess: false, })
    })
  };

  const getDeployTitle = () => {
    if (!state.deployed) return '自动部署';
    return state.lastSuccess ? '成功' : '失败';
  };

  const getDeployContent = () => {
    if (!state.deployed) return (
      <DialogContentText id="alert-dialog-description">
        点击确定将会生成随机账户部署合约
      </DialogContentText>
    );
    return state.lastSuccess ?
      <div>
        <Typography gutterBottom>
          部署成功
        </Typography>
        <Typography gutterBottom>
          账户地址: {state.deployResponse.accountAddr}
        </Typography>
        <Typography gutterBottom>
          合约地址: {state.deployResponse.contractAddr}
        </Typography>
      </div> :
      <DialogContentText id="alert-dialog-description">
        未知错误，请再次尝试或者联系维护者...
      </DialogContentText>
  };

  const getContinueButton = () => {
    if (!state.deployed) return (
      <Button onClick={handleDeploy} color="primary" autoFocus>
        确定
      </Button>
    );
    //TODO: goto dashboard
    return (
      <Button onClick={handleClose} color="primary">
        确定
      </Button>
    );
  };

  return (
  <div>
    <Button variant="outlined" color="primary" onClick={handleOpen}>
      还没有部署合约？立即部署！
    </Button>
    <Dialog
      open={state.openDeploy}
      onClose={handleClose}
      aria-labelledby="alert-dialog-title"
      aria-describedby="alert-dialog-description"
    >
      <DialogTitle id="alert-dialog-title">
        {getDeployTitle()}
      </DialogTitle>
      <DialogContent>
        <DialogContentText id="alert-dialog-description">
          <>{getDeployContent()}</>
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          {state.deployed ? '关闭' : '取消'}
        </Button>
        {getContinueButton()}
      </DialogActions>
    </Dialog>
  </div>
  )
}
