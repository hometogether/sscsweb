/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Andrea22
 */
@Entity
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    private Profilo user;

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public Profilo getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(Profilo user) {
        this.user = user;
    }

    private Date data;

    /**
     * Get the value of data
     *
     * @return the value of data
     */
    public Date getData() {
        return data;
    }

    /**
     * Set the value of data
     *
     * @param data new value of data
     */
    public void setData(Date data) {
        this.data = data;
    }

    private Time ora;

    /**
     * Get the value of ora
     *
     * @return the value of ora
     */
    public Time getOra() {
        return ora;
    }

    /**
     * Set the value of ora
     *
     * @param ora new value of ora
     */
    public void setOra(Time ora) {
        this.ora = ora;
    }

    private String testo;

    /**
     * Get the value of testo
     *
     * @return the value of testo
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Set the value of testo
     *
     * @param testo new value of testo
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    @OneToMany
    private List<Commento> commenti;

    /**
     * Get the value of commenti
     *
     * @return the value of commenti
     */
    public List<Commento> getCommenti() {
        return commenti;
    }

    /**
     * Set the value of commenti
     *
     * @param commenti new value of commenti
     */
    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }
    
    
    @OneToMany
    private List<Profilo> likes;

    /**
     * Get the value of likes
     *
     * @return the value of likes
     */
    public List<Profilo> getLikes() {
        return likes;
    }

    /**
     * Set the value of likes
     *
     * @param likes new value of likes
     */
    public void setLikes(List<Profilo> likes) {
        this.likes = likes;
    }

}
