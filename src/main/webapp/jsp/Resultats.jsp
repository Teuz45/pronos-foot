<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="HelloWorld.message" /></title>
</head>
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<body>
	<input type="hidden" id="dateProchainEvenement" value="<s:property value='dateProchainEvenement' />">
	<s:url action="resultats" var="urlResultats"/>

	<div class="navigation">
		<div class="competition-logo">
			<a class="logolink" title="UEFA EURO 2016" href='<s:property value="#urlResultats"/>'></a>
		</div>
		<div class="competition-aside">
			<div class="prochainEvenement">
<%-- 				<s:property value='libelleProchainEvenement' /> --%>
<!-- 				<br/> -->
				<div style="width:10%"><img src="<s:property value='prochainMatch.resultat.equipeDom.logo' />"></div>
				<div class="cdEquipe" style="width:30%"><s:property value='prochainMatch.resultat.equipeDom.cdEquipe' /></div>
				<div style="width:5%">v</div>
				<div class="cdEquipe" style="width:30%"><s:property value='prochainMatch.resultat.equipeExt.cdEquipe' /></div>
				<div style="width:10%"><img src="<s:property value='prochainMatch.resultat.equipeExt.logo' />"></div>
			</div>
			<hr/>
			<div class="countdown">
				<table>
					<tr id="temps">
						<td style="width: 51px" id="countdown_jours">999</td>
						<td> </td>
						<td style="width: 34px" id="countdown_heures">99</td>
						<td>:</td>
						<td style="width: 34px" id="countdown_minutes">99</td>
						<td>:</td>
						<td style="width: 34px" id="countdown_secondes">99</td>
					</tr>
					<tr id="legende">
						<td>Jours</td>
						<td></td>
						<td>Hrs</td>
						<td></td>
						<td>Mins</td>
						<td></td>
						<td>Secs</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="menu">Test Menu</div>
	</div>
</body>
</html>