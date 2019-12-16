# 供应链金融平台

SYSU 2019 区块链课程期末作业

> 真实报告存放在`doc/report.md`

## 构建和运行

### 后端 (`backend`目录)

#### 准备

请将账户私钥信息（PEM/PKCS12）替换`src/main/resources/application.yml`中的`accounts`字段，并将文件放在该`resources`目录下。

默认会读取`accounts.pem-file`字段作为私钥文件名

#### 部署

首次运行前请尝试修改账户私钥信息并单独运行`test/java`目录下的`org.dasin.supply.deploy.Admin`类的`deployAndCallHelloWorld`方法，该方法会将合约部署到链上并保存合约地址和管理员账户地址信息到`resources/contract.properties`

之后通过单独运行同一类中的`addOrg`方法可以读取管理员账户信息并创建新的组织

这一步详见视频`doc/preview.mp4`

#### 运行

使用maven安装依赖后运行`org.dasin.supply.Application`类监听本地8080端口

### 前端(`frontend`目录)

```bash
    cd frontend
    yarn
    yarn start
```

请用浏览器访问`http://localhost:3000`，输入后端配置文件`application.yml`中的`account.pem-file`字段值（不包括.pem后缀名）

