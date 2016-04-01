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

            destinatario.getRichieste().add(richiesta);
            profiloFacade.edit(destinatario);
            return 0;

        }

    }

    public int accettaRichiesta(Profilo profilo, Richiesta richiesta) {
        //il tipo di ritorno è una Stringa, perché in alcuni casi dovremo tornare un Long (tipo non primitivo) e in altri un int.
        /*interessi.remove(interesse);
         p.setInteressi(interessi);
         profiloFacade.edit(p);*/

        List<Richiesta> richieste = profilo.getRichieste();


        boolean contain = false;
        int pos = -1;
        for (int i=0; i<richieste.size() && contain == false;i++){
            if (richieste.get(i).getId() == richiesta.getId()){
                contain = true;
                pos = i;
            }
        }
        
        if (contain) {
            String nome = richiesta.getMittente().getNome();
            Profilo mittente =  richiesta.getMittente();
            
            richiestaFacade.remove(richiesta);
            richieste.remove(pos);
            profilo.setRichieste(richieste);
            profiloFacade.edit(profilo);

            //creazione diario
            
            Diario diario = new Diario();
            List<Profilo> partecipanti = new ArrayList<Profilo>();
            partecipanti.add(profilo);
            partecipanti.add(mittente);
            diario.setPartecipanti(partecipanti);
            diario.setNome("Diario di "+profilo.getNome()+" e "+ nome);
            //diario.setData_inizio(richiesta.get);
            diarioFacade.create(diario);
            
           
            
            return 0;
        } else {
            System.out.println("Errore sconosciuto: si vuole accettare una richiesta che l'utente non ha associato");
            return -1;
        }

    }
    
    public int rifiutaRichiesta(Profilo profilo, Richiesta richiesta) {
        //il tipo di ritorno è una Stringa, perché in alcuni casi dovremo tornare un Long (tipo non primitivo) e in altri un int.
        /*interessi.remove(interesse);
         p.setInteressi(interessi);
         profiloFacade.edit(p);*/

        List<Richiesta> richieste = profilo.getRichieste();

        //dobbiamo controllare che l'interesse non sia già associato all'user
        boolean contain = false;
        int pos = -1;
        for (int i=0; i<richieste.size() && contain == false;i++){
            if (richieste.get(i).getId() == richiesta.getId()){
                contain = true;
                pos = i;
            }
        }
        
        if (contain) {            
            richiestaFacade.remove(richiesta);
            richieste.remove(pos);
            profilo.setRichieste(richieste);
            profiloFacade.edit(profilo);
            
            return 0;
        } else {
            System.out.println("Errore sconosciuto: si vuole rifiutare una richiesta che l'utente non ha associato");
            return -1;
        }

    }

}
