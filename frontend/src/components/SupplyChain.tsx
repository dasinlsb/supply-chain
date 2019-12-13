import React from "react";
import {BrowserRouter, Route} from "react-router-dom";
import EntryPage from "./Entry/EntryPage";
import NoRegister from "./Register/NoRegister";
import Dashboard from "./Dashboard";

export default function SupplyChain() {
  return (
    <>
      <BrowserRouter>
        <Route exact path="/" component={EntryPage} />
        <Route path="/register" component={NoRegister} />
        <Route path="/dashboard" component={Dashboard} />
      </BrowserRouter>
    </>
  )
}
