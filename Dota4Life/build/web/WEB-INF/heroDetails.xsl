<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="hero">
        <div class="hero-portrait">
            <img>
                <xsl:attribute name="src">Media<xsl:value-of select="img"/></xsl:attribute>
            </img>
        </div>
        <div class="hero-name">
            <div class="big-size">
                <xsl:value-of select="name"/>
            </div>
            <div class="small-size">
                <xsl:apply-templates select="roles/role"/>
            </div>
        </div>
        
        <div class="hero-story">
            <h1>Bio</h1>
            <p>
                <xsl:value-of select="lore" disable-output-escaping="yes"/>
            </p>
        </div>
        
        <div class="hero-skill-list">
            <h1 style="text-align: center;">Skills</h1>
            
            <xsl:apply-templates select="skills/skill">
                <xsl:sort select="skillOrder" order="ascending"/>
            </xsl:apply-templates>
        </div>
    </xsl:template>
    
    <xsl:template match="role">
        <span>
            <xsl:value-of select="roleName"/>
        </span>
    </xsl:template>
    
    <xsl:template match="skill">
        <div class="hero-skill">
            <div class="skill-name">
                <xsl:value-of select="name"/>
            </div>
            <div class="skill-type">
                <span class="left-side">Ability: <xsl:value-of select="ability"/></span>
                <xsl:if test="damageType">
                    <span class="right-side">Damage: <xsl:value-of select="damageType"/></span>
                </xsl:if>
            </div>
            <div class="clear"></div>
            
            <div class="skill-intro">
                <div class="skill-img">
                    <img>
                        <xsl:attribute name="src">Media<xsl:value-of select="img"/></xsl:attribute>
                    </img>
                </div>
                <div class="skill-content">
                    <xsl:value-of select="description" disable-output-escaping="yes"/>
                </div>
            </div>
            <div class="clear"></div>
            
            <xsl:if test="coolDown">
                <div class="skill-info">
                    <div class="info-name">Cooldown:</div>
                    <div class="info"><xsl:value-of select="coolDown"/></div>
                </div>
                <div class="clear"></div>
            </xsl:if>
            
            <xsl:if test="mana">
                <div class="skill-info">
                    <div class="info-name">Mana:</div>
                    <div class="info"><xsl:value-of select="mana"/></div>
                </div>
                <div class="clear"></div>
            </xsl:if>
            
            <xsl:if test="damage">
                <div class="skill-info">
                    <div class="info-name">Damage:</div>
                    <div class="info"><xsl:value-of select="damage"/></div>
                </div>
                <div class="clear"></div>
            </xsl:if>
            
            <xsl:if test="duration">
                <div class="skill-info">
                    <div class="info-name">Duration:</div>
                    <div class="info"><xsl:value-of select="duration"/></div>
                </div>
                <div class="clear"></div>
            </xsl:if>
            
            <xsl:if test="stunDuration">
                <div class="skill-info">
                    <div class="info-name">Stun Duration:</div>
                    <div class="info"><xsl:value-of select="stunDuration"/></div>
                </div>
                <div class="clear"></div>
            </xsl:if>
            
            <xsl:if test="silenceDuration">
                <div class="skill-info">
                    <div class="info-name">Silence Duration:</div>
                    <div class="info"><xsl:value-of select="silenceDuration"/></div>
                </div>
                <div class="clear"></div>
            </xsl:if>
            
        </div>
    </xsl:template>
    
</xsl:stylesheet>
