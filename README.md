# icm-admin
系统基础管理服务：
用户、权限、菜单、基础管理服务

icm-admin
	├─src
	│  └─main
	│      ├─java
	│      │  └─com.lam.gz.admin			// Java代码
	│      │                  ├─common
	│	   │				  │	  ├─AdminBeanConfig.java 	// 该模块的application配置
	│	   │				  │   ├─AdminConstant.java		// 该模块的静态常量
	│	   │				  │   ├─AdminModule.java		// 该模块的Module
	│	   │				  │   ├─AdminOperation.java		// 该模块的操作
	│	   │				  │   └─AdminRedisConstant.java // 跟Redis相关的Key等常量值
	│      │                  ├─controller
	│      │                  ├─dao
	│      │                  ├─entity						// 实体类
	│      │                  ├─mapper						// mybatis xml SQL文件
	│      │                  ├─service
	│      │                  ├─vo							// 查询用的参数封装类
	│	   │				  └─AdminBootstrap 				// 启动类
	│      └─resources
	│          	├─application.yml			// 应用配置
	│ 	    	├─banner.txt				// 启动标志动画
	│ 	   		├─logback-spring.xml		// 系统日志文件配置
	│ 	   		│
	│ 	   		├─assembly					// 打包信息配置模板
	│ 	   		│  ├─assembly.xml
	│ 	   		│  └─bin					// 启动命令模板
	│ 	   		│     ├─dump.sh				
	│ 	    	│     ├─server.sh
	│ 	    	│     ├─start.bat
	│ 	    	│     ├─start.sh
	│ 	    	│     └─stop.sh
	│ 	    	│
	│			└─filters					// 环境配置信息
	│				dev.properties			
	│				prd.properties
	│				sit.properties
	│				uat.properties
	│
	├─target
	│   ├─assembly							// 打包信息配置
	│	│	└─bin							// 启动命令
	│   ├─classes							// 编译的class
	│   ├─admin.jar							// 打包的可运行jar，带lib依赖
	│   ├─admin.jar.original				// 原始jar，不带lib依赖
	│   └─icm-admin-dev.tar.gz				// 可运行包，包含启动命令
	└─pom.xml								// pom 文件