## SpringDoc简介
官方网址  https://springdoc.org/

SpringDoc 是一个开源项目，用于自动化生成和维护 Spring Boot 应用程序的 OpenAPI 3 规范文档。OpenAPI（以前称为Swagger）是一个规范和完整的框架，用于描述、生产、消费和可视化 RESTful Web 服务的API。SpringDoc 利用 OpenAPI 3 规范，为开发者提供了一种简便的方式来生成和展示他们的 API 文档。

**概览**
![spring-doc-design.png](img%2Fspring-doc-design.png)

## 基本概念

| 名词概念        | 说明                                                                                                                                                                                                        |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| OpenAPI     | 是一个组织（OpenAPI Initiative），他们指定了一个如何描述HTTP API的规范（OpenAPI Specification）。既然是规范，那么谁想实现都可以，只要符合规范即可。                                                                                                         |
| Swagger     | 它是SmartBear这个公司的一个开源项目，里面提供了一系列工具，包括著名的 swagger-ui。swagger是早于OpenApi的，某一天swagger将自己的API设计贡献给了OpenApi，然后由其标准化了。                                                                                            |
| Springfox   | 是Spring生态的一个开源库，是Swagger与OpenApi规范的具体实现。我们使用它就可以在spring中生成API文档。以前基本上是行业标准，目前最新版本可以支持 Swagger2, Swagger3 以及 OpenAPI3 三种格式。但是其从 2020年7月14号就不再更新了，不支持springboot3，所以业界都在不断的转向我们今天要谈论的另一个库Springdoc，新项目就不要用了。 |
| SpringDoc   | SpringDoc是Spring官方推荐的API，支持SpringBoot3, 简化了Rest API 接口文档生成和维护的复杂度                                                                                                                                         |

## SpringBoot集成SpringDoc
### 1. 安装依赖
对于spring-boot 和 swagger-ui集成SpringDoc直接安装依赖，不需要其他的任何配置。
![spring-doc-jicheng.png](img%2Fspring-doc-jicheng.png)

```xml
    <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-api</artifactId>
      <version>2.3.0</version>
   </dependency>
```
### 2、查看api接口信息
项目启动之后，直接访问 http://localhost:8080/v3/api-docs 即可，注意端口号是项目启动的端口号

### 3、导入文档工具
SpringDoc支持openapi规范，所以市面上大部分框架均支持导入。例如: apifox apipost postman torna knife4j 等，根据对应工具的文档接入即可


下面以ruoyi-vue-plus项目为例导入到apifox的工具里面
1. 下载apifox客户端，创建一个项目
![spring-doc-create-project.png](img%2Fspring-doc-create-project.png)

2. 导入数据
![spring-doc-impot.png](img%2Fspring-doc-impot.png)
![spring-doc-import.png](img%2Fspring-doc-import.png)

## Swagger升级SpringDoc指南

|  swagger | springdoc  |  javadoc |
|---|---|---|
| @Api(name = "xxx") | @Tag(name = "xxx")  | java类注释第一行  |
| @Api(description= "xxx") | @Tag(description= "xxx")  | java类注释  |
| @ApiOperation  | @Operation  | java方法注释  |
| @ApiIgnore  | @Hidden  | 无  |
| @ApiParam  | @Parameter  | java方法@param参数注释  |
| @ApiImplicitParam  | @Parameter  | java方法@param参数注释  |
| @ApiImplicitParams  | @Parameters  | 多个@param参数注释  |
| @ApiModel                    | @Schema  | java实体类注释  |
| @ApiModelProperty                    | @Schema  | java属性注释  |
| @ApiModelProperty(hidden = true)                   | @Schema(accessMode = READ_ONLY)  | 无  |
| @ApiResponse                    | @ApiResponse  | java方法@return返回值注释  |

		
		
		
		
		
		
		
		
		
		
		
		
