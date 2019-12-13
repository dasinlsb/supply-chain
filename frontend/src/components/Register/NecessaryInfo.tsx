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
import {RegProcessor} from "./RegisterPage";


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

export default function NecessaryInfo() {
  const classes = useStyles();
  const reg = useContainer(RegProcessor);
  return (
    <Container component="main" maxWidth="xs">
      <div className={classes.paper}>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              {"组织信息"}
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                label="组织名称"
                name="orgName"
                onChange={reg.updateInfo}
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
                  onChange={reg.updateInfo}
                >
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
                label="法人身份证号"
                name="corporationIdCardNumber"
                onChange={reg.updateInfo}
              />
            </Grid>
            <Grid item xs={12}>
              {"登录信息"}
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                label="管理员手机"
                name="phoneNumber"
                onChange={reg.updateInfo}
              />
            </Grid>


            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                label="管理员用户名"
                name="username"
                onChange={reg.updateInfo}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                label="设置密码"
                name="password"
                onChange={reg.updateInfo}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                label="确认密码"
                //onChange={reg.updateInfo}
              />
            </Grid>

          </Grid>


        </form>
      </div>

    </Container>
  );
}
