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
@Table(name = "words_key", catalog = "contactgetter", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WordsKey.findAll", query = "SELECT w FROM WordsKey w")
    , @NamedQuery(name = "WordsKey.findByIdRecherche", query = "SELECT w FROM WordsKey w WHERE w.idRecherche = :idRecherche")
    , @NamedQuery(name = "WordsKey.findByWord", query = "SELECT w FROM WordsKey w WHERE w.word = :word")})
public class WordsKey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "id_recherche", nullable = false, length = 100)
    private String idRecherche;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "word", nullable = false, length = 400)
    private String word;
    @JoinColumn(name = "id_recherche", referencedColumnName = "id_recherche", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Recherche recherche;

    public WordsKey() {
    }

    public WordsKey(String idRecherche) {
        this.idRecherche = idRecherche;
    }

    public WordsKey(String idRecherche, String word) {
        this.idRecherche = idRecherche;
        this.word = word;
    }

    public String getIdRecherche() {
        return idRecherche;
    }

    public void setIdRecherche(String idRecherche) {
        this.idRecherche = idRecherche;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Recherche getRecherche() {
        return recherche;
    }

    public void setRecherche(Recherche recherche) {
        this.recherche = recherche;
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
        if (!(object instanceof WordsKey)) {
            return false;
        }
        WordsKey other = (WordsKey) object;
        if ((this.idRecherche == null && other.idRecherche != null) || (this.idRecherche != null && !this.idRecherche.equals(other.idRecherche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project.contactfinder.entities.WordsKey[ idRecherche=" + idRecherche + " ]";
    }
    
}
