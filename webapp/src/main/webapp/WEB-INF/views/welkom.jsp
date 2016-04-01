<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WELKOM</title>
<h1><c:out value="${welcomeMessage}" /></h1>
</head>
<h1>Welkom bij de epische bestel App</h1>
<h2>Maak hieronder een keuze</h2>
    <a href="<c:url value="/listbetaalwijze" />">Alle Betaalwijzes</a> |
    <a href="<c:url value="/listArtikelen" />">Alle Artikelen</a> |
    <a href="<c:url value="/listFacturen" />">Alle Facturen</a>|
    <a href="<c:url value="/showListKlant" />">Alle Klanten</a> |
    <a href="<c:url value="/listBestellingen" />">Alle Bestellingen</a> |

<body>

</body>
</html>