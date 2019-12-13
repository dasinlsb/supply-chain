pragma solidity ^0.4.24;

contract UserService {
    address public owner = msg.sender;

    mapping(int64=>string) user_store;

    modifier bySelf(address _account) {
        require(msg.sender == _account);
        _;
    }

    function getUser (int64 addr) public view returns(string memory) {
        return user_store[addr];
    }

    function setUser (int64 addr, string memory user) public bySelf(owner) {
        user_store[addr] = user;
    }
}
