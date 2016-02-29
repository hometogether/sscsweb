/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Antonio
 */
@Local
public interface UtenteFacebookFacadeLocal {
  
    void create(UtenteFacebook user);

    void edit(UtenteFacebook user);

    void remove(UtenteFacebook user);

    UtenteFacebook find(Object id);

    List<UtenteFacebook> findAll();

    List<UtenteFacebook> findRange(int[] range);
    
    UtenteFacebook getUtente(String email, String idFacebook);

    int count();

}
