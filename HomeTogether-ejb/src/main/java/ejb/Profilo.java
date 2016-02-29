/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
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
public class Profilo implements Serializable {
    
    

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

    private String email;

    /**
     * Get the value of nome
     *
     * @return the value of nome
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of nome
     *
     * @param email new value of nome
     */
    public void setEmail(String email) {
        this.email = email;
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

    private String cognome;

    /**
     * Get the value of cognome
     *
     * @return the value of cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Set the value of cognome
     *
     * @param cognome new value of cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    private String sesso;

    /**
     * Get the value of sesso
     *
     * @return the value of sesso
     */
    public String getSesso() {
        return sesso;
    }

    /**
     * Set the value of sesso
     *
     * @param sesso new value of sesso
     */
    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    private String data_nascita;

    /**
     * Get the value of data_nascita
     *
     * @return the value of data_nascita
     */
    public String getData_nascita() {
        return data_nascita;
    }

    /**
     * Set the value of data_nascita
     *
     * @param data_nascita new value of data_nascita
     */
    public void setData_nascita(String data_nascita) {
        this.data_nascita = data_nascita;
    }

    private int tipo;

    /**
     * Get the value of tipo
     *
     * @return the value of tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Set the value of tipo
     *
     * @param tipo new value of tipo
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    private String foto_profilo="profile_img/default-profile.png";

    /**
     * Get the value of foto_profilo
     *
     * @return the value of foto_profilo
     */
    public String getFoto_profilo() {
        return foto_profilo;
    }

    /**
     * Set the value of foto_profilo
     *
     * @param foto_profilo new value of foto_profilo
     */
    public void setFoto_profilo(String foto_profilo) {
        this.foto_profilo = foto_profilo;
    }

    @ManyToOne
        private Comune comune;

    /**
     * Get the value of comune
     *
     * @return the value of comune
     */
    public Comune getComune() {
        return comune;
    }

    /**
     * Set the value of comune
     *
     * @param comune new value of comune
     */
    public void setComune(Comune comune) {
        this.comune = comune;
    }


    private String occupazione;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String getOccupazione() {
        return occupazione;
    }

    public void setOccupazione(String Occupazione) {
        this.occupazione = Occupazione;
    }

    private String formazione;

    /**
     * Get the value of formazione
     *
     * @return the value of formazione
     */
    public String getFormazione() {
        return formazione;
    }

    /**
     * Set the value of formazione
     *
     * @param formazione new value of formazione
     */
    public void setFormazione(String formazione) {
        this.formazione = formazione;
    }

    private String telefono;

    /**
     * Get the value of telefono
     *
     * @return the value of telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Set the value of telefono
     *
     * @param telefono new value of telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @ManyToMany
    private List<Interesse> interessi;

    /**
     * Get the value of interessi
     *
     * @return the value of interessi
     */
    public List<Interesse> getInteressi() {
        return interessi;
    }

    /**
     * Set the value of interessi
     *
     * @param interessi new value of interessi
     */
    public void setInteressi(List<Interesse> interessi) {
        this.interessi = interessi;
    }

    @OneToMany
    private List<Profilo> following;

    /**
     * Get the value of following
     *
     * @return the value of following
     */
    public List<Profilo> getFollowing() {
        return following;
    }

    /**
     * Set the value of following
     *
     * @param following new value of following
     */
    public void setFollowing(List<Profilo> following) {
        this.following = following;
    }
    
    @ManyToMany(mappedBy = "partecipanti")
    private List<Diario> diari;
    
    /**
     * Get the value of diari
     *
     * @return the value of diari
     */
    public List<Diario> getDiari() {
        return diari;
    }

    /**
     * Set the value of diari
     *
     * @param diari new value of diari
     */
    public void setDiari(List<Diario> diari) {
        this.diari = diari;
    }
    

}
