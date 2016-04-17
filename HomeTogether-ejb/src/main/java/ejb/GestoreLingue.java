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
public class GestoreLingue {

    @EJB
    private LinguaFacadeLocal linguaFacade;
    @EJB
    private ProfiloFacadeLocal profiloFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Lingua> creaListaLingue() {
        
        List<Lingua> list = linguaFacade.getLingue();
        return list;
    }
    
    public Long aggiungiLingua(Long idProfilo, String nomelingua) {
        if (idProfilo == null || nomelingua == null) {
            System.out.println("Errore: id Profilo = 0 o nome lingua non valido");
            return null;
        } else {
            Lingua lingua = linguaFacade.getLingua(nomelingua);
            if (lingua == null) {
                //la lingua non esiste...
                return null;
            }

            Profilo p = profiloFacade.getProfilo(idProfilo);
            List<Lingua> lingue = p.getLingue();

            //dobbiamo controllare che la lingua non sia già associata all'user
            boolean contain = lingue.contains(lingua);

            if (contain == false) {
                lingue.add(lingua);
                p.setLingue(lingue);
                EntityManager em = profiloFacade.getEntityManager();
                em.persist(lingua);
                em.flush();
                profiloFacade.edit(p);

                return lingua.getId();
            } else {
                System.out.println("non devo fare niente, la lingua è già associata!");
                return null; //da cambiare...
            }
        }

    }
    
    public int rimuoviLingua(Long idProfilo, Long idLingua) {
        if (idProfilo == null || idLingua == null) {
            System.out.println("Errore: id Profilo = 0 o id lingua non valido");

            return -1;
        } else {
            Lingua lingua = linguaFacade.getLingua(idLingua);

            if (lingua == null) {
                System.out.println("Errore sconosciuto: si vuole eliminare una lingua inesistente");
                return -1;
            } 

            Profilo p = profiloFacade.getProfilo(idProfilo);

            List<Lingua> lingue = p.getLingue();
            
            //dobbiamo controllare che la lingua non sia già associato all'user
            boolean contain = lingue.contains(lingua);

            if (contain) {
                lingue.remove(lingua);
                p.setLingue(lingue);
                profiloFacade.edit(p);

                return 0;
            } else {
                System.out.println("Errore sconosciuto: si vuole eliminare una lingua che l'utente non ha associato");
                return -1; 
            }
        }

    }

}
