
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Betaalwijze pagina</title>
</head>
<body>
	<h1>Betaalwijze niet gevonden</h1>
</br>

</br>
	<a href="<c:url value="/createbetaalwijze" />">Create Betaalwijze</a> 
</br>
	<a href="<c:url value="/listbetaalwijze" />">Alle Betaalwijzes</a>
	<form ID="findBetaalwijze" method="POST" action="/webapp/findBetaalwijze">
    	Zoek betaalwijze op id: <input type="text" name="idBetaalwijze" value="${betaalwijze.idBetaalwijze}" />
    	<input type="submit" value="Zoeken" />
    </form>

</body>
</html>