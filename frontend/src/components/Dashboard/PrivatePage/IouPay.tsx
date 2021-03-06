import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import {useMutate} from "restful-react/lib";
import {Snackbar} from "@material-ui/core";

interface IouState {
  openIou: boolean;
  openMsg: boolean;
  lastSuccess: boolean;
  toAddr: string;
  value: string;
  iouId: string;
}

const initialState: IouState = {
  openIou: false,
  openMsg: false,
  lastSuccess: false,
  toAddr: '',
  value: '',
  iouId: '',
};

export default function IouPay() {
  const [state, setState] = React.useState(initialState);

  const handleOpen = (name: 'openIou'|'openMsg') => () => {
    setState({...state, [name]: true});
  };

  const handleClose = (name: 'openIou'|'openMsg') => () => {
    setState({...state, [name]: false});
  };

  const updateInfo = (event: React.ChangeEvent<{ name?: string; value: unknown }>) => {
    setState({...state, [event.target.name as string]: event.currentTarget.value, })
  };

  const { mutate: addIou } = useMutate({
    verb: "POST",
    path: "/pay",
  });

  return (
    <div>
      <Button variant="outlined" color="primary" onClick={handleOpen('openIou')}>
        支付白条
      </Button>
      <Dialog open={state.openIou} onClose={handleClose('openIou')} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">填写信息</DialogTitle>
        <DialogContent>
          <TextField
            value={state.iouId}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="iouId"
            label="白条编号"
            onChange={updateInfo}
          />
          <TextField
            value={state.value}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            label="剩余金额"
            name="value"
            autoFocus
            onChange={updateInfo}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose('openIou')} color="primary">
            取消
          </Button>
          <Button onClick={() => {
            addIou(state)
            .then(() => {
              setState({...initialState, openIou: false, openMsg: true, lastSuccess: true, });
            }).catch(err => setState({...initialState, openIou: false, openMsg: true, lastSuccess: false, }))
          }} color="primary">
            提交
          </Button>
        </DialogActions>
      </Dialog>
      <Snackbar
        anchorOrigin={{ vertical: 'top', horizontal: 'center', }}
        open={state.openMsg}
        onClose={handleClose('openMsg')}
        ContentProps={{
          'aria-describedby': 'message-id',
        }}
        message={<span id="message-id">{state.lastSuccess ? '操作成功' : '操作失败,注意数据合法性及日期格式(yyyy.MM.dd)'}</span>}
      />
    </div>
  );
}
