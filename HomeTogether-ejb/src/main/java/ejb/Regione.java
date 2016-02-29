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

/**
 *
 * @author Andrea22
 */
@Entity
public class Regione implements Serializable {

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

        private String capoluogo;

    /**
     * Get the value of capoluogo
     *
     * @return the value of capoluogo
     */
    public String getCapoluogo() {
        return capoluogo;
    }

    /**
     * Set the value of capoluogo
     *
     * @param capoluogo new value of capoluogo
     */
    public void setCapoluogo(String capoluogo) {
        this.capoluogo = capoluogo;
    }
    

    
    
}
