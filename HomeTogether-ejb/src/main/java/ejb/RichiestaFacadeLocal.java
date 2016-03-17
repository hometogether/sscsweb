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
import javax.persistence.EntityManager;

@Local
public interface RichiestaFacadeLocal {

    void create(Richiesta richiesta);

    void edit(Richiesta richiesta);

    void remove(Richiesta richiesta);

    Richiesta find(Object id);

    List<Richiesta> findAll();

    List<Richiesta> findRange(int[] range);

    int count();

    EntityManager getEntityManager();
    
    Richiesta getRichiesta(Long idRichiesta);
    
}
