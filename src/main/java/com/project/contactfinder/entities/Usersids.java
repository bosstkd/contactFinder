/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "usersids", catalog = "contactgetter", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usersids.findAll", query = "SELECT u FROM Usersids u")
    , @NamedQuery(name = "Usersids.findByIdUser", query = "SELECT u FROM Usersids u WHERE u.idUser = :idUser")
    , @NamedQuery(name = "Usersids.findByPsw", query = "SELECT u FROM Usersids u WHERE u.psw = :psw")
    , @NamedQuery(name = "Usersids.findByActive", query = "SELECT u FROM Usersids u WHERE u.active = :active")})
public class Usersids implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "id_user", nullable = false, length = 60)
    private String idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "psw", nullable = false, length = 300)
    private String psw;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;

    public Usersids() {
    }

    public Usersids(String idUser) {
        this.idUser = idUser;
    }

    public Usersids(String idUser, String psw, boolean active) {
        this.idUser = idUser;
        this.psw = psw;
        this.active = active;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usersids)) {
            return false;
        }
        Usersids other = (Usersids) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.contactfinder.entities.Usersids[ idUser=" + idUser + " ]";
    }
    
}
