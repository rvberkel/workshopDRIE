<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alle Facturen</title>
</head>
<body>
<p><a href="createfactuur">Add Factuur</a></p>
    <table border=1>
        <thead>
            <tr>
                <th>Factuur ID</th>
                <th>Factuurdatum</th>
                <th>Bestelling ID</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${facturen}" var="factuur">
                <tr>
                    <td><c:out value="${factuur.idFactuur}" /></td>
                    <fmt:formatDate var="fmtDate" value="${factuur.factuurDatum}" pattern="dd/MM/yyyy"/>
                    <td><c:out value="${fmtDate}" /></td>
                    <td><c:out value="${factuur.bestelling.idBestelling}" /></td>
                    <td><a href="showUpdateFactuur?idFactuur=${factuur.idFactuur}">Update</a></td>
                    <td><a href="deleteFactuur?idFactuur=${factuur.idFactuur}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="<c:url value="/showListKlant" />">Terug naar Klantenlijst (c:url manier)</a></br>
    <a href="showListKlant">Terug naar klantenlijst (normale manier)</a>
</body>
</html>