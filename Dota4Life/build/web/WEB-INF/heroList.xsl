<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="heroes">
        <div class="select_hero">
            <div class="hero_attribute strength">
                <img src="Media/General/overviewicon_str.png" class="float_left" /> 
                <div class="float_left">Strength</div>
            </div>
            <div class="clear"></div>
            <div class="img_content">

                <xsl:apply-templates select="hero[heroAttribute='Strength']">
                    <xsl:sort select="heroName" order="ascending"/>
                </xsl:apply-templates> 

            </div>
        </div>

        <div class="clear"></div>
        
        <div class="select_hero">
            <div class="hero_attribute strength">
                <img src="Media/General/overviewicon_agi.png" class="float_left" /> 
                <div class="float_left">Agility</div>
            </div>
            <div class="clear"></div>
            <div class="img_content">

                <xsl:apply-templates select="hero[heroAttribute='Agility']">
                    <xsl:sort select="heroName" order="ascending"/>
                </xsl:apply-templates> 

            </div>
        </div>

        <div class="clear"></div>
        
        <div class="select_hero">
            <div class="hero_attribute strength">
                <img src="Media/General/overviewicon_int.png" class="float_left" /> 
                <div class="float_left">Intelligence</div>
            </div>
            <div class="clear"></div>
            <div class="img_content">

                <xsl:apply-templates select="hero[heroAttribute='Intelligence']">
                    <xsl:sort select="heroName" order="ascending"/>
                </xsl:apply-templates> 

            </div>
        </div>
    </xsl:template>
    
    <xsl:template match="hero">
        <div class="hero-selection">
            <xsl:attribute name="title">
                <xsl:value-of select="heroName"/>
            </xsl:attribute>
            <a href="#">
                <xsl:attribute name="href">DispatchServlet?btnAction=viewHeroDetails&amp;heroId=<xsl:value-of select="heroId"/></xsl:attribute>
                <img class="hero-img">
                    <xsl:attribute name="src">Media<xsl:value-of select="heroImg"/></xsl:attribute>
                </img>
            </a>
        </div>
    </xsl:template>

</xsl:stylesheet>
