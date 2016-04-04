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
					<th>Naam</th> <th>Artikel ID</th>	<th>Artikelnummer</th>	<th>Stukprijs</th>	<th>Aantal</th>
				</tr>
			</thead>
			<tbody>
				
					<c:forEach items="${artikelen}" var="artikel" >
				<tr>	
					<td><input type="text" name="artikelnaam" readonly="readonly" value="${artikel.artikelnaam}"/></td>
					<td><input type="text" name="idArtikel" readonly="readonly" value="${artikel.idArtikel}"/></td>
					<td><input type="text" name="artikelnummer" readonly="readonly" value="${artikel.artikelnummer}"/></td>
					<td><input type="text" name="artikelprijs" readonly="readonly" value="${artikel.artikelprijs}"/></td>
					<td><input type="text" name="aantal" value="${aantal}"/></td>
					
					<!--  
						<td><sf:input path="artikelnummer" /> <sf:errors path="artikelnummer" cssErrorClass="errors" /></td>
						<td><sf:input path="artikelprijs" /> <sf:errors path="artikelprijs" /></td>					-->
					</tr>
					
					</c:forEach>
			</tbody>
		</table>		
		Klant ID: <input type="text" name="klantId" value="${klantId}"/><br>
		
		<input type="submit" value="Buy! Buy! Buy!" />
	</sf:form>
	
	<a href="<c:url value="/listBestellingen" />">Terug naar Bestellingen</a>


</body>
</html>