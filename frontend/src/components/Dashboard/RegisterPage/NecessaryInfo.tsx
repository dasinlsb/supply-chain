import React from 'react';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import {MenuItem} from "@material-ui/core";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import {useContainer} from "unstated-next";
import {SubstepProps} from "./index";
import Button from "@material-ui/core/Button";
import Dropzone from "react-dropzone-uploader";
import 'react-dropzone-uploader/dist/styles.css';


import config from "../../../config";
import qs from 'qs';
import Typography from "@material-ui/core/Typography";


const useStyles = makeStyles(theme => ({
  paper: {
    marginTop: theme.spacing(0),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
  formControl: {
    margin: theme.spacing(0),
    minWidth: 120,
  },
}));

export default function NecessaryInfo(props: SubstepProps) {
  const classes = useStyles();
  return (
    <Container component="main" maxWidth="xs">
      <div className={classes.paper}>
        <div className={classes.form}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              {"组织信息"}
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                label="账户地址"
                name="orgAddr"
                onChange={props.updateInfo}
                value={props.info.orgAddr}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                label="组织编号"
                name="orgId"
                onChange={props.updateInfo}
                value={props.info.orgId}
              />
            </Grid>
            <Grid item xs={12}>
              <FormControl variant="filled" className={classes.formControl} required={true} fullWidth={true}>
                <InputLabel htmlFor="age-simple">组织类型</InputLabel>
                <Select
                  inputProps={{
                    name: 'orgType',
                    id: 'age-simple',
                  }}
                  value={props.info.orgType}
                  onChange={props.updateInfo}
                >
                  <MenuItem value={""}>{' '}</MenuItem>
                  <MenuItem value={"enterprise"}>企业</MenuItem>
                  <MenuItem value={"funder"}>资金提供方</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                label="额度限制"
                name="iouLimit"
                onChange={props.updateInfo}
                value={props.info.iouLimit}
              />
            </Grid>
            <Dropzone
              getUploadParams={() => ({
                url: config.api_server_url + '/upload?' + qs.stringify({type: 'pem',})
              })}
              onSubmit={(files, allFiles) => allFiles.forEach(f => f.remove())}
              inputContent={(file, extra) => (extra.reject ? '只允许上传以.pem后缀的文件' : '请点击或拖拽上传pem私钥文件')}
              // onChangeStatus={({ meta }, status) => {
              //   console.log(status, meta)
              // }}
              accept=".pem"
            />
          </Grid>
        </div>
      </div>

    </Container>
  );
}
