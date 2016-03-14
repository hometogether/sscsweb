/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import ejb.Interesse;
import ejb.Lingua;
import ejb.Profilo;
import java.util.List;

/**
 *
 * @author Andrea22
 */
public class Match {
    
    private Profilo profilo;

    public Profilo getProfilo() {
        return profilo;
    }

    public void setProfilo(Profilo profilo) {
        this.profilo = profilo;
    }
    
    private List<Interesse> interessi;

    public List<Interesse> getInteressi() {
        return interessi;
    }

    public void setInteressi(List<Interesse> interessi) {
        this.interessi = interessi;
    }
    
    private List<Lingua> lingue;

    public List<Lingua> getLingue() {
        return lingue;
    }

    public void setLingue(List<Lingua> lingue) {
        this.lingue = lingue;
    }
    
    public float match_totale;

    public float getMatch_totale() {
        return match_totale;
    }

    public void setMatch_totale(float match_totale) {
        this.match_totale = match_totale;
    }
    
    private float match_lingue;

    public float getMatch_lingue() {
        return match_lingue;
    }

    public void setMatch_lingue(float match_lingue) {
        this.match_lingue = match_lingue;
    }
    
    private float match_interessi;

    public float getMatch_interessi() {
        return match_interessi;
    }

    public void setMatch_interessi(float match_interessi) {
        this.match_interessi = match_interessi;
    }
    
}
    