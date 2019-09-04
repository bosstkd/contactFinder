/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "recherche", catalog = "contactgetter", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recherche.findAll", query = "SELECT r FROM Recherche r")
    , @NamedQuery(name = "Recherche.findByIdRecherche", query = "SELECT r FROM Recherche r WHERE r.idRecherche = :idRecherche")
    , @NamedQuery(name = "Recherche.findByDesignation", query = "SELECT r FROM Recherche r WHERE r.designation = :designation")
    , @NamedQuery(name = "Recherche.findByMail", query = "SELECT r FROM Recherche r WHERE r.mail = :mail")
    , @NamedQuery(name = "Recherche.findByPhone", query = "SELECT r FROM Recherche r WHERE r.phone = :phone")
    , @NamedQuery(name = "Recherche.findByUrl", query = "SELECT r FROM Recherche r WHERE r.url = :url")
    , @NamedQuery(name = "Recherche.findByDates", query = "SELECT r FROM Recherche r WHERE r.dates = :dates")})
public class Recherche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "id_recherche", nullable = false, length = 100)
    private String idRecherche;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "designation", nullable = false, length = 500)
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "mail", nullable = false, length = 200)
    private String mail;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "phone", length = 50)
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "url", nullable = false, length = 1000)
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dates", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dates;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "recherche")
    private WordsKey wordsKey;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(optional = false)
    private Users idUser;

    public Recherche() {
    }

    public Recherche(String idRecherche) {
        this.idRecherche = idRecherche;
    }

    public Recherche(String idRecherche, String designation, String mail, String url, Date dates) {
        this.idRecherche = idRecherche;
        this.designation = designation;
        this.mail = mail;
        this.url = url;
        this.dates = dates;
    }

    public String getIdRecherche() {
        return idRecherche;
    }

    public void setIdRecherche(String idRecherche) {
        this.idRecherche = idRecherche;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public WordsKey getWordsKey() {
        return wordsKey;
    }

    public void setWordsKey(WordsKey wordsKey) {
        this.wordsKey = wordsKey;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecherche != null ? idRecherche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recherche)) {
            return false;
        }
        Recherche other = (Recherche) object;
        if ((this.idRecherche == null && other.idRecherche != null) || (this.idRecherche != null && !this.idRecherche.equals(other.idRecherche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.contactfinder.entities.Recherche[ idRecherche=" + idRecherche + " ]";
    }
    
}
