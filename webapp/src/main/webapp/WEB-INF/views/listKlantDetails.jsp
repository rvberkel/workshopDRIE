<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Klantdetails</title>
</head>
<body>
	<h1><c:out value="${detailsVanKlant}" /></h1>
	
	<table border=1>
       	<thead>
            <tr>
                <th><a href="checkAccounts">Accounts</a></th>
                <th><a href="checkAdressen">Adressen</a></th>
                <th><a href="checkBestellingen">Bestellingen</a></th>
                <th><a href="checkBetalingen">Betalingen</a></th>
            </tr>
        </thead>
    </table>
    <p><a href="showListKlant">Terug naar klantenlijst</a></p>
<body>
</body>
</html>