# 供应链金融平台

SYSU 2019 区块链课程期末作业

> 真实报告存放在`doc/report.md`

## 构建和运行

### 后端 (`backend`目录)

请将账户私钥信息（PEM/PKCS12）替换`src/main/resources/application.yml`中的`accounts`字段，并将文件放在该`resources`目录下。

默认会读取`accounts.pem-file`字段作为私钥文件名

使用maven安装依赖后运行`org.dasin.supply.Application`类监听本地8080端口

### 前端(`frontend`目录)

```bash
    cd frontend
    yarn
    yarn start
```

登录注册功能未实现，请直接进入`http://localhost:3000/dashboard`

