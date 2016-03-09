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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Andrea22
 */
@Entity
public class Commento implements Serializable {
    @ManyToOne
    private Post post;
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data", nullable = false)
    private java.util.Date data;

    /**
     * Get the value of data
     *
     * @return the value of data
     */
    public java.util.Date getData() {
        return data;
    }

    /**
     * Set the value of data
     *
     * @param data new value of data
     */
    public void setData(java.util.Date data) {
        this.data = data;
    }
    @PrePersist
    protected void onCreate() {
        data = new java.util.Date();
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

    

}
