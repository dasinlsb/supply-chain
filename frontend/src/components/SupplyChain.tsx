import React from "react";
import {BrowserRouter, Route, RouteProps, Redirect, Switch} from "react-router-dom";
import Dashboard from "./Dashboard";
import {AuthProvider, useAuth} from "./AuthProvider";
import EntryPage from "./EntryPage";

const AuthRoute: React.FC<RouteProps> = ({component, ...rest}) => {
  const auth = useAuth();
  const Component: React.ElementType = component || Dashboard;
  return (
    <Route
      {...rest}
      render={(props) => {
        return auth.state.isAuthenticated ? (
          <Component {...props} />
        ) : (
          <Redirect to={{
            pathname: '/',
            state: {from: props.location},
          }}
          />
        )
      }
      }
    />
  )
};

export default function SupplyChain() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component={EntryPage} />
          <AuthRoute path="/dashboard" component={Dashboard} />
          <Route path="/*" component={EntryPage} />
        </Switch>
      </BrowserRouter>
    </AuthProvider>
  )
}
