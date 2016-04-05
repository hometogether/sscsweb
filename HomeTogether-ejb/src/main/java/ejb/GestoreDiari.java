/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import java.util.Objects;
import javax.ejb.ApplicationException;
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
@ApplicationException(rollback=true)
public class GestoreDiari {

    @EJB
    private DiarioFacadeLocal diarioFacade;
    @EJB
    private PostFacadeLocal postFacade;
    @EJB
    private CommentoFacadeLocal commentoFacade;
    @EJB
    private HashtagFacadeLocal hashtagFacade;
    @EJB
    private ProfiloFacadeLocal profiloFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Diario getDiario(Long idDiario) {
        Diario d = diarioFacade.getDiario(idDiario);
        return d;
    }
       
    public List<Post> getPosts(Long idDiario) {
        List<Post> p = postFacade.getPosts(idDiario);
        return p;
    }
    
    public Post getPost(Long idPost) {
        Post p = postFacade.getPost(idPost);
        return p;
    }
    
    public Post aggiungiPost(Diario diario, Profilo profilo, String testo) {
        Post post = new Post();
        post.setUser(profilo);
        post.setTesto(testo);
        post.setDiario(diario);
        diario.getPost().add(post);
        EntityManager em = diarioFacade.getEntityManager();
        em.persist(post);
        em.flush();
        diarioFacade.edit(diario);
        System.out.println("aggiunto il nuovo post. Id: "+post.getId());
        return post;

    }
    
    public void modificaPost(Post post, String testo) {
        post.setTesto(testo);
        postFacade.edit(post);
        System.out.println("Post Modificato");
        

    }
    
    public void eliminaPost(Post post) {
        Diario diario= post.getDiario();
        diario.getPost().remove(post);
        diarioFacade.edit(diario);
        postFacade.remove(post);
        System.out.println("Post Eliminato");
        

    }
    
    public void aggiungiLike(Post post, Profilo profilo) {
        
        post.getLikes().add(profilo);
        postFacade.edit(post);
        System.out.println("aggiunto il nuovo like");

    }
    
     public int rimuoviLike(Post post, Profilo profilo) {
        List<Profilo> list = post.getLikes();
        boolean trovato = false;
        for (int i=0;i<list.size() && trovato == false;i++){
            if (Objects.equals(list.get(i).getId(), profilo.getId())){
                list.remove(i);
                postFacade.edit(post);
                trovato = true;
            }
        }
        if (trovato) {
            return 0;
        } else {
            return -1;
        }
           

    }
     
    public Commento getCommento(Long idCommento) {
        Commento c = commentoFacade.getCommento(idCommento);
        return c;
    }
    
     public Long aggiungiCommento(Post post, Profilo profilo, String testo) {
        Commento commento = new Commento();
        commento.setTesto(testo);
        commento.setUser(profilo);
        commento.setPost(post);
        post.getCommenti().add(commento);
        EntityManager em = diarioFacade.getEntityManager();
        em.persist(commento);
        em.flush();
        postFacade.edit(post);
        System.out.println("aggiunto il nuovo commento. Id: "+commento.getId());
        return commento.getId();

    }
     
    public void modificaCommento(Commento commento, String testo) {
        commento.setTesto(testo);
        commentoFacade.edit(commento);
        System.out.println("Commento Modificato");
        

    }
    
    public void eliminaCommento(Commento commento) {
        Post post= commento.getPost();
        post.getCommenti().remove(commento);
        postFacade.edit(post);
        commentoFacade.remove(commento);
        
        System.out.println("Commento Eliminato");
        

    }
    
    public int aggiungiHashtag(Post post, String nomehashtag) {
        System.out.println("entro in agggiungiHashtag");
        System.out.println("nome hashtag:" + nomehashtag);
        if (post == null || nomehashtag == null) {
            System.out.println("Errore: id Profilo = 0 o nome nomehashtag non valido");
            return -1;
        } else {
            Hashtag hashtag = hashtagFacade.getHashtag(nomehashtag);
            if (hashtag == null) {
                //l'interesse non esiste nel DB. Dobbiamo crearlo.
                hashtag = new Hashtag();
                hashtag.setNome(nomehashtag);
                hashtagFacade.create(hashtag);
                System.out.println("creato nuovo Hashtag");

            } else {
                System.out.println("Hashtag esistente");
            }
            
            List<Hashtag> hashtags = post.getHashtags();
            //dobbiamo controllare che l'hashtag non sia già associato al post
            boolean contain = post.getHashtags().contains(hashtag);

            if (contain == false) {
                hashtags.add(hashtag);
                post.setHashtags(hashtags);
                //EntityManager em = postFacade.getEntityManager();
                //em.persist(post);
                //em.flush();
                postFacade.edit(post);

                return 0;
            } else {
                System.out.println("non devo fare niente, l'hashtag è già associato!");
                return -1; 
            }
        }

    }
    
    public List<Post> cercaHashtag(String nomehashtag) {
        System.out.println("entro in agggiungiHashtag");
        System.out.println("nome hashtag:" + nomehashtag);
        if (nomehashtag == null) {
            System.out.println("Nome nomehashtag non valido");
            return null;
        } else {
            Hashtag hashtag = hashtagFacade.getHashtag(nomehashtag);
            if (hashtag == null) {
                System.out.println("Nessun post ha questo hashtag associato");
                return null;

            } 
            
            List<Post> hashtags = hashtagFacade.getPost(hashtag);
            
            return hashtags;
        }

    }
}
