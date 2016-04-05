<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bestelformulier updaten</title>
</head>
<h1>Bestelformulier updaten</h1>
<body>

	Verander de hoeveelheden der artikelen.

	<sf:form ID="ArtikelTabel?${bestellingHasArtikelen}" method="POST"
		action="/webapp/updateBestelling" commandName="bestellingHasArtikel">
		Klant ID: <input type="text" name="klantId" readonly="readonly" value="${klantId}"/><br>
		Bestelling ID: <input type="text" name="idBestelling" readonly="readonly" value="${bestelling.idBestelling}"/><br>
		
		<table ID="bestellingHasArtikelTabel" border=1>
			<thead>
				<tr>
					<th>Naam</th> <th>Artikel ID</th>	<th>Artikelnummer</th>	<th>Stukprijs</th>	<th>Aantal</th>
				</tr>
			</thead>
			<tbody>
				
					<c:forEach items="${bestellingHasArtikelen}" var="bha" >
					<input type="hidden" name="idBHA" readonly="readonly" value="${bha.idBestelArtikel}"/>
				<tr>
					
					<td><input type="text" name="artikelnaam" readonly="readonly" value="${bha.artikel.artikelnaam}"/></td>
					<td><input type="text" name="idArtikel" readonly="readonly" value="${bha.artikel.idArtikel}"/></td>
					<td><input type="text" name="artikelnummer" readonly="readonly" value="${bha.artikel.artikelnummer}"/></td>
					<td><input type="text" name="artikelprijs" readonly="readonly" value="${bha.artikel.artikelprijs}"/></td>
					<td><input type="text" name="aantal" value="${bha.aantal}"/></td>
				</tr>
					
					</c:forEach>
			</tbody>
		</table>		
		TODO: linkje voeg artikel toe  <br>
		<input type="submit" value="Buy! Buy! Buy!" />
	</sf:form>
	
	<a href="<c:url value="/listBestellingen" />" class="button">Terug naar Bestellingen</a>

</body>
</html>