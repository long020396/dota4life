<%-- 
    Document   : heroInfo
    Created on : Aug 21, 2017, 12:37:56 PM
    Author     : Du
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<c:set var="heroName" value="${requestScope.HERO_NAME}"/>
<c:set var="heroDetails" value="${requestScope.HERO_DETAILS}"/>
<c:set var="badAgainstHeroes" value="${requestScope.BAD_AGAINST_HERO_LIST}"/>
<c:set var="goodAgainstHeroes" value="${requestScope.GOOD_AGAINST_HERO_LIST}"/>
<c:import var="heroDetailsXSL" url="WEB-INF/heroDetails.xsl"/>
<c:import var="referenceHeroesXSL" url="WEB-INF/referenceHeroes.xsl"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/general.css" rel="stylesheet" type="text/css"/>
        <link href="css/heroInfo.css" rel="stylesheet" type="text/css"/>
        <title>${heroName}</title>
    </head>
    <body>
        <div class="container">
            <c:import url="header.html" charEncoding="UTF-8" />

            <div class="hero-info">
                <x:transform xml="${heroDetails}" xslt="${heroDetailsXSL}" />

                <div class="reference-heroes">
                    <div class="bad-against">
                        <h1>Bad against heroes</h1>

                        <x:transform xml="${badAgainstHeroes}" xslt="${referenceHeroesXSL}" />
                    </div>
                    <div class="good-against">
                        <h1>Good against heroes</h1>

                        <x:transform xml="${goodAgainstHeroes}" xslt="${referenceHeroesXSL}" />
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
