/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Du
 */
@Entity
@Table(name = "Hero")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hero", propOrder = {
    "id",
    "name",
    "img",
    "attribute",
    "lore",
    "badAgainstIDs",
    "goodAgainstIDs",
    "roleOfHeroList",
    "skillList"})
@NamedQueries({
    @NamedQuery(name = "Hero.findAll", query = "SELECT h FROM Hero h")
    , @NamedQuery(name = "Hero.findById", query = "SELECT h FROM Hero h WHERE h.id = :id")
    , @NamedQuery(name = "Hero.findByName", query = "SELECT h FROM Hero h WHERE h.name = :name")
    , @NamedQuery(name = "Hero.findByOldName", query = "SELECT h FROM Hero h WHERE h.oldName = :oldName")
    , @NamedQuery(name = "Hero.findByImg", query = "SELECT h FROM Hero h WHERE h.img = :img")
    , @NamedQuery(name = "Hero.findByAttribute", query = "SELECT h FROM Hero h WHERE h.attribute = :attribute")
    , @NamedQuery(name = "Hero.findByLore", query = "SELECT h FROM Hero h WHERE h.lore = :lore")
    , @NamedQuery(name = "Hero.findByBadAgainstIDs", query = "SELECT h FROM Hero h WHERE h.badAgainstIDs = :badAgainstIDs")
    , @NamedQuery(name = "Hero.findByGoodAgainstIDs", query = "SELECT h FROM Hero h WHERE h.goodAgainstIDs = :goodAgainstIDs")})
public class Hero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    @XmlElement(required = true)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    @XmlElement(required = true)
    private String name;
    @Column(name = "OldName")
    @XmlTransient
    private String oldName;
    @Basic(optional = false)
    @Column(name = "Img")
    @XmlElement(required = true)
    private String img;
    @Basic(optional = false)
    @Column(name = "Attribute")
    @XmlElement(required = true)
    private String attribute;
    @Basic(optional = false)
    @Column(name = "Lore")
    @XmlElement(required = true)
    private String lore;
    @Column(name = "BadAgainstIDs")
    @XmlElement
    private String badAgainstIDs;
    @Column(name = "GoodAgainstIDs")
    @XmlElement
    private String goodAgainstIDs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "heroID")
    @XmlElement
    private List<RoleOfHero> roleOfHeroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "heroID")
    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    @OrderBy("SkillOrder ASC")
    private List<Skill> skillList;

    public Hero() {
    }

    public Hero(Integer id) {
        this.id = id;
    }

    public Hero(Integer id, String name, String img, String attribute, String lore) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.attribute = attribute;
        this.lore = lore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getBadAgainstIDs() {
        return badAgainstIDs;
    }

    public void setBadAgainstIDs(String badAgainstIDs) {
        this.badAgainstIDs = badAgainstIDs;
    }

    public String getGoodAgainstIDs() {
        return goodAgainstIDs;
    }

    public void setGoodAgainstIDs(String goodAgainstIDs) {
        this.goodAgainstIDs = goodAgainstIDs;
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
