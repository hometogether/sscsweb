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
public class GestoreUtenti {

    @EJB
    private UtenteGoogleFacadeLocal utenteGoogleFacade;
    @EJB
    private UtenteAppFacadeLocal utenteAppFacade;
    @EJB
    private UtenteFacebookFacadeLocal utenteFacebookFacade;
    @EJB
    private ProfiloFacadeLocal profiloFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public int aggiungiUser(String nome, String cognome, String password, String r_password, String email, String r_email, String data_nascita, String sesso, Comune comune) {
        System.out.println("entro in aggiungi user, con la persistance spettacolari!!!wowowow");
        if (nome == null || cognome == null || password == null || r_password == null || email == null
                || r_email == null || data_nascita == null || sesso == null) {
            System.out.println("Non sono stati compilati tutti i campi!");
            return -1;
        } else if (!password.equals(r_password)) {
            System.out.println("Le password non corrispondono!");
            return -2;
        } else if (!email.equals(r_email)) {
            System.out.println("Le email non corrispondono!");
            return -3;
        } else {
            int emailesistente = profiloFacade.checkEmailEsistente(email);
            if (emailesistente != 0) {
                System.out.println("Email già presente nel DB!");
                return -4;
            }
        }
        Profilo p = new Profilo();
        p.setNome(nome);
        p.setCognome(cognome);
        p.setEmail(email);
        p.setData_nascita(data_nascita);
        p.setSesso(sesso);
        p.setTipo(0);
        p.setComune(comune);
        //p.setFoto_profilo("");
        profiloFacade.create(p);
        Profilo profilo = profiloFacade.getProfilo(email);
        // Long idProfilo = profilo.getId();
        UtenteApp u = new UtenteApp();
        u.setPassword(password);
        u.setProfilo(profilo);
        u.setEmail(email);

       // UtenteApp u = new UtenteApp();
        /*u.setNome(nome);
         u.setCognome(cognome);*/
        //u.setIdUtente("0");
        // utenteAppFacade.create(u);
        //System.out.println("la data è:"+data_nascita);
        // String[] data = data_nascita.split("/");
        utenteAppFacade.create(u);

        return 0;
    }

    public int aggiungiUserGoogle(String nome, String cognome, String idGoogle, String email, String r_email, String data_nascita, String sesso, String foto, Comune comune) {
        System.out.println("entro in aggiungi user, con la persistance spettacolari!!!wowowow");
        if (nome == null || cognome == null || idGoogle == null || email == null
                || r_email == null || data_nascita == null || sesso == null) {
            System.out.println("Non sono stati compilati tutti i campi!");
            return -1;
        } else if (!email.equals(r_email)) {
            System.out.println("Le email non corrispondono!");
            return -3;
        } else {
            int emailesistente = profiloFacade.checkEmailEsistente(email);
            if (emailesistente != 0) {
                System.out.println("Email già presente nel DB!");
                return -4;
            }
        }
        Profilo p = new Profilo();
        p.setNome(nome);
        p.setCognome(cognome);
        p.setEmail(email);
        p.setData_nascita(data_nascita);
        p.setSesso(sesso);
        p.setTipo(0);
        p.setComune(comune);
        p.setFoto_profilo(foto);
        profiloFacade.create(p);
        Profilo profilo = profiloFacade.getProfilo(email);
        //Long idProfilo = profilo.getId();
        UtenteGoogle u = new UtenteGoogle();
        u.setIdGoogle(idGoogle);
        u.setProfilo(profilo);
        u.setEmail(email);

       // UtenteApp u = new UtenteApp();
        /*u.setNome(nome);
         u.setCognome(cognome);*/
        //u.setIdUtente("0");
        // utenteAppFacade.create(u);
        //System.out.println("la data è:"+data_nascita);
        // String[] data = data_nascita.split("/");
        utenteGoogleFacade.create(u);

        return 0;
    }

