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
public interface ComuneFacadeLocal {

    void create(Comune comune);

    void edit(Comune comune);

    void remove(Comune comune);

    Comune find(Object id);

    List<Comune> findAll();

    List<Comune> findRange(int[] range);

    int count();

    public List<Comune> getComuni();

    
}
