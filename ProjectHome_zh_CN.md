[English](http://code.google.com/p/jplanet/) | [中文（简体）](http://code.google.com/p/jplanet/wiki/ProjectHome_zh_CN)


# 介绍 #

JPlanet 是一个新闻聚合器。它从公开的站点下载新闻种子并以最新的新闻在最前面的方式排序，把内容聚合成一个综合的种子。


# 编译 #

```
mvn package war:war
```


# 运行环境 #

  * JDK1.5 或更高
  * Servlet 2.4 或更高/JSP 2.1 或更高(比如 Apache Tomcat 5.5.23 或更高)


# 安装 #

  * 复制 jplanet.war 到 Tomcat 等的 webapps 目录下
  * 用浏览器浏览 `http://localhost:8080/jplanet/`


# 管理 #
  * 用浏览器打开 `http://localhost:8080/jplanet/admin/`
  * 用 [OpenID](http://openid.net.cn) 登录到后台


# 演示 #
  * [OpenID 中文新闻聚合](http://planet.openid.net.cn/)