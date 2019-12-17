import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import Button from '@material-ui/core/Button';
import NecessaryInfo from "./NecessaryInfo";
import Finish from "./Finish";
import Copyright from "../../Copyright";
import BasicInfo from "./BasicInfo";
import {Redirect} from 'react-router-dom';
import config from "../../../config";
import axios from "axios";

const useStyles = makeStyles(theme => ({
  appBar: {
    position: 'relative',
  },
  layout: {
    width: 'auto',
    // marginLeft: theme.spacing(2),
    // marginRight: theme.spacing(2),
    // [theme.breakpoints.up(600 + theme.spacing(2) * 2)]: {
    //     width: 600,
    //     marginLeft: 'auto',
    //     marginRight: 'auto',
    // },
  },
  paper: {
    // marginTop: theme.spacing(3),
    marginBottom: theme.spacing(3),
    // padding: theme.spacing(2),
    // [theme.breakpoints.up(600 + theme.spacing(3) * 2)]: {
    //     marginTop: theme.spacing(6),
    //     marginBottom: theme.spacing(6),
    //     padding: theme.spacing(3),
    // },
  },
  stepper: {
    padding: theme.spacing(3, 0, 5),
  },
  buttons: {
    display: 'flex',
    justifyContent: 'flex-end',
  },
  button: {
    marginTop: theme.spacing(3),
    marginLeft: theme.spacing(1),
  },
}));

const steps = ['必要信息', '完善基础信息(可选)', '注册完成'];



function isStepBeforeSubmit(step: number) {
  return step === steps.length - 2;
}

export interface RegisterInfoType {
  orgAddr: string;
  orgId: string;
  orgType: "enterprise" | "funder" | "";
  iouLimit: string;
}

const initialRegisterInfoType: RegisterInfoType = {
  iouLimit: "", orgAddr: "", orgId: "", orgType: "",
};

interface RegisterState {
  // register information
  info: RegisterInfoType;
  // page information
  activeStep: number;
  openMsg: boolean;
  lastSuccess: boolean;
}

const initialState: RegisterState = {
  activeStep: 0,
  info: initialRegisterInfoType,
  openMsg: false,
  lastSuccess: true,
};

export interface SubstepProps {
  info: RegisterInfoType;
  updateInfo: (event: React.ChangeEvent<{ name?: string; value: unknown }>) => void;
}

export default function RegisterPage() {
  const classes = useStyles();

  const [state, setState] = React.useState(initialState);
  const gotoNextStep = () => {
    if (state.activeStep == steps.length - 2) {
      axios.post(config.api_server_url + '/users', state.info).then(() => setState({...state, activeStep: state.activeStep+1, lastSuccess: true, }))
      .catch(err => setState({...state, activeStep: state.activeStep+1, lastSuccess: false, }))
    } else if (state.activeStep < steps.length - 2) {
      setState({...state, activeStep: state.activeStep + 1,})
    }
  };
  const gotoPreviousStep = () => {
    setState({...state, activeStep: state.activeStep-1, });
  };
  const updateInfo = (event: React.ChangeEvent<{ name?: string; value: unknown }>) => {
    //console.log(event.target.name as string, event.target.value);
    setState({...state, info: {...state.info, [event.target.name as string]: event.target.value,}})
  };

  function getStepContent(step: number) {
    switch (step) {
      case 0:
        return <NecessaryInfo info={state.info} updateInfo={updateInfo}/>;
      case 1:
        return <BasicInfo/>;
      case 2:
        return <Finish success={state.lastSuccess} account={state.info.orgAddr}/>;
      default:
        throw new Error('Unknown step');
    }
  }

  return (
    <>
      <main className={classes.layout}>
        <Paper className={classes.paper}>
          <Stepper activeStep={state.activeStep} className={classes.stepper}>
            {steps.map(label => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>
          <React.Fragment>
            {state.activeStep === steps.length ? (
              <Redirect to="/" />
            ) : (
              <React.Fragment>
                {getStepContent(state.activeStep)}
                <div className={classes.buttons}>
                  {state.activeStep !== 0 && state.activeStep < steps.length-1 && (
                    <Button onClick={gotoPreviousStep} className={classes.button}>
                      {"上一步"}
                    </Button>
                  )}
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={gotoNextStep}
                    className={classes.button}
                  >
                    {state.activeStep === steps.length - 1 ? '回到主页' : '下一步'}
                  </Button>
                </div>
              </React.Fragment>
            )}
          </React.Fragment>
        </Paper>
        <Copyright />
      </main>
    </>
  );
}
