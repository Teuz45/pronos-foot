<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><s:text name="HelloWorld.message" /></title>
	</head>
	
	<body>
		<s:include value="header.jsp">
			<s:param name="currentmenu" value="'resultats'" />
		</s:include>
		
		<div class="content">
			<form action="validerResultats">
				<div class="content-left">
					<s:iterator value="listeResultats" status="resultStatus" var="result">	
						<div class="divResultat">
							<table class="tabResultat" align="center">				
								<tr>
									<td class="colInfos"></td>
									<td style="font-size: 11px" colspan="7"><s:date name="match.date" format="dd/MM/yyyy"/></td>
								</tr>
								<tr>
									<td class="colInfos"><strong># <s:property value="idMatch" /></strong></td>
									<td class="colEquipes colDom" rowspan="2"><s:property value="equipeDom.nom" /></td>
									<td class="colLogos" rowspan="2"><img src="<s:property value='equipeDom.logo' />"></td>
									<s:if test='utilisateur.profil!="ADMIN"'>
										<s:if test="scoreDom!=null && scoreExt!=null" >
											<td class="colScores <s:if test="scoreDom>scoreExt">victoire</s:if><s:elseif test="scoreDom==scoreExt">nul</s:elseif>" rowspan="2">
												<s:property value="scoreDom" />
											</td>
											<td class="colSeparateur" rowspan="2">
												-
											</td>
											<td class="colScores <s:if test="scoreExt>scoreDom">victoire</s:if><s:elseif test="scoreDom==scoreExt">nul</s:elseif>" rowspan="2">
												<s:property value="scoreExt" />
											</td>
										</s:if>
										<s:else>
											<td class="colHeure" rowspan="2" colspan="3"><s:date name="match.date" format="HH:mm"/></td>
										</s:else>
									</s:if>
									<s:else>
										<td class="colScores" rowspan="2">
											<input type="text" name="scoreDom" value="<s:property value="scoreDom"/>"/>
										</td>
										<td class="colSeparateur" rowspan="2">
											-
										</td>
										<td class="colScores" rowspan="2">
											<input type="text" name="scoreExt" value="<s:property value="scoreExt"/>"/>
										</td>
									</s:else>
									<td class="colLogos" rowspan="2"><img src="<s:property value='equipeExt.logo' />"></td>
									<td class="colEquipes colExt" rowspan="2"><s:property value="equipeExt.nom" /></td>
								</tr>
								<tr>
									<td class="colInfos"><s:property value="match.phase.libelle" /> <s:property value="match.groupe" /></td>
								</tr>
								<tr>
									<td class="colInfos"></td>
									<td style="font-size: 11px" colspan="7"><s:property value="match.stade.nomStade" /> (<s:property value="match.stade.ville" />)</td>
								</tr>
								<s:if test='utilisateur.profil=="ADMIN"'>
									<tr>
										<td class="colInfos"></td>
										<td style="text-align: center;" colspan="7"><input type="submit" value="OK" /></td>
									</tr>
								</s:if>
							</table>
						</div>
					</s:iterator>
				</div>
				<div class="content-right">
					<h2 class="portlet-title">Groupe A</h2>
					<table class="classement">
						<thead>
							<tr>
								<th class="title"><abbr class="standings-abbr" title="Rang">Rg</abbr> </th>
								<th class="title-equipe">Equipe</th>
								<th class="title"><abbr class="standings-abbr" title="Joué(s)">J</abbr></th>
								<th class="title"><abbr class="standings-abbr" title="Victoires">V</abbr></th>
								<th class="title"><abbr class="standings-abbr" title="Matches nuls">N</abbr></th>
								<th class="title"><abbr class="standings-abbr" title="Défaites">D</abbr></th>
								<th class="title"><abbr class="standings-abbr" title="Buts pour">bp</abbr></th>
								<th class="title"><abbr class="standings-abbr" title="Contre">bc</abbr></th>
								<th class="title"><abbr class="standings-abbr" title="Différence de buts">+/-</abbr></th>
								<th class="title"><abbr class="standings-abbr" title="Points">pts</abbr></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="classementGroupe" status="matchStatus" var="match">
								<tr class="<s:if test="#matchStatus.index %2 == 0">data-ligne-paire</s:if><s:else>data-ligne-impaire</s:else>">
									<td class="data">
										<s:property value="ranking"/>
									</td>
									<td class="data-equipe">
										<s:property value="nomEquipe"/>
									</td>
									<td class="data">
										<s:property value="nbMatchsJoues"/>
									</td>
									<td class="data">
										<s:property value="nbVictoires"/>
									</td>
									<td class="data">
										<s:property value="nbNuls"/>
									</td>
									<td class="data">
										<s:property value="nbDefaites"/>
									</td>
									<td class="data">
										<s:property value="nbButsPour"/>
									</td>
									<td class="data">
										<s:property value="nbButsContre"/>
									</td>
									<td class="data">
										<s:property value="differenceButs"/>
									</td>
									<td class="data-points">
										<s:property value="nbPoints"/>
									</td>
								</tr> 
								</s:iterator>
							</tbody>
						</table>
					
				</div>
				<div style="clear: both;"></div>
			</form>
		</div>
	</body>
</html>