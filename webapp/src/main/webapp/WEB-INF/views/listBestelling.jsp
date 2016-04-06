<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Bestellingen</title>
</head>
<body>
    <p><a href="showCreateBestellingForm">Add Bestelling</a></p>
  
    <table border=1>
        <thead>
            <tr>
                <th>Klant ID</th>
                <th>Bestelling ID</th>
                <th>Artikelaantal</th>
                <th>Totaalprijs</th> 
                <th colspan=3>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${bestellingen}" var="bestelling">
                <tr>
                    <td><c:out value="${bestelling.klant.idKlant}"/></td>
                    <td><c:out value="${bestelling.idBestelling}"/></td>
                    
                    <td></td>
                    <td><c:forEach items="${bestelling.bestellingHasArtikelen}" var="bestellingHasArtikel">
                    	
                    </c:forEach> 
                    
                    </td>
                    
                    <td><a href="showUpdateBestellingForm?idBestelling=${bestelling.idBestelling}">Update</a></td>
                    <td><a href="deleteBestelling?idBestelling=${bestelling.idBestelling}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table> 
    <a href="<c:url value="/listbetaalwijze" />" class="button">Alle Betaalwijzes</a> |
    <a href="<c:url value="/listArtikelen" />" class="button">Alle Artikelen</a> |
    <a href="<c:url value="/listFacturen" />" class="button">Alle Facturen</a> <br>
    <p><a href="welkom" class="button">Terug naar het begin</a></p>
</body>
</html>