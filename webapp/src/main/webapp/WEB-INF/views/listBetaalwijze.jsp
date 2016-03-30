<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Betaalwijzen/Payment methods</title>
</head>
<body>
    <p><a href="createbetaalwijze">Add Betaalwijze</a></p>
    <table border=1>
        <thead>
            <tr>
                <th>Betaalwijze ID</th>
                <th>Betaalwijze omschrijving</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${betaalwijzen}" var="betaalwijze">
                <tr>
                    <td><c:out value="${betaalwijze.idBetaalwijze}" /></td>
                    <td><c:out value="${betaalwijze.betaalwijze}" /></td>
                    <td><a href="updateBetaalwijze?idBetaalwijze=${betaalwijze.idBetaalwijze}">Update</a></td>
                    <td><a href="deleteBetaalwijze?idBetaalwijze=${betaalwijze.idBetaalwijze}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form ID="findBetaalwijze" method="POST" action="/webapp/findBetaalwijze">
    	Zoek betaalwijze op id: <input type="text" name="idBetaalwijze" value="${betaalwijze.idBetaalwijze}" />
    	<input type="submit" value="Zoeken" />
    </form>
    </br>
    <a href="<c:url value="/showListKlant" />">Terug naar Klantenlijst (c:url manier)</a></br>
    <a href="showListKlant">Terug naar klantenlijst (normale manier)</a>
</body>
</html>