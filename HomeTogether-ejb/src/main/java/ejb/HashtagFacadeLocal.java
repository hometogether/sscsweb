/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author Andrea22
 */
import java.util.List;
import javax.ejb.Local;

@Local
public interface HashtagFacadeLocal {

    void create(Hashtag hashtag);

    void edit(Hashtag hashtag);

    void remove(Hashtag hashtag);

    Hashtag find(Object id);

    List<Hashtag> findAll();

    List<Hashtag> findRange(int[] range);

   /* Profilo getInteresse(String email);

    int checkEmailEsistente(String email);*/
     
    int count();

    public Hashtag getHashtag(Long idhashtag);
    
    public Hashtag getHashtag(String nomehashtag);

    public List<Post> getPost(Hashtag hashtag);

    
}
