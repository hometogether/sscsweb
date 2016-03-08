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
public interface ProfiloFacadeLocal {

    void create(Profilo user);

    void edit(Profilo user);

    void remove(Profilo user);

    Profilo find(Object id);

    List<Profilo> findAll();

    List<Profilo> findRange(int[] range);

    Profilo getProfilo(String email);
    
    Profilo getProfilo(Long idProfilo);
    
    List getProfiloUtente(String nomeDigitato, Long idp, int offset);

    int checkEmailEsistente(String email);
     
    int count();

    EntityManager getEntityManager();
    
}
