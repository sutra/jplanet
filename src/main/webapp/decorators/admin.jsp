<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>JPlanet - <decorator:title default="Welcome!" />
		</title>
		<meta http-equiv="Content-Type"
			content="application/xhtml-xml; charset=UTF-8" />
		<meta content="all" name="robots" />
		<link rel="stylesheet" href="${ctx}/generic.css" type="text/css" />
		<link rel="stylesheet" href="${ctx}/layout.css" type="text/css" />
		<link rel="stylesheet" href="${ctx}/planet.css" type="text/css" />
		<decorator:head />
	</head>
	<body>
		<div>
			<div><a href="logout"><fmt:message key="logout" /></a></div>
			<div style="margin: 10px 20px 0px 20px">
				<decorator:body />
			</div>
		</div>
	</body>
</html>