    public int aggiungiUserFacebook(String nome, String cognome, String idFacebook, String email, String r_email, String data_nascita, String sesso, String foto, Comune comune) {

        if (nome == null || cognome == null || idFacebook == null || email == null
                || r_email == null || data_nascita == null || sesso == null || foto == null) {
            System.out.println("Non sono stati compilati tutti i campi!");
            return -1;
        } else if (!email.equals(r_email)) {
            System.out.println("Le email non corrispondono!");
            return -3;
        } else {
            int emailesistente = profiloFacade.checkEmailEsistente(email);
            if (emailesistente != 0) {
                System.out.println("Email già presente nel DB!");
                return -4;
            }
        }
        Profilo p = new Profilo();
        p.setNome(nome);
        p.setCognome(cognome);
        p.setEmail(email);
        p.setData_nascita(data_nascita);
        p.setSesso(sesso);
        p.setTipo(0);
        p.setComune(comune);
        p.setFoto_profilo(foto);
        profiloFacade.create(p);
        Profilo profilo = profiloFacade.getProfilo(email);
        //Long idProfilo = profilo.getId();
        UtenteFacebook u = new UtenteFacebook();
        u.setIdFacebook(idFacebook);
        u.setProfilo(profilo);
        u.setEmail(email);

       // UtenteApp u = new UtenteApp();
        /*u.setNome(nome);
         u.setCognome(cognome);*/
        //u.setIdUtente("0");
        // utenteAppFacade.create(u);
        //System.out.println("la data è:"+data_nascita);
        // String[] data = data_nascita.split("/");
        utenteFacebookFacade.create(u);

        return 0;
    }

    public List<UtenteApp> getUsers() {
        List<UtenteApp> listaUser = utenteAppFacade.findAll();

        return listaUser;
    }

    public List<UtenteFacebook> getUserFacebook() {
        List<UtenteFacebook> listaUser = utenteFacebookFacade.findAll();

        return listaUser;
    }

    public List<UtenteGoogle> getUserGoogle() {
        List<UtenteGoogle> listaUser = utenteGoogleFacade.findAll();

        return listaUser;
    }

    public UtenteApp loginUtente(String email, String password) {
        System.out.println("entro in loginUtente");

        UtenteApp u = utenteAppFacade.getUtente(email, password);

        return u;

    }

    public UtenteGoogle loginGoogle(String email, String idGoogle) {
        System.out.println("entro in loginUtente");

        UtenteGoogle u = utenteGoogleFacade.getUtente(email, idGoogle);

        return u;

    }

    public UtenteFacebook loginFacebook(String email, String idFacebook) {
        System.out.println("entro in loginUtente");

        UtenteFacebook u = utenteFacebookFacade.getUtente(email, idFacebook);

        return u;

    }

    public void modificaFotoProfilo(String email, String foto) {
        Profilo p = profiloFacade.getProfilo(email);
        p.setFoto_profilo(foto);
        profiloFacade.edit(p);
    }

    public Profilo modificaInfo(String email, String data_nascita, String formazione, String occupazione, String numero_tel) {
        Profilo p = profiloFacade.getProfilo(email);
        if(!data_nascita.equals("")){
            p.setData_nascita(data_nascita);
        }else if(!formazione.equals("")){
            p.setFormazione(formazione);
        }else if(!occupazione.equals("")){
            p.setOccupazione(occupazione);
        }else if(!numero_tel.equals("")){
            p.setTelefono(numero_tel);
        }
        
        /*
         p.setIdComune(localita);*/
        
        profiloFacade.edit(p);
        return p;
       
    }

    public int aggiungiFollowing(Profilo personalProfile, Profilo followProfile) {
        List<Profilo> following = personalProfile.getFollowing();

        //il compare con la lista in questo caso non va, perché Profilo è un oggetto composto da ulteriori
        //oggetti, e il compare è semplicemente un .equals (più o meno).
        Long idfollow = followProfile.getId();
       
        for (int i = 0; i < following.size(); i++) {
             System.out.println("nome di un mio following:"+following.get(i).getNome());
            if (following.get(i).getId() == idfollow) {
                System.out.println("Il soggetto segue già l'utente.");
                return -1;
            }
        }

        following.add(followProfile);
        personalProfile.setFollowing(following);
        profiloFacade.edit(personalProfile);
        System.out.println("aggiungo il nuovo Follow");
        return 0;

    }

    public int eliminaFollowing(Profilo personalProfile, Profilo followProfile) {
        List<Profilo> following = personalProfile.getFollowing();

        //il compare con la lista in questo caso non va, perché Profilo è un oggetto composto da ulteriori
        //oggetti, e il compare è semplicemente un .equals (più o meno).
        Long idfollow = followProfile.getId();
       
        for (int i = 0; i < following.size(); i++) {
             System.out.println("nome di un mio following:"+following.get(i).getNome());
            if (following.get(i).getId() == idfollow) {
                following.remove(i);
                personalProfile.setFollowing(following);
                profiloFacade.edit(personalProfile);
                System.out.println("Abbiamo eliminato il follow!");
                return 0;
            }
        }
        return -1;
        
    }

}
