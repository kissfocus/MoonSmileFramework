# MoonSmileFramework
======================
Dropwizard +Guice + Redis + Jobs + Guava

### About

[Dropwizard](http://dropwizard.io/) 1.0.2 [guice](https://github.com/google/guice) (4.1.0) integration.

This is an REST service Framework using Dropwizard.
It can help to build with [Gradle](https://gradle.org/) and auto build fat jar.
Resource Auto Inject and Configure the DB WITH READ WRITE split.

Features:
* [Guice](https://github.com/google/guice) injector created on run phase
* [Hikaricp](https://github.com/brettwooldridge/HikariCP) DataBase Pool Manager,Read From Config File Or From URL,Read Write Split
* [Redis](http://redis.io/) redis config support ,to cache data with redis.
* [Quartz](http://www.quartz-scheduler.org/) Schedule Job Support,help to do jobs in framework
* [JWTAuth](https://jwt.io/) Json Web Token Auth Support,Custome Your Auth System
* [Guava](https://github.com/google/guava) Google Help Libs with cache and events
* [JDBI](http://www.jdbi.org/) DB Util Method to operate with mysql
* [OkHttp](http://square.github.io/okhttp/) Use OKHTTP to Deal With HTTP(GET OR POST)

### moonsmile.core
It is the core frame wrapper with dropwizard and It has many useful method to help develop.
* `FrameBridge` Use this to get the config or enviroment
* `DBPoolCenter` Use this to manager the db pool and read write
* `DBExecutor` Use this to help you to execute sql with mysql
* `EncryptUtils` For Safety to save db password
* `PBKDF2ForPasswordHash` Encrypt the user password to save into database
* `RedisPoolCenter` To Connect redis use pool manager
* `HttpManager` To send get or post method with http
* `GeneralFormator` Convert JSON to Object OR Protobuf to json

### moonsmile.cache
Example Project
It is compile with the moonsmile.core,And You can see how to use core method and service
* Schedule Job with Quartz
* Schedule Job To Save Into Redis
* Read Data From Redis

### moonsmile.rest
Example Project
It is compile with the moonsmile.core,And You can see how to use core method and service
* Database Operate With DBCenter
* Simple Say HelloWorld

### moonsmile.setting
Example Project
It is compile with the moonsmile.core,And You can see how to use core method and service
* Database Center Manager,Read DB Config From Url Or File

### Setup

#### The Project Use IDEA AND GRADLE
1. General the idea project
`gradlew idea`
2. Build The Project to download libs
`gradlew build`

#### How to run the project
1. Run/Debug Configuration,Select the Application
2. Setting the vmoptions And Other MainClass,Module
`-Ddropwizard.config=src/main/resources/cache_config.yml`
3. Run
![image](https://github.com/kissfocus/MoonSmileFramework/raw/master/doc/project-setting.png)

In Resources Folder,It has an yml config file,need to config something like redis ,port
Open the WebSite,`http://localhost:8081/pageobject/hello`


# 基本信息
这是一个很小的示例程序，主要是用来学习使用dropwizard框架。
它也可以作为一个项目模板，可以快速的作为一个新的项目并运行。
集成了最常用的框架,适用于`Restfull` 架构风格`Web Service`接口开发。
项目使用最灵活的构建工具-`gradle`，加入了常用的gradle插件。

# 功能信息

本项目是基于Gradle进行构建，并搭建了适合大型构建项目的子模块系统，环境变量，自动合并不同环境的配置文件。
主要特点如下：
1、基于Gradle构建，自动完成打包和单元测试。
2、分离配置文件，基于环境变量进行切换。
3、子模块系统，每个子模块单独构建，并继承相应的配置。
4、Protobuf自动构建
5、JSON和Protobuf协议输出
6、基于Dropwizard框架
7、集成capsule，自动生成jar包
8、集成远程调试功能

# 主要框架信息

## Jetty
-利用Jetty作为webapp和后端服务的容器
-作为Dropwizard的承载程序，使的运行和调试非常容易

## Dropwizard
- 创建RESTful (JSON)服务，使用jersery和jackson，protobuf,并包含健康检查
- 通过JDBI创建持久层逻辑

## [Gradle](http://www.gradle.org)
- 基于Gradle的构建，使用Groovy语法，集成了最新的3.0版本

## hikaricp
- 目前最好用，性能最高的数据库连接池组件

## protobuf
- 基于谷歌的二进制传输协议，提高接口传输，减少流量

## guava
- 谷歌的一个很有用的类库

## JWT(JSON WEB TOKEN) AUTH
- 集成了JWT，通过Oauth的请求头进行验证

### 下载项目后如何构建
本项目是使用idea，如何是使用eclipse，第二条命令要改改
1. 打开命令行，进入到项目目录./gradlew build`
2. 生成相应的依赖和项目文件./gradlew idea`

### 如何基于gradle运行该程序
1. 打开命令行，进入到项目目录./gradlew run`
2. 设置VMOptions：-Ddropwizard.config=src/main/resources/metaengine_config.yml
每个项目不同，根据具体的设置

第一次打开该项目构建时，会进行相应的gradle文件下载，以及类库下载。

License
-------

Copyright (c) 2016 KissFocus

This library is licensed under the Apache License, Version 2.0.

See http://www.apache.org/licenses/LICENSE-2.0.html or the LICENSE file in this repository for the full license text.ICENSE-2.0.html or the LICENSE file in this repository for the full license text.