import * as React from "react";

import {BrowserRouter, Redirect, Route, RouteProps, Switch, useHistory} from "react-router-dom";
import Login from "./Login";
import Register from "./Register";
import Article from "./Home/Article";
import User from "./Home/User";
import Tag from "./Home/Tag";
import {ComponentType, useContext} from "react";
import {AuthProvider, useAuth} from "./AuthProvider";
import Home from "./Home/Home";

const AuthRoute: React.FC<RouteProps> = ({component, ...rest}) => {
  const auth = useAuth();
  const Component: React.ElementType = component || Article;
  return (
    <Route
      {...rest}
      render={(props) => {
        console.log("Will render AuthRoute, isAuthenticated:", auth.state.isAuthenticated);
        return auth.state.isAuthenticated ? (
          <Component {...props} />
        ) : (
          <Redirect to={{
            pathname: '/login',
            state: {from: props.location},
          }}
          />
        )
      }
      }
    />
  )
};

const Blog: React.FC = () => {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Switch>
          <Route path="/login" component={Login}/>
          <Route path="/register" component={Register}/>
          <AuthRoute path="/" component={Home}/>
        </Switch>
      </BrowserRouter>
    </AuthProvider>
  )
};

export default Blog;