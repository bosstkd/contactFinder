/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "users", catalog = "contactgetter", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByIdUser", query = "SELECT u FROM Users u WHERE u.idUser = :idUser")
    , @NamedQuery(name = "Users.findByMail", query = "SELECT u FROM Users u WHERE u.mail = :mail")
    , @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone")
    , @NamedQuery(name = "Users.findByPays", query = "SELECT u FROM Users u WHERE u.pays = :pays")
    , @NamedQuery(name = "Users.findByEntreprise", query = "SELECT u FROM Users u WHERE u.entreprise = :entreprise")
    , @NamedQuery(name = "Users.findByDates", query = "SELECT u FROM Users u WHERE u.dates = :dates")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "id_user", nullable = false, length = 60)
    private String idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "mail", nullable = false, length = 150)
    private String mail;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "pays", nullable = false, length = 40)
    private String pays;
    @Size(max = 200)
    @Column(name = "entreprise", length = 200)
    private String entreprise;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dates", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dates;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
    private Usersids usersids;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<Recherche> rechercheCollection;

    public Users() {
    }

    public Users(String idUser) {
        this.idUser = idUser;
    }

    public Users(String idUser, String mail, String phone, String pays, Date dates) {
        this.idUser = idUser;
        this.mail = mail;
        this.phone = phone;
        this.pays = pays;
        this.dates = dates;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Usersids getUsersids() {
        return usersids;
    }

    public void setUsersids(Usersids usersids) {
        this.usersids = usersids;
    }

    @XmlTransient
    public Collection<Recherche> getRechercheCollection() {
        return rechercheCollection;
    }

    public void setRechercheCollection(Collection<Recherche> rechercheCollection) {
        this.rechercheCollection = rechercheCollection;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.contactfinder.entities.Users[ idUser=" + idUser + " ]";
    }
    
}
