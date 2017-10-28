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
        <link href="css/general.css" rel="stylesheet" type="text/css"/>
        <title>Dota4Life</title>
    </head>
    <body>
        <div class="container">
            <c:import url="header.html" charEncoding="UTF-8" />

            <div class="content-frame">
                <div class="search">
                    <label>Hero name</label>
                    <input type="text" name="txtHeroName"/>
                </div>

                <x:transform xml="${heroList}" xslt="${xslDoc}" />
            </div>
        </div>


    </body>
</html>
