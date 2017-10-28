<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="heroes">
        <table style="border: 2px solid red;">
                <xsl:apply-templates select="hero[heroAttribute='Strength']">
                    <xsl:sort select="heroName" order="ascending"/>
                </xsl:apply-templates>
        </table>
    </xsl:template>
    
    <xsl:template match="hero">
        <tr>
            <td>
                <xsl:value-of select="heroName"/>
            </td>
            <td>
                <img>
                    <xsl:attribute name="src">Media<xsl:value-of select="heroImg"/></xsl:attribute>
                </img>
            </td>
        </tr>
    </xsl:template>

</xsl:stylesheet>
