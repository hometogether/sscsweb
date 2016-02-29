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
    public String aggiungiInteresse(Long idProfilo, String nomeinteresse) {
        //il tipo di ritorno è una Stringa, perché in alcuni casi dovremo tornare un Long (tipo non primitivo) e in altri un int.
        System.out.println("entro in agggiungiInteresse");
        System.out.println("nome interesse:" + nomeinteresse);
        if (idProfilo == null || nomeinteresse == null) {
            System.out.println("Errore: id Profilo = 0 o nome interesse non valido");
            System.out.println("idProfilo=" + idProfilo);
            System.out.println("nomeinteresse=" + nomeinteresse);

            return "-1";
        } else {
            Interesse interesse = interesseFacade.getInteresse(nomeinteresse);
            System.out.println("supero il get Interesse. interesse = " + interesse);

            if (interesse == null) {
                //l'interesse non esiste nel DB. Dobbiamo crearlo.
                interesse = new Interesse();

                interesse.setNome(nomeinteresse);

                interesseFacade.create(interesse);
                System.out.println("supero il create");

            }

            Profilo p = profiloFacade.getProfilo(idProfilo);
            System.out.println("supero il get profilo. Profilo = " + p);

            List<Interesse> interessi = p.getInteressi();

            //dobbiamo controllare che l'interesse non sia già associato all'user
            boolean contain = interessi.contains(interesse);

            if (contain == false) {
                interessi.add(interesse);
                p.setInteressi(interessi);
                profiloFacade.edit(p);
                System.out.println("supero l'edit");

                return ""+interesse.getId();
            } else {
                System.out.println("non devo fare niente, l'interesse è già associato!");
                return "-1"; //da cambiare...
            }
        }

    }
    
    public int rimuoviInteresse(Long idProfilo, Long idInteresse) {
        System.out.println("entro in eliminaInteresse");
        System.out.println("id interesse:" + idInteresse);

        if (idProfilo == null || idInteresse == null) {
            System.out.println("Errore: id Profilo = 0 o nome interesse non valido");
            System.out.println("idProfilo=" + idProfilo);
            System.out.println("idinteresse=" + idInteresse);

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
