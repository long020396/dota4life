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
@Table(name = "RoleOfHero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleOfHero.findAll", query = "SELECT r FROM RoleOfHero r")
    , @NamedQuery(name = "RoleOfHero.findById", query = "SELECT r FROM RoleOfHero r WHERE r.id = :id")})
public class RoleOfHero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "HeroID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Hero heroID;
    @JoinColumn(name = "RoleID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Role roleID;

    public RoleOfHero() {
    }

    public RoleOfHero(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hero getHeroID() {
        return heroID;
    }

    public void setHeroID(Hero heroID) {
        this.heroID = heroID;
    }

    public Role getRoleID() {
        return roleID;
    }

    public void setRoleID(Role roleID) {
        this.roleID = roleID;
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
        if (!(object instanceof RoleOfHero)) {
            return false;
        }
        RoleOfHero other = (RoleOfHero) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RoleOfHero[ id=" + id + " ]";
    }

}
