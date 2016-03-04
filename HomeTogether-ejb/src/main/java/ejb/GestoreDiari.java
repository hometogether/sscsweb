/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

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
public class GestoreDiari {

    @EJB
    private DiarioFacadeLocal diarioFacade;
    @EJB
    private PostFacadeLocal postFacade;

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
        diario.getPost().add(post);
        
        diarioFacade.edit(diario);
        System.out.println("aggiunto il nuovo post");
        return post;

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
}
