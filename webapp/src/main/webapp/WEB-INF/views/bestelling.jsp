<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bestelformulier</title>
</head>
<h1>Bestelformulier</h1>
<body>

	Vul de hoeveelheden in van de artikelen die je wil kopen.

	<sf:form ID="ArtikelTabel?${artikelen}" method="POST"
		action="/webapp/createBestelling" commandName="artikel">
		<table ID="artikelTabel" border=1>
			<thead>
				<tr>
					<th>Artikel ID</th>
					<th>Artikelnummer</th>
					<th>Stukprijs</th>
					<th>Aantal</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach items="${artikelen}" var="artikel" >
						<td><sf:hidden path="idArtikel" /></td>
						<td><sf:label path="artikelnummer" />
							<sf:errors path="artikelnummer" cssErrorClass="errors" /></td>
						<td><sf:label path="artikelprijs" />
							<sf:errors path="artikelprijs" /></td>
					</c:forEach>
				<!-- 	<td> <sf:input path="aantal"
						value="<sf:out value="aantal" />" />
					<br />  -->
				</tr>


			</tbody>
		</table>
		<input type="submit" value="Submit" />
	</sf:form>


	<!--
	<sf:form ID="createArtikel?idArtikel=${artikel.idArtikel}"
		method="POST" action="/webapp/createArtikel" commandName="artikel">
		   <sf:hidden path="idArtikel" />
 	Naam*: <sf:input path="artikelnaam" />
		<sf:errors path="artikelnaam" cssClass="errors" />
		<br />
   Prijs*: <sf:input path="artikelprijs" />
		<sf:errors path="artikelprijs" />
		<br />
   Nummer*: <sf:input path="artikelnummer" />
		<sf:errors path="artikelnummer" cssErrorClass="errors" />
		<br>
   Omschrijving: <sf:input path="artikelomschrijving" />
		<sf:errors path="artikelomschrijving" />
		<br />
   *verplicht veld
   <input type="submit" value="Submit" />
	</sf:form>
-->
</body>
</html>