/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Andrea22
 */
@Entity
public class Comune implements Serializable {

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

    private String codiceCatastale;

    /**
     * Get the value of codiceCatastale
     *
     * @return the value of codiceCatastale
     */
    public String getCodiceCatastale() {
        return codiceCatastale;
    }

    /**
     * Set the value of codiceCatastale
     *
     * @param codiceCatastale new value of codiceCatastale
     */
    public void setCodiceCatastale(String codiceCatastale) {
        this.codiceCatastale = codiceCatastale;
    }

        private int cap;

    /**
     * Get the value of cap
     *
     * @return the value of cap
     */
    public int getCap() {
        return cap;
    }

    /**
     * Set the value of cap
     *
     * @param cap new value of cap
     */
    public void setCap(int cap) {
        this.cap = cap;
    }

    
    @ManyToOne
    private Provincia provincia;

    /**
     * Get the value of provincia
     *
     * @return the value of provincia
     */
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * Set the value of provincia
     *
     * @param provincia new value of provincia
     */
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

}
