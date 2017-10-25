package DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "simplifiedhero")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "heroId",
    "heroName",
    "heroImg",
    "heroAttribute"})
public class SimplifiedHeroDTO {
    
    @XmlElement(required = true)
    private Integer heroId;
    @XmlElement(required = true)
    private String heroName;
    @XmlElement(required = true)
    private String heroImg;
    @XmlElement(required = true)
    private String heroAttribute;

    public SimplifiedHeroDTO() {
    }

    public SimplifiedHeroDTO(Integer heroId, String heroName, String heroImg, String heroAttribute) {
        this.heroId = heroId;
        this.heroName = heroName;
        this.heroImg = heroImg;
        this.heroAttribute = heroAttribute;
    }

    public Integer getHeroId() {
        return heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroImg() {
        return heroImg;
    }

    public void setHeroImg(String heroImg) {
        this.heroImg = heroImg;
    }

    public String getHeroAttribute() {
        return heroAttribute;
    }

    public void setHeroAttribute(String heroAttribute) {
        this.heroAttribute = heroAttribute;
    }
    
    
}
