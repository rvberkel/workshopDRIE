<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
    <%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new adres</title>
</head>
<body>
<!--
    <form ID="createOrUpdateAdres?idAdres=${adres.idAdres}" method="POST" action="/webapp/createOrUpdateAdres">
        AdresID : <input type="text" readonly="readonly" name="idAdres"
            value="<c:out value="${adres.idAdres}" />" /> <br /> 
        Straatnaam : <input type="text" name="straatnaam"
            value="<c:out value="${adres.straatnaam}" />" /> <br /> 
        Huisnummer : <input type="text" name="huisnummer"
            value="<c:out value="${adres.huisnummer}" />" /> <br /> 
        Postcode : <input type="text" name="postcode"
            value="<c:out value="${adres.postcode}" />" /> <br /> 
        Woonplaats : <input type="text" name="woonplaats"
			value="<c:out value="${adres.woonplaats}" />" /> <br />
		Adrestype : <table border=1>
        				<thead>
            				<tr>
                				<th>keuzegetal</th>
                				<th>adrestypeomschrijving</th>
            				</tr>
        				</thead>
        				<tbody>
        					<c:forEach items="${adrestypen}" var="adrestype">
        						<tr>
        							<td><c:out value="${adrestype.idAdres_type}"/></td>
                    				<td><c:out value="${adrestype.adres_type}"/></td>
                    			</tr>
                    		</c:forEach>
        				</tbody>
    				</table>
    				<input type="text" name="idAdrestype" 
    					value="<c:out value="${adrestype.idAdres_type}" />" /> <br />

		<input type="submit" value="Submit" />
    </form>
-->

<sf:form ID="createOrUpdateAdres?idAdres=${adres.idAdres}" method="POST" action="/webapp/createOrUpdateAdres" commandName="adres">
		<sf:hidden path="idAdres" />
 	Straatnaam*: <sf:input path="straatnaam" />
		<sf:errors path="straatnaam" /><br />
   	Huisnummer*: <sf:input path="huisnummer" />
		<sf:errors path="huisnummer" /><br />
   	Postcode*: <sf:input path="postcode" />
		<sf:errors path="postcode" /><br>
   	Woonplaats*: <sf:input path="woonplaats" />
		<sf:errors path="woonplaats" /><br />
	Adrestype*: <table border=1>
        				<thead>
            				<tr>
                				<th>keuzegetal</th>
                				<th>adrestypeomschrijving</th>
            				</tr>
        				</thead>
        				<tbody>
        					<c:forEach items="${adrestypen}" var="adrestype">
        						<tr>
        							<td><c:out value="${adrestype.idAdres_type}"/></td>
                    				<td><c:out value="${adrestype.adres_type}"/></td>
                    			</tr>
                    		</c:forEach>
        				</tbody>
    			</table>
    				<input type="text" name="idAdrestype" value="<c:out value="${adrestype.idAdres_type}"/>"/><br/>
   	*verplicht veld
   <input type="submit" value="Submit" />
</sf:form>
</body>
</html>