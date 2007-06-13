<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp" %>
<%@ include file="/includes/sitemesh-taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
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
			<div><a href="../"><fmt:message key="aggregator" /></a> | <a href="logout"><fmt:message key="logout" /></a></div>
			<div style="margin: 10px 20px 0px 20px">
				<page:applyDecorator page="/admin/planet.jsp" name="planet" />
			</div>
		</div>
	</body>
</html>
