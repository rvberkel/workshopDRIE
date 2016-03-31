<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
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
    <!--   
     <form ID="createBetaalwijze?idBetaalwijze=${betaalwijze.idBetaalwijze}" method="POST" action="/webapp/createbetaalwijze">
        BetaalwijzeID : <input type="text" readonly="readonly" name="idBetaalwijze"
            value="<c:out value="${betaalwijze.idBetaalwijze}" />" /> <br/> 
        Betaalwijze (voer een getal in!) : <input type="text" name="betaalwijze"
            value="<c:out value="${betaalwijze.betaalwijze}" />" />
             <br/>
			<input type="submit" value="Submit" />
    </form>  
     -->
      
    <sf:form ID="createBetaalwijze?idBetaalwijze=${betaalwijze.idBetaalwijze}" method="POST" 
    action="/webapp/createbetaalwijze" commandName = "betaalwijze">
	
		<sf:hidden path="idBetaalwijze"/>
    	Betaalwijze (voer een getal in!) : <sf:input path="betaalwijze"/> 
    						<!-- <sf:errors path="betaalwijze"/> <br/> -->		
    	<input type="submit" value="Submit" />
     </sf:form>
    
    <p><a href="showListKlant">Terug naar klantenlijst</a></p>
    <p><a href="listbetaalwijze">Terug naar betaalwijzen</a></p>
</body>
</html>