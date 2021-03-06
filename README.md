# 供应链金融平台

[![Build Status](https://travis-ci.com/dasinlsb/supply-chain.svg?branch=master)](https://travis-ci.com/dasinlsb/supply-chain)

SYSU 2019 区块链课程 期末作业

> 真实报告存放在`doc/report.md`

## 预览

![](doc/assets/new/mine_page.png)

![](doc/assets/new/users_page.png)

![](doc/assets/new/query_page.png)

![](doc/assets/new/register_page.png)

## 构建和运行


### 后端 (`backend`目录)

由于注册新组织需要将私钥文件存入mysql，所以需设置`application.yml`中的`spring.datasource.password`字段

也可以通过docker运行mysql:

```shell
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=12345abcde mysql
```

#### 启动

```bash
cd backend
./gradlew build
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
```

### 前端(`frontend`目录)

```bash
cd frontend
yarn
yarn start
```

请用浏览器访问`http://localhost:3000`。

第一次使用请先部署合约，并使用管理员账户(部署成功后返回的地址)登录。

只有管理员账户有增加成员的权限。

