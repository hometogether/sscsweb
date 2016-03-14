/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import utility.Match;

/**
 *
 * @author Andrea22
 */
@Stateless
@LocalBean
public class GestoreMatch {

    @EJB
    private ProfiloFacadeLocal profiloFacade;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Profilo> getMatchComune(Long comune, Long idprofilo) {
        List<Profilo> list = profiloFacade.getMatchComune(comune, idprofilo);
        return list;
    }
    
    public List<Profilo> getMatchProvincia(Long provincia, Long idprofilo) {
        List<Profilo> list = profiloFacade.getMatchProvincia(provincia, idprofilo);
        return list;
    }
    
    public List<Profilo> getMatchRegione(Long regione, Long idprofilo) {
        List<Profilo> list = profiloFacade.getMatchRegione (regione, idprofilo);
        return list;
    }
    
    public Match getMatch(Profilo user, Profilo profilo) {
        //utente è l'user loggato
        //profilo è uno degli utenti trovati nella ricerca
        
        Match match = new Match();
        int sizeuserlistainteressi = user.getInteressi().size();
        int sizeprofilolistainteressi = profilo.getInteressi().size();

        List<Interesse> intersezioneInteressi = intersectionInteressi(user.getInteressi(),profilo.getInteressi());

        match.setInteressi(intersezioneInteressi);
        float match_interessi= 0;
        if (Math.min(sizeuserlistainteressi, sizeprofilolistainteressi)!=0){
            match_interessi = (intersezioneInteressi.size())/(Math.min(sizeuserlistainteressi, sizeprofilolistainteressi));
        }
        match.setMatch_interessi(match_interessi);
        
        int sizeuserlistalingue = user.getLingue().size();
        int sizeprofilolistalingue = profilo.getLingue().size();

        
        List<Lingua> intersezioneLingue = intersectionLingue(user.getLingue(),profilo.getLingue());

        match.setLingue(intersezioneLingue);
        float match_lingue= 0;
        if (Math.min(sizeuserlistalingue, sizeprofilolistalingue)!=0){
            match_lingue = (intersezioneLingue.size())/(Math.min(sizeuserlistalingue, sizeprofilolistalingue));
        }
        match.setProfilo(profilo);
        match.setMatch_lingue(match_lingue);
        match.setMatch_totale((match_interessi+match_lingue)/2);
        
        
        
        
        return match;
        
        
    }
    
    public List<Interesse> intersectionInteressi(List<Interesse> list1, List<Interesse> list2) {
        List<Interesse> list = new ArrayList<Interesse>();

        for (Interesse t1 : list1) {
            for (Interesse t2 : list2) {
                if(Objects.equals(t1.getId(), t2.getId())) {
                    list.add(t1);
                }
            }
            
        }

        return list;
    }
    
    public List<Lingua> intersectionLingue(List<Lingua> list1, List<Lingua> list2) {
        List<Lingua> list = new ArrayList<Lingua>();

        for (Lingua t1 : list1) {
            for (Lingua t2 : list2) {
                if(Objects.equals(t1.getId(), t2.getId())) {
                    list.add(t1);
                }
            }
            
        }

        return list;
    }
       

}
