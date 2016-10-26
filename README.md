# MoonSmileFramework
Dropwizard +Guice + Redis + Jobs + Guava

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


