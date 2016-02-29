/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.sql.Date;
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
public class Diario implements Serializable {

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

    private String nome;

    /**
     * Get the value of nome
     *
     * @return the value of nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set the value of nome
     *
     * @param nome new value of nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    private Date data_inizio;

    /**
     * Get the value of data_inizio
     *
     * @return the value of data_inizio
     */
    public Date getData_inizio() {
        return data_inizio;
    }

    /**
     * Set the value of data_inizio
     *
     * @param data_inizio new value of data_inizio
     */
    public void setData_inizio(Date data_inizio) {
        this.data_inizio = data_inizio;
    }

    private Date data_fine;

    /**
     * Get the value of data_fine
     *
     * @return the value of data_fine
     */
    public Date getData_fine() {
        return data_fine;
    }

    /**
     * Set the value of data_fine
     *
     * @param data_fine new value of data_fine
     */
    public void setData_fine(Date data_fine) {
        this.data_fine = data_fine;
    }

    @ManyToMany
    private List<Profilo> partecipanti;

    /**
     * Get the value of partecipanti
     *
     * @return the value of partecipanti
     */
    public List<Profilo> getPartecipanti() {
        return partecipanti;
    }

    /**
     * Set the value of partecipanti
     *
     * @param partecipanti new value of partecipanti
     */
    public void setPartecipanti(List<Profilo> partecipanti) {
        this.partecipanti = partecipanti;
    }

    @OneToMany
    private List<Post> post;

    /**
     * Get the value of post
     *
     * @return the value of post
     */
    public List<Post> getPost() {
        return post;
    }

    /**
     * Set the value of post
     *
     * @param post new value of post
     */
    public void setPost(List<Post> post) {
        this.post = post;
    }

}
