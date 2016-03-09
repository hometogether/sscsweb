/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Andrea22
 */
@Entity
public class Post implements Serializable {
    @ManyToOne
    private Diario diario;
    public Diario getDiario() {
        return diario;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
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

    @OneToOne
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
    @PrePersist
    protected void onCreate() {
        data = new Date();
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

    @OneToMany(mappedBy = "post",cascade=CascadeType.REMOVE)

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
