package DTO;

import entities.Hero;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "heroes")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "heroList", propOrder = "hero")
public class HeroListDTO implements Serializable{
    
    @XmlElement(required = true)
    private List<SimplifiedHeroDTO> hero;

    public HeroListDTO() {
    }

    public HeroListDTO(List<SimplifiedHeroDTO> hero) {
        if (this.hero == null) {
            this.hero = new ArrayList<SimplifiedHeroDTO>();
        }
        this.hero = hero;
    }

    public List<SimplifiedHeroDTO> getHero() {
        return hero;
    }

    public void setHero(List<SimplifiedHeroDTO> hero) {
        this.hero = hero;
    }
    
}
