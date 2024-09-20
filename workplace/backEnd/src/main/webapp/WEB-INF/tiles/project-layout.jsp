<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<tiles:insertAttribute name="css"/>
	<tiles:insertAttribute name="project-css"/>
	<title>Project Management System - probada</title>
</head>
<body>
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="modal"/>
	<tiles:insertAttribute name="footer"/>
	<tiles:insertAttribute name="script"/>
	<tiles:insertAttribute name="project-script"/>
	<tiles:insertAttribute name="overlay"/>
	<tiles:insertAttribute name="project-overlay"/>
</body>
</html>