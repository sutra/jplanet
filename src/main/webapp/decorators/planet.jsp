<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/taglibs.jsp" %>
<%@ include file="/includes/sitemesh-taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title default="${planet.title}" /></title>
<meta http-equiv="Content-Type"	content="application/xhtml+xml; charset=UTF-8" />
<meta content="all" name="robots" />

<meta name="keywords" content="${planet.keywords}" />
<meta name="description" content="${planet.description}" />

<jsp:include flush="true" page="/custom/favicon.jsp" />

<jsp:include flush="true" page="/includes/feed.jsp" />

<jsp:include flush="true" page="/custom/styles.jsp" />

<decorator:head />

</head>
<body>
<div>

	<jsp:include flush="true" page="/custom/header.jsp" />

	<div id="body">
		<decorator:body />
	</div>

	<jsp:include flush="true" page="/includes/sidebar.jsp" />

	<jsp:include flush="true" page="/custom/footer.jsp" />

</div>
</body>
</html>
