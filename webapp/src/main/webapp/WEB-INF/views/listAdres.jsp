<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adressen van klant</title>
</head>
<body>
<p><a href="createAdres">Add Adres</a></p>
    <table border=1>
        <thead>
            <tr>
                <th>AdresID</th>
                <th>Straatnaam</th>
                <th>Huisnummer</th>
                <th>Postcode</th>
                <th>Woonplaats</th>
                <th>Adrestype</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${adressen}" var="adres" varStatus="adrestype">
                <tr>
                    <td><c:out value="${adres.idAdres}" /></td>
                    <td><c:out value="${adres.straatnaam}" /></td>
                    <td><c:out value="${adres.huisnummer}" /></td>
                    <td><c:out value="${adres.postcode}" /></td>
                    <td><c:out value="${adres.woonplaats}" /></td>
                    <td><c:out value="${adrestypen[adrestype.index].adres_type}" /></td>
                    <td><a href="update?idAdres=${adres.idAdres}">Update</a></td>
                    <td><a href="deleteAdres?idAdres=${adres.idAdres}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="showKlantDetails">Terug naar klantdetails</a></p>
    <p><a href="showListKlant">Terug naar klantenlijst</a></p>
</body>
</html>