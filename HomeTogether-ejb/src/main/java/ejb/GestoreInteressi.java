/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author Andrea22
 */
@Stateless
@LocalBean
public class GestoreInteressi {

    @EJB
    private InteresseFacadeLocal interesseFacade;
    @EJB
    private ProfiloFacadeLocal profiloFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Long aggiungiInteresse(Long idProfilo, String nomeinteresse) {
        if (idProfilo == null || nomeinteresse == null) {
            System.out.println("Errore: id Profilo = 0 o nome interesse non valido");
            return null;
        } else {
            Interesse interesse = interesseFacade.getInteresse(nomeinteresse);
            if (interesse == null) {
                //l'interesse non esiste nel DB. Dobbiamo crearlo.
                interesse = new Interesse();
                interesse.setNome(nomeinteresse);
                interesseFacade.create(interesse);

            }

            Profilo p = profiloFacade.getProfilo(idProfilo);
            List<Interesse> interessi = p.getInteressi();

            //dobbiamo controllare che l'interesse non sia già associato all'user
            boolean contain = interessi.contains(interesse);

            if (contain == false) {
                interessi.add(interesse);
                p.setInteressi(interessi);
                EntityManager em = profiloFacade.getEntityManager();
                em.persist(interesse);
                em.flush();
                profiloFacade.edit(p);

                return interesse.getId();
            } else {
                System.out.println("non devo fare niente, l'interesse è già associato!");
                return null; 
            }
        }

    }
    
    public int rimuoviInteresse(Long idProfilo, Long idInteresse) {

        if (idProfilo == null || idInteresse == null) {
            System.out.println("Errore: id Profilo = 0 o nome interesse non valido");

            return -1;
        } else {
            Interesse interesse = interesseFacade.getInteresse(idInteresse);
            System.out.println("supero il get Interesse. interesse = " + interesse);

            if (interesse == null) {
                System.out.println("Errore sconosciuto: si vuole eliminare un interesse inesistente");
                return -1;
            } 

            Profilo p = profiloFacade.getProfilo(idProfilo);
            System.out.println("supero il get profilo. Profilo = " + p);

            List<Interesse> interessi = p.getInteressi();
            
            //dobbiamo controllare che l'interesse non sia già associato all'user
            boolean contain = interessi.contains(interesse);

            if (contain) {
                interessi.remove(interesse);
                p.setInteressi(interessi);
                profiloFacade.edit(p);
                System.out.println("supero l'edit");

                return 0;
            } else {
                System.out.println("Errore sconosciuto: si vuole eliminare un interesse che l'utente non ha associato");
                return -1; 
            }
        }

    }

}
