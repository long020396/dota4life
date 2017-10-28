<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="heroes">
        <xsl:apply-templates select="hero"/>
    </xsl:template>
    
    <xsl:template match="hero">
        <div class="hero-selection">
            <xsl:attribute name="title">
                <xsl:value-of select="heroName"/>
            </xsl:attribute>
            <a>
                <xsl:attribute name="href">DispatchServlet?btnAction=viewHeroDetails&amp;heroId=<xsl:value-of select="heroId"/></xsl:attribute>
                <img class="hero-img">
                    <xsl:attribute name="src">Media<xsl:value-of select="heroImg"/></xsl:attribute>
                </img>
            </a>
        </div>
    </xsl:template>

</xsl:stylesheet>
