[English](http://code.google.com/p/jplanet/) | [中文（简体）](http://code.google.com/p/jplanet/wiki/ProjectHome_zh_CN)


# Introduction #

JPlanet is an awesome 'river of news' feed reader. It downloads news feeds published by web sites and aggregates their content together into a single combined feed, latest news first.


# Build #

```
mvn package war:war
```


# Requirement #

  * JDK1.5+
  * Servlet 2.4+/JSP 2.1+(Such as Apache Tomcat 5.5.23+)


# Installation #

  * Copy jplanet.war to Tomcat's %TOMCAT\_HOME%/webapps
  * Browse `http://localhost:8080/jplanet/`


# Administration #
  * Browse `http://localhost:8080/jplanet/admin/`
  * Login by [OpenID](http://openid.net)

# Demo #
  * [Planet OpenID China](http://planet.openid.net.cn/)