/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Local;

@Local
public interface UtenteGoogleFacadeLocal {

    void create(UtenteGoogle user);

    void edit(UtenteGoogle user);

    void remove(UtenteGoogle user);

    UtenteGoogle find(Object id);

    List<UtenteGoogle> findAll();

    List<UtenteGoogle> findRange(int[] range);

    int count();

    public UtenteGoogle getUtente(String email, String password);
    
}
