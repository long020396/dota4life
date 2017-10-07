/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Du
 */
@Entity
@Table(name = "Hero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hero.findAll", query = "SELECT h FROM Hero h"),
    @NamedQuery(name = "Hero.findById", query = "SELECT h FROM Hero h WHERE h.id = :id"),
    @NamedQuery(name = "Hero.findByHeroName", query = "SELECT h FROM Hero h WHERE h.heroName = :heroName"),
    @NamedQuery(name = "Hero.findByHeroImg", query = "SELECT h FROM Hero h WHERE h.heroImg = :heroImg"),
    @NamedQuery(name = "Hero.findByLore", query = "SELECT h FROM Hero h WHERE h.lore = :lore")})
public class Hero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "HeroName")
    private String heroName;
    @Basic(optional = false)
    @Column(name = "HeroImg")
    private String heroImg;
    @Basic(optional = false)
    @Column(name = "Lore")
    private String lore;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "heroID")
    private List<RoleOfHero> roleOfHeroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "heroID")
    private List<Skill> skillList;
    @JoinColumn(name = "AttributeID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Attribute attributeID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "strongWithHeroID")
    private List<Hero> strongWithHeroes;
    @JoinColumn(name = "StrongWithHeroID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Hero strongWithHeroID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "weakWithHeroID")
    private List<Hero> weakWithHeroes;
    @JoinColumn(name = "WeakWithHeroID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Hero weakWithHeroID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comboWithHeroID")
    private List<Hero> comboWithHeroes;
    @JoinColumn(name = "ComboWithHeroID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Hero comboWithHeroID;

    public Hero() {
        this.roleOfHeroList = new ArrayList<RoleOfHero>();
    }

    public Hero(Integer id) {
        this.id = id;
        this.roleOfHeroList = new ArrayList<RoleOfHero>();
    }

    public Hero(Integer id, String heroName, String heroImg, String lore) {
        this.id = id;
        this.heroName = heroName;
        this.heroImg = heroImg;
        this.lore = lore;
        this.roleOfHeroList = new ArrayList<RoleOfHero>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    @XmlTransient
    public List<RoleOfHero> getRoleOfHeroList() {
        return roleOfHeroList;
    }

    public void setRoleOfHeroList(List<RoleOfHero> roleOfHeroList) {
        this.roleOfHeroList = roleOfHeroList;
    }

    @XmlTransient
    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public Attribute getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(Attribute attributeID) {
        this.attributeID = attributeID;
    }

    @XmlTransient
    public List<Hero> getStrongWithHeroes() {
        return strongWithHeroes;
    }

    public void setStrongWithHeroes(List<Hero> strongWithHeroes) {
        this.strongWithHeroes = strongWithHeroes;
    }

    public Hero getStrongWithHeroID() {
        return strongWithHeroID;
    }

    public void setStrongWithHeroID(Hero strongWithHeroID) {
        this.strongWithHeroID = strongWithHeroID;
    }

    @XmlTransient
    public List<Hero> getWeakWithHeroes() {
        return weakWithHeroes;
    }

    public void setWeakWithHeroes(List<Hero> weakWithHeroes) {
        this.weakWithHeroes = weakWithHeroes;
    }

    public Hero getWeakWithHeroID() {
        return weakWithHeroID;
    }

    public void setWeakWithHeroID(Hero weakWithHeroID) {
        this.weakWithHeroID = weakWithHeroID;
    }

    @XmlTransient
    public List<Hero> getComboWithHeroes() {
        return comboWithHeroes;
    }

    public void setComboWithHeroes(List<Hero> comboWithHeroes) {
        this.comboWithHeroes = comboWithHeroes;
    }

    public Hero getComboWithHeroID() {
        return comboWithHeroID;
    }

    public void setComboWithHeroID(Hero comboWithHeroID) {
        this.comboWithHeroID = comboWithHeroID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hero)) {
            return false;
        }
        Hero other = (Hero) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Hero[ id=" + id + " ]";
    }
    
}
