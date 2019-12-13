curl -XPOST -H "Content-Type: application/json" -d '{"orgId": "1", "orgName": "sysu", "orgType": "enterprise", "corporationIdCardNumber": "33108", "phoneNumber": "133", "username": "dasin", "password":"123", "creatTime": "2019"}' localhost:8080/api/v1/users
curl -XPOST -H "Content-Type: application/json" -d '{"username": "dasin", "password":"123"}' localhost:8080/api/v1/login


cp /home/dasin/fisco/console/contracts/sdk/java/org/dasin/supply/contract/* /home/dasin/Courses/blkchain/supply/backend/src/main/java/org/dasin/supply/contract
