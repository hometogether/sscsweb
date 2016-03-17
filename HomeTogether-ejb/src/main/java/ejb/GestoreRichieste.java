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
public class GestoreRichieste {

    @EJB
    private RichiestaFacadeLocal richiestaFacade;
    @EJB
    private ProfiloFacadeLocal profiloFacade;
    @EJB
    private DiarioFacadeLocal diarioFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public int aggiungiRichiesta(Profilo profilo, Profilo destinatario) {
        //il tipo di ritorno è una Stringa, perché in alcuni casi dovremo tornare un Long (tipo non primitivo) e in altri un int.

        if (profilo == null || destinatario == null) {
            System.out.println("Errore: profilo o destinatario non validi");
            return -1;
        } else {

            Richiesta richiesta = new Richiesta();
            richiesta.setMittente(profilo);
            richiesta.setProfilo(destinatario);
            richiestaFacade.create(richiesta);
            System.out.println("dopo il create");

            destinatario.getRichieste().add(richiesta);
            profiloFacade.edit(destinatario);
            System.out.println("dopo l'edit");
            return 0;

        }

    }

    public int accettaRichiesta(Profilo profilo, Richiesta richiesta) {
        //il tipo di ritorno è una Stringa, perché in alcuni casi dovremo tornare un Long (tipo non primitivo) e in altri un int.
        /*interessi.remove(interesse);
         p.setInteressi(interessi);
         profiloFacade.edit(p);*/

        List<Richiesta> richieste = profilo.getRichieste();

        //dobbiamo controllare che l'interesse non sia già associato all'user
        boolean contain = richieste.contains(richiesta);
        if (contain) {
            richieste.remove(richiesta);
            profilo.setRichieste(richieste);
            profiloFacade.edit(profilo);
            
            //creazione diario
            
            Diario diario = new Diario();
            diario.getPartecipanti().add(profilo);
            diario.getPartecipanti().add(richiesta.getMittente());
            diario.setNome("Diario di"+profilo.getNome()+" e "+richiesta.getMittente().getNome());
            diarioFacade.create(diario);
            
           
            
            return 0;
        } else {
            System.out.println("Errore sconosciuto: si vuole eliminare una richiesta che l'utente non ha associato");
            return -1;
        }

    }

}
