import React, {useContext} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Paper from '@material-ui/core/Paper';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import NecessaryInfo from "./NecessaryInfo";
import Finish from "./Finish";
import Copyright from "../Copyright";
import BasicInfo from "./BasicInfo";
import {Link, Redirect} from 'react-router-dom';
import {Grid} from "@material-ui/core";
import {createContainer} from "unstated-next";

const useStyles = makeStyles(theme => ({
    appBar: {
        position: 'relative',
    },
    layout: {
        width: 'auto',
        marginLeft: theme.spacing(2),
        marginRight: theme.spacing(2),
        [theme.breakpoints.up(600 + theme.spacing(2) * 2)]: {
            width: 600,
            marginLeft: 'auto',
            marginRight: 'auto',
        },
    },
    paper: {
        marginTop: theme.spacing(3),
        marginBottom: theme.spacing(3),
        padding: theme.spacing(2),
        [theme.breakpoints.up(600 + theme.spacing(3) * 2)]: {
            marginTop: theme.spacing(6),
            marginBottom: theme.spacing(6),
            padding: theme.spacing(3),
        },
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

function getStepContent(step: number) {
    switch (step) {
        case 0:
            return <NecessaryInfo />;
        case 1:
            return <BasicInfo />;
        case 2:
            return <Finish success={false}/>;
        default:
            throw new Error('Unknown step');
    }
}

function isStepBeforeSubmit(step: number) {
    return step === steps.length - 2;
}

export interface RegisterInfoType {
    orgName: string;
    orgType: "enterprise" | "funder" | undefined;
    corporationIdCardNumber: string;
    phoneNumber: string;
    username: string;
    password: string;
}

const initialRegisterInfoType: RegisterInfoType = {
    corporationIdCardNumber: "", orgName: "", orgType: undefined, password: "", phoneNumber: "", username: ""
};

interface RegisterState {
    // register information
    info: RegisterInfoType;
    // page information
    activeStep: number;
}

const initialState: RegisterState = {
    activeStep: 0,
    info: initialRegisterInfoType,
};

function useRegProcessor() {
    const [state, setState] = React.useState(initialState);
    const gotoNextStep = () => {
        setState({...state, activeStep: state.activeStep+1, })
    };
    const gotoPreviousStep = () => {
        setState({...state, activeStep: state.activeStep-1, });
    };
    const updateInfo = (event: React.ChangeEvent<{ name?: string; value: unknown }>) => {
        setState({...state, info: {...state.info, [event.target.name as string]: event.currentTarget.value, }})
    };
    return { state, gotoNextStep, gotoPreviousStep, updateInfo, }
}

export const RegProcessor = createContainer(useRegProcessor);

export default function RegisterPage() {
    const classes = useStyles();
    const { state, gotoNextStep, gotoPreviousStep } = useRegProcessor();
    return (
      <RegProcessor.Provider>
          <CssBaseline />
          <AppBar position="absolute" color="default" className={classes.appBar}>
              <Toolbar>
                  <Typography variant="h6" color="inherit" noWrap>
                      注册
                  </Typography>
              </Toolbar>
          </AppBar>
          <main className={classes.layout}>
              <Paper className={classes.paper}>
                  <Stepper activeStep={state.activeStep} className={classes.stepper}>
                      {steps.map(label => (
                        <Step key={label}>
                            <StepLabel>{label}</StepLabel>
                        </Step>
                      ))}
                  </Stepper>
                  <Grid container justify="flex-end">
                      <Grid item>
                          <Link to="/">
                              {"已有账号? 立即登录!"}
                          </Link>
                      </Grid>
                  </Grid>
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
      </RegProcessor.Provider>
    );
}