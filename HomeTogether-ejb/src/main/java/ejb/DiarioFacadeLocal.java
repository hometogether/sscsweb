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
public interface DiarioFacadeLocal {

    void create(Diario diario);

    void edit(Diario diario);

    void remove(Diario diario);

    Diario find(Object id);

    List<Diario> findAll();

    List<Diario> findRange(int[] range);

    int count();


    
}
