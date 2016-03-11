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
		<p>
			<s:url action="helloWorld" >Test Struts 2</s:url> <br/>
			<s:url action="/pronos-foot/HelloWorld.action" >Test Struts 2</s:url> <br/>
			<a href="/pronos-foot/HelloWorld.action">Test Struts 2 Hello World example!!</a>
		</p>
	</body>
</html>