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
    , @NamedQuery(name = "Skill.findBySkillName", query = "SELECT s FROM Skill s WHERE s.skillName = :skillName")
    , @NamedQuery(name = "Skill.findBySkillImg", query = "SELECT s FROM Skill s WHERE s.skillImg = :skillImg")
    , @NamedQuery(name = "Skill.findByAbility", query = "SELECT s FROM Skill s WHERE s.ability = :ability")
    , @NamedQuery(name = "Skill.findByAffect", query = "SELECT s FROM Skill s WHERE s.affect = :affect")
    , @NamedQuery(name = "Skill.findByDamageType", query = "SELECT s FROM Skill s WHERE s.damageType = :damageType")
    , @NamedQuery(name = "Skill.findByDescription", query = "SELECT s FROM Skill s WHERE s.description = :description")
    , @NamedQuery(name = "Skill.findByCoolDown", query = "SELECT s FROM Skill s WHERE s.coolDown = :coolDown")
    , @NamedQuery(name = "Skill.findByMana", query = "SELECT s FROM Skill s WHERE s.mana = :mana")
    , @NamedQuery(name = "Skill.findByDamage", query = "SELECT s FROM Skill s WHERE s.damage = :damage")
    , @NamedQuery(name = "Skill.findByDuration", query = "SELECT s FROM Skill s WHERE s.duration = :duration")
    , @NamedQuery(name = "Skill.findByScepterNote", query = "SELECT s FROM Skill s WHERE s.scepterNote = :scepterNote")
    , @NamedQuery(name = "Skill.findByLinkenNote", query = "SELECT s FROM Skill s WHERE s.linkenNote = :linkenNote")
    , @NamedQuery(name = "Skill.findByBkbNote", query = "SELECT s FROM Skill s WHERE s.bkbNote = :bkbNote")
    , @NamedQuery(name = "Skill.findByMantaNote", query = "SELECT s FROM Skill s WHERE s.mantaNote = :mantaNote")
    , @NamedQuery(name = "Skill.findBySilverNote", query = "SELECT s FROM Skill s WHERE s.silverNote = :silverNote")})
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "SkillName")
    private String skillName;
    @Basic(optional = false)
    @Column(name = "SkillImg")
    private String skillImg;
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
    @Column(name = "ScepterNote")
    private String scepterNote;
    @Column(name = "LinkenNote")
    private String linkenNote;
    @Column(name = "BkbNote")
    private String bkbNote;
    @Column(name = "MantaNote")
    private String mantaNote;
    @Column(name = "SilverNote")
    private String silverNote;
    @JoinColumn(name = "HeroID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Hero heroID;

    public Skill() {
    }

    public Skill(Integer id) {
        this.id = id;
    }

    public Skill(Integer id, String skillName, String skillImg, String description) {
        this.id = id;
        this.skillName = skillName;
        this.skillImg = skillImg;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillImg() {
        return skillImg;
    }

    public void setSkillImg(String skillImg) {
        this.skillImg = skillImg;
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

    public String getScepterNote() {
        return scepterNote;
    }

    public void setScepterNote(String scepterNote) {
        this.scepterNote = scepterNote;
    }

    public String getLinkenNote() {
        return linkenNote;
    }

    public void setLinkenNote(String linkenNote) {
        this.linkenNote = linkenNote;
    }

    public String getBkbNote() {
        return bkbNote;
    }

    public void setBkbNote(String bkbNote) {
        this.bkbNote = bkbNote;
    }

    public String getMantaNote() {
        return mantaNote;
    }

    public void setMantaNote(String mantaNote) {
        this.mantaNote = mantaNote;
    }

    public String getSilverNote() {
        return silverNote;
    }

    public void setSilverNote(String silverNote) {
        this.silverNote = silverNote;
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
