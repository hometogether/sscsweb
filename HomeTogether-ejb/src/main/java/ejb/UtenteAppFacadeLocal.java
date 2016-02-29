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
public interface UtenteAppFacadeLocal {

    void create(UtenteApp user);

    void edit(UtenteApp user);

    void remove(UtenteApp user);

    UtenteApp find(Object id);

    List<UtenteApp> findAll();

    List<UtenteApp> findRange(int[] range);

    UtenteApp getUtente(String email, String password);
    
    int count();

    
}

