<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<p>Struts 2 Tutorial</p>
		
		<s:url action="helloWorld" var="myurl"/>
		<a href='<s:property value="#myurl"/>'>Test Struts 2</a>
		
		<s:url action="resultats" var="urlResultats"/>
		<a href='<s:property value="#urlResultats"/>'>Résultats</a>
	</body>
</html>