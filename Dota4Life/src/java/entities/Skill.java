/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Du
 */
@Entity
@Table(name = "Skill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s")
    , @NamedQuery(name = "Skill.findById", query = "SELECT s FROM Skill s WHERE s.id = :id")
    , @NamedQuery(name = "Skill.findByName", query = "SELECT s FROM Skill s WHERE s.name = :name")
    , @NamedQuery(name = "Skill.findByImg", query = "SELECT s FROM Skill s WHERE s.img = :img")
    , @NamedQuery(name = "Skill.findBySkillOrder", query = "SELECT s FROM Skill s WHERE s.skillOrder = :skillOrder")
    , @NamedQuery(name = "Skill.findByAbility", query = "SELECT s FROM Skill s WHERE s.ability = :ability")
    , @NamedQuery(name = "Skill.findByAffect", query = "SELECT s FROM Skill s WHERE s.affect = :affect")
    , @NamedQuery(name = "Skill.findByDamageType", query = "SELECT s FROM Skill s WHERE s.damageType = :damageType")
    , @NamedQuery(name = "Skill.findByDescription", query = "SELECT s FROM Skill s WHERE s.description = :description")
    , @NamedQuery(name = "Skill.findByCoolDown", query = "SELECT s FROM Skill s WHERE s.coolDown = :coolDown")
    , @NamedQuery(name = "Skill.findByMana", query = "SELECT s FROM Skill s WHERE s.mana = :mana")
    , @NamedQuery(name = "Skill.findByDamage", query = "SELECT s FROM Skill s WHERE s.damage = :damage")
    , @NamedQuery(name = "Skill.findByDuration", query = "SELECT s FROM Skill s WHERE s.duration = :duration")
    , @NamedQuery(name = "Skill.findByStunDuration", query = "SELECT s FROM Skill s WHERE s.stunDuration = :stunDuration")
    , @NamedQuery(name = "Skill.findBySilenceDuration", query = "SELECT s FROM Skill s WHERE s.silenceDuration = :silenceDuration")})
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Img")
    private String img;
    @Basic(optional = false)
    @Column(name = "SkillOrder")
    private int skillOrder;
    @Column(name = "Ability")
    private String ability;
    @Column(name = "Affect")
    private String affect;
    @Column(name = "DamageType")
    private String damageType;
    @Basic(optional = false)
    @Column(name = "Description")
    private String description;
    @Column(name = "CoolDown")
    private String coolDown;
    @Column(name = "Mana")
    private String mana;
    @Column(name = "Damage")
    private String damage;
    @Column(name = "Duration")
    private String duration;
    @Column(name = "StunDuration")
    private String stunDuration;
    @Column(name = "SilenceDuration")
    private String silenceDuration;
    @JoinColumn(name = "HeroID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Hero heroID;

    public Skill() {
    }

    public Skill(Integer id) {
        this.id = id;
    }

    public Skill(Integer id, String name, String img, int skillOrder, String description) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.skillOrder = skillOrder;
        this.description = description;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSkillOrder() {
        return skillOrder;
    }

    public void setSkillOrder(int skillOrder) {
        this.skillOrder = skillOrder;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getAffect() {
        return affect;
    }

    public void setAffect(String affect) {
        this.affect = affect;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(String coolDown) {
        this.coolDown = coolDown;
    }

    public String getMana() {
        return mana;
    }

    public void setMana(String mana) {
        this.mana = mana;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStunDuration() {
        return stunDuration;
    }

    public void setStunDuration(String stunDuration) {
        this.stunDuration = stunDuration;
    }

    public String getSilenceDuration() {
        return silenceDuration;
    }

    public void setSilenceDuration(String silenceDuration) {
        this.silenceDuration = silenceDuration;
    }

    public Hero getHeroID() {
        return heroID;
    }

    public void setHeroID(Hero heroID) {
        this.heroID = heroID;
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
        if (!(object instanceof Skill)) {
            return false;
        }
        Skill other = (Skill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Skill[ id=" + id + " ]";
    }
    
}
