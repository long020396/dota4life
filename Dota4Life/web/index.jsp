<%-- 
    Document   : index
    Created on : Jul 21, 2017, 6:24:05 PM
    Author     : Du
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<c:set var="heroList" value="${requestScope.HERO_LIST}"/>
<c:import var="xslDoc" url="WEB-INF/heroList.xsl"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dota 4 Life</title>
    </head>
    <body>

        <x:transform xml="${heroList}" xslt="${xslDoc}" />

    </body>
</html>
