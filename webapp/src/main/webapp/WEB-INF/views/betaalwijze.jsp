<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Add new betaalwijze</title>
</head>
<body>
<table border=1>
        <thead>
            <tr>
                <th>keuzegetal</th>
                <th>Betaalwijze omschrijving</th>
            </tr>
        </thead>
        <tbody>
                <tr><td>0</td><td>Contant</td></tr>
                <tr><td>1</td><td>Pin</td></tr>
                <tr><td>2</td><td>Ideaal</td></tr>
                <tr><td>3</td><td>Kredietkaart</td></tr>
	            <tr><td>4</td><td>Paypal</td></tr>
	            <tr><td>5</td><td>Afterpay</td></tr>
	            <tr><td>6</td><td>Natura</td></tr>
        </tbody>
    </table>
	Maak veld BetaalID leeg als je niet update!!
    <form ID="createBetaalwijze?idBetaalwijze=${betaalwijze.idBetaalwijze}" method="POST" action="/webapp/createBetaalwijze">
        BetaalwijzeID : <input type="text" name="idBetaalwijze"
            value="<c:out value="${betaalwijze.idBetaalwijze}" />" /> <br /> 
        Betaalwijze (voer getal in!) : <input type="text" name="betaalwijze"
            value="<c:out value="${betaalwijze.betaalwijze}" />" /> <br />
			<input type="submit" value="Submit" />
    </form>
</body>
</html>