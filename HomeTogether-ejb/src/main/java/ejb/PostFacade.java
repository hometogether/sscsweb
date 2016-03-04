/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utility.Constants;

/**
 *
 * @author Andrea22
 */
@Stateless
@LocalBean
public class PostFacade extends AbstractFacade<Post> implements PostFacadeLocal {

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {

        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
    @Override
    public List<Post> getPosts(Long idDiario) {
        //Query q = em.createNativeQuery(query);
        System.out.println("entro in getPosts");
        Query q = em.createQuery("SELECT p FROM Diario d JOIN d.post p WHERE d.id=:custId ORDER BY p.data DESC");
        q.setParameter("custId", idDiario);
        List<Post> posts = q.getResultList();
        return posts;
    }

    @Override
    public Post getPost(Long idPost) {
        //Query q = em.createNativeQuery(query);
        System.out.println("entro in getPost");
        Query q = em.createQuery("SELECT p FROM Post p WHERE p.id=:custId");
        q.setParameter("custId", idPost);
        Post post = (Post)q.getResultList().get(0);
        return post;
    }

    
}
