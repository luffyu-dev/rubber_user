## RUBBER_USER
> rubber_user服务是提供用户登录、用户基础数据拉取的基础服务

### 层次结构
- dao：提供与数据库持久层的数据交互
- api：可对外暴露的service服务层，和服务常用的数据常量层，可提供给dubbo的应用，无其他服务依赖
- service：业务实现的逻辑层，用于服务代码的实现
- manager：本服务和外部服务的调用层
- web_starter: web调用服务，对外提供http的接口