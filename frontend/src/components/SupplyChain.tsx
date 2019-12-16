import React from "react";
import {BrowserRouter, Route, RouteProps, Redirect, Switch} from "react-router-dom";
import EntryPage from "./Entry/EntryPage";
import Dashboard from "./Dashboard";
import {AuthProvider, useAuth} from "./AuthProvider";
import RegisterPage from "./Register/RegisterPage";

const AuthRoute: React.FC<RouteProps> = ({component, ...rest}) => {
  const auth = useAuth();
  const Component: React.ElementType = component || Dashboard;
  console.log('AuthRoute is rendering...');
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
          <Route path="/register" component={RegisterPage} />
          <AuthRoute path="/dashboard" component={Dashboard} />
          <Route path="/*" component={EntryPage} />
        </Switch>
      </BrowserRouter>
    </AuthProvider>
  )
}
