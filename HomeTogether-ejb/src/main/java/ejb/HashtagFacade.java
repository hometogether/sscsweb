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

/**
 *
 * @author Andrea22
 */
@Stateless
@LocalBean
public class HashtagFacade extends AbstractFacade<Hashtag> implements HashtagFacadeLocal{

    @PersistenceContext(unitName = "org.sample_HomeTogether-ejb_ejb_1.0-SNAPSHOTPU")
    
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        
        return em;
    }

    public HashtagFacade() {
        super(Hashtag.class);
    }

    @Override
    public Hashtag getHashtag(Long idhashtag) {
        Query q = em.createQuery("SELECT h FROM Hashtag h WHERE h.id =:custId");
        q.setParameter("custId", idhashtag);
        List l = q.getResultList();
        System.out.println(l);
        if (l.isEmpty()){
            return null;
        } else {
            Hashtag h = em.find(Hashtag.class, ((Hashtag)l.get(0)).getId());
            return h;
        }
    }
    
    @Override
    public Hashtag getHashtag(String nomehashtag) {
        Query q = em.createQuery("SELECT h FROM Hashtag h WHERE h.nome =:custNome");
        q.setParameter("custNome", nomehashtag);
        List l = q.getResultList();
        System.out.println(l);
        if (l.isEmpty()){
            return null;
        } else {
            Hashtag h = em.find(Hashtag.class, ((Hashtag)l.get(0)).getId());
            return h;
        }
    }

    @Override
    public List<Post> getPost(Hashtag hashtag) {
        //Query q = em.createNativeQuery(query);
        System.out.println("entro in getPost");
        Query q = em.createQuery("SELECT p FROM Post p JOIN p.hashtags h WHERE h.id=:custId");
        q.setParameter("custId", hashtag.getId());
        List<Post> post = q.getResultList();
        System.out.println("post hashtag trovati:"+q.getResultList());
        return post;
    }
    
    
    
    
}
