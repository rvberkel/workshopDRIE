<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Klanten</title>
</head>
<body>
    <p><a href="showCreateKlantForm">Add Klant</a></p>
  
    <table border=1>
        <thead>
            <tr>
                <th>KlantID</th>
                <th>Voornaam</th>
                <th>Tussenvoegsel</th>
                <th>Achternaam</th>
                <th>Email</th>
                <th colspan=3>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${klanten}" var="klant">
                <tr>
                    <td><c:out value="${klant.idKlant}"/></td>
                    <td><c:out value="${klant.voornaam}"/></td>
                    <td><c:out value="${klant.tussenvoegsel}"/></td>
                    <td><c:out value="${klant.achternaam}"/></td>
                    <td><c:out value="${klant.email}"/></td>
                    <td><a href="checkKlantDetails?idKlant=${klant.idKlant}&voornaam=${klant.voornaam}&tussenvoegsel=${klant.tussenvoegsel}&achternaam=${klant.achternaam}">Check Klantdetails</a></td>
                    <td><a href="showUpdateKlantForm?idKlant=${klant.idKlant}">Update</a></td>
                    <td><a href="deleteKlant?idKlant=${klant.idKlant}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table> 
    <a href="<c:url value="/listbetaalwijze" />">Alle Betaalwijzes</a> |
    <a href="<c:url value="/listArtikelen" />">Alle Artikelen</a> |
    <a href="<c:url value="/listFacturen" />">Alle Facturen</a> <br>
    <p><a href="welkom">Terug naar het begin</a></p>
</body>
</html>