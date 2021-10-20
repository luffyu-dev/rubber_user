## RUBBER_USER
> rubber_user服务是提供用户登录、用户基础数据拉取的基础服务

### 层次结构
- dao：提供与数据库持久层的数据交互
- api：可对外暴露的service服务层，和服务常用的数据常量层，可提供给dubbo的应用，无其他服务依赖
- service：业务实现的逻辑层，用于服务代码的实现
- manager：本服务和外部服务的调用层
- web_starter: web调用服务，对外提供http的接口



### DAO层介绍

#### DAO层单库表设计
```
rubber:
  proxy:
    set:
      dbInstance: userInstance
    config:
      dbInstances:
        userInstance:
          instanceName: userInstance
          type: SINGLE
          config:
            type: com.alibaba.druid.pool.DruidDataSource
            platform: mysql
            initialSize: 5
            minIdle: 5
            maxIdle: 5
            maxActive: 50
            maxWait: 60000
            timeBetweenEvictionRunsMillis: 60000
            minEvictableIdleTimeMillis: 300000
            validationQuery: SELECT 1 FROM DUAL
            testWhileIdle: true
            testOnBorrow: true
            testOnReturn: true
            poolPreparedStatements: true
            maxPoolPreparedStatementPerConnectionSize: 20
            # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
            filters: stat,wall,log4j
            # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
            connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
          connects:
            - url: jdbc:mysql://127.0.0.1:3306/user_db?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db
```

#### DAO层多实例设计
> 在nacos上的文件名称是 dev-rubber-config-mysql-userInstance.yml
```
rubber:
  proxy:
    set:
      dbInstance: userInstance
    config:
      dbInstances:
        userInstance:
          instanceName: userInstance
          type: CLUSTER
          config:
            type: com.alibaba.druid.pool.DruidDataSource
            platform: mysql
            initialSize: 5
            minIdle: 5
            maxIdle: 5
            maxActive: 50
            maxWait: 60000
            timeBetweenEvictionRunsMillis: 60000
            minEvictableIdleTimeMillis: 300000
            validationQuery: SELECT 1 FROM DUAL
            testWhileIdle: true
            testOnBorrow: true
            testOnReturn: true
            poolPreparedStatements: true
            maxPoolPreparedStatementPerConnectionSize: 20
            # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
            filters: stat,wall,log4j
            # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
            connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
          connects:
            - url: jdbc:mysql://127.0.0.1:3306/user_db_00?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db_00
            - url: jdbc:mysql://127.0.0.1:3306/user_db_01?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db_01
            - url: jdbc:mysql://127.0.0.1:3306/user_db_02?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db_02
            - url: jdbc:mysql://127.0.0.1:3306/user_db_03?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db_03
            - url: jdbc:mysql://127.0.0.1:3306/user_db_04?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db_04
            - url: jdbc:mysql://127.0.0.1:3306/user_db_05?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db_05
            - url: jdbc:mysql://127.0.0.1:3306/user_db_06?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db_06
            - url: jdbc:mysql://127.0.0.1:3306/user_db_07?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db_07
            - url: jdbc:mysql://127.0.0.1:3306/user_db_08?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db_08
            - url: jdbc:mysql://127.0.0.1:3306/user_db_09?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db_09
            - url: jdbc:mysql://127.0.0.1:3306/user_db?useUnicode=true;characterEncoding=utf-8
              username: root
              password: root
              driverClassName: com.mysql.cj.jdbc.Driver
              dbName: user_db
      table-config:
        - logicTableName: t_user_basic_info
          ruleType: TEN_DB_TEN_TABLE_USER
          dbName: user_db
          shardingColumn: uid
          tableNum: 10
        - logicTableName: t_global_uid_generator
          ruleType: SINGLE_DB_SINGLE_TABLE
          dbName: user_db
        - logicTableName: t_user_account_info
          ruleType: SINGLE_DB_SINGLE_TABLE
          dbName: user_db
```
