<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Artikelen</title>
</head>
<body>
    <p><a href="createArtikel">Add Artikel</a></p>
  <h1><c:out value="${welcomeMessage}" /></h1>
    <table border=1>
        <thead>
            <tr>
                <th>ArtikelId</th>
                <th>Naam</th>
                <th>Prijs</th>
                <th>Nummer</th>
                <th>Omschrijving</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${artikelen}" var="artikel">
                <tr>
                    <td><c:out value="${artikel.idArtikel}" /></td>
                    <td><c:out value="${artikel.artikelnaam}" /></td>
                    <td><c:out value="${artikel.artikelprijs}" /></td>
                    <td><c:out value="${artikel.artikelnummer}" /></td>
                    <td><c:out value="${artikel.artikelomschrijving}" /></td>
                    <td><a href="updateArtikel?idArtikel=${artikel.idArtikel}">Update</a></td>
                    <td><a href="deleteArtikel?getId=${artikel.idArtikel}">Delete</a></td><!--<c:out value="${artikel.idArtikel}"/>-->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
<p><a href="showListKlant">Terug naar klantenlijst</a></p>
</html>