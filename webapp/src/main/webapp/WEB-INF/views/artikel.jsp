<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Add new artikel</title>
		<link rel="stylesheet" type="text/css"
			  href="<c:url value="/resources/style.css" />">
	</head>
	<body>
	<!--     <form ID="createArtikel?idArtikel=${artikel.idArtikel}" method="POST" action="/webapp/createArtikel">
        ArtikelID : <input type="text" readonly="readonly" name="idArtikel"
            value="<c:out value="${artikel.idArtikel}" />" /> <br />
        Naam : <input type="text" name="artikelnaam"
            value="<c:out value="${artikel.artikelnaam}" />" /> <br />
        Prijs : <input type="text" name="artikelprijs"
            value="<c:out value="${artikel.artikelprijs}" />" /> <br />
        Nummer : <input type="text" name="artikelnummer"
            value="<c:out value="${artikel.artikelnummer}" />" /> <br />
        Omschrijving : <input type="text" name="artikelomschrijving"
			value="<c:out value="${artikel.artikelomschrijving}" />" /> <br /> <input
            type="submit" value="Submit" />
    </form>
-->

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
</body>
</html>