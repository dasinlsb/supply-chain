pragma solidity ^0.4.24;

contract HelloWorld{
    string hname;
    constructor() public{
       hname = "Hello, World!";
    }

    function get()constant public returns(string){
        return hname;
    }

    function set(string n) public{
    	hname = n;
    }
}
