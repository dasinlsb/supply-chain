pragma solidity ^0.4.24;

contract Supply {
    address public owner = msg.sender;

    modifier byAdmin {
        require(msg.sender == owner);
        _;
    }

    struct Organization {
        bool valid;
        address addr;
        string orgId;
        string orgType;
        int64 iouLimit;
        int64[] outIous;
        int64[] inIous;
    }

    struct Iou {
        int64 iouId;
        address fromAddr;
        address toAddr;
        string createTime;
        int64 amount;
        int64 remain;
        string due;
    }

    mapping(address=>Organization) org_store;
    address[] org_list;
    mapping(int64=>Iou) iou_store;
    int64 iou_count;

    constructor() public {
        iou_count = 0;
    }

    event OrgCreation(address indexed addr, string orgId, string orgType, int64 iouLimit);
    event IouCreation(int64 indexed iouId, address fromAddr, address toAddr, int64 amount, string due);
    event IouPayback(int64 indexed iouId, int64 value, int64 indexed remain);
    event IouSplit(int64 indexed iouId, int64 value1);
    event IouTransferTo(int64 indexed iouId, address toAddr);
    event IouTransferFrom(int64 indexed iouId, address fromAddr);

    function isValidAddr(address addr) public view returns (bool) {
        return org_store[addr].valid;
    }

    // iou
    function addIou(address fromAddr, address toAddr, string memory createTime, int64 amount, string memory due) public returns (int64) {
        // not empty
        require(isValidAddr(fromAddr));
        require(isValidAddr(toAddr));
        // renter proposes
        require(msg.sender == fromAddr);
        // enough iouLimit
        require(0 < amount);
        require(org_store[msg.sender].iouLimit >= amount);

        iou_count += 1;
        iou_store[iou_count] = Iou(iou_count, fromAddr, toAddr, createTime, amount, amount, due);
        org_store[fromAddr].iouLimit -= amount;
        org_store[fromAddr].outIous.push(iou_count);
        org_store[toAddr].inIous.push(iou_count);
        emit IouCreation(iou_count, fromAddr, toAddr, amount, due);
        return iou_count;
    }

    function getOrgAddrs(uint256 count) public view returns (address[]) {
        require(0 <= count);
        if (org_list.length < count) {
            count = org_list.length;
        }
        address[] memory addrs = new address[](count);
        for (uint256 i = 0; i < count; i++) {
            addrs[i] = org_list[i];
        }
        return addrs;
    }

    // organization
    function getOrgInfo(address addr) public view returns (string memory, string memory, int64) {
        require(isValidAddr(addr));
        return (org_store[addr].orgId,
        org_store[addr].orgType,
        org_store[addr].iouLimit);
    }

    function getIouInfo(int64 iouId) public view returns (int64, address, address, string, int64, int64, string) {
        require(iou_store[iouId].iouId > 0);
        Iou memory iou = iou_store[iouId];
        return (iou.iouId, iou.fromAddr, iou.toAddr, iou.createTime, iou.amount, iou.remain, iou.due);
    }

    function getOrgOutIous(address addr) public view returns (int64[]) {
        require(isValidAddr(addr));
        return org_store[addr].outIous;
    }

    function getOrgInIous(address addr) public view returns (int64[]) {
        require(isValidAddr(addr));
        return org_store[addr].inIous;
    }

    function addOrg(address addr, string memory orgId, string memory orgType, int64 iouLimit) public byAdmin {
        require(!isValidAddr(addr));
        int64[] memory ins;
        int64[] memory outs;
        Organization memory org = Organization(true, addr, orgId, orgType, iouLimit, ins, outs);
        org_store[addr] = org;
        org_list.push(addr);
        emit OrgCreation(addr, orgId, orgType, iouLimit);
    }

    function payIou(int64 iouId, int64 value) public {
        require(msg.sender == iou_store[iouId].fromAddr);
        require(org_store[msg.sender].iouLimit >= value);
        require(iou_store[iouId].remain >= value);
        iou_store[iouId].remain -= value;
        if (iou_store[iouId].remain == 0) {
            //TODO: clean empty iou
        }
        emit IouPayback(iouId, value, iou_store[iouId].remain);
    }

    function splitIou(int64 iouId, int64 value1, string memory createTime) public {
        require(iou_store[iouId].remain > value1 && value1 > 0);
        Iou memory oldIou = iou_store[iouId];
        addIou(oldIou.fromAddr, oldIou.toAddr, createTime, value1, oldIou.due);
        iou_store[iouId].remain -= value1;
        emit IouSplit(iouId, value1);
    }

    function transferIouTo(int64 iouId, address toOrgAddr) public {
        require(iou_store[iouId].toAddr == msg.sender);
        iou_store[iouId].toAddr = toOrgAddr;
        emit IouTransferTo(iouId, toOrgAddr);
    }

    function transferIouFrom(int64 iouId, address fromOrgAddr) public {
        require(iou_store[iouId].fromAddr == msg.sender);
        iou_store[iouId].fromAddr = fromOrgAddr;
        emit IouTransferFrom(iouId, fromOrgAddr);
    }
}
