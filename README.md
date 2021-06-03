# springcloud-alibaba-example

### spring cloud alibaba 框架demo

* seata:1.4.2
* spring-cloud-alibaba-dependencies:2.2.0.RELEASE
* spring-cloud-dependencies:Hoxton.SR1
* spring-boot-starter-parent:2.2.4.RELEASE

```
.\startup.cmd -m standalone
.\seata-server.bat 
sh nacos-config.sh -h localhost -g SEATA_GROUP
.\mqnamesrv.cmd
```


### 参考文档
> https://blog.csdn.net/qq853632587/article/details/111644295

* seata配置文件放在seata-conf文件夹下
