/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Andrea22
 */
@Entity
public class Richiesta implements Serializable {
    @ManyToOne
    private Profilo profilo;

    public Profilo getProfilo() {
        return profilo;
    }

    public void setProfilo(Profilo profilo) {
        this.profilo = profilo;
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
    private Profilo mittente;

    /**
     * Get the value of mittente
     *
     * @return the value of mittente
     */
    public Profilo getMittente() {
        return mittente;
    }

    /**
     * Set the value of mittente
     *
     * @param mittente new value of mittente
     */
    public void setMittente(Profilo mittente) {
        this.mittente = mittente;
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

}
