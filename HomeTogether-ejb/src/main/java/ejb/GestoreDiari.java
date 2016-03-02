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
    
    public Post aggiungiPost(Diario diario, Profilo profilo, String testo) {
        Post post = new Post();
        post.setUser(profilo);
        post.setTesto(testo);
        diario.getPost().add(post);
        
        diarioFacade.edit(diario);
        System.out.println("aggiunto il nuovo post");
        return post;

    }

}
