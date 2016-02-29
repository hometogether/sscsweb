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
public interface LinguaFacadeLocal {

    void create(Lingua lingua);

    void edit(Lingua lingua);

    void remove(Lingua lingua);

    Lingua find(Object id);

    List<Lingua> findAll();

    List<Lingua> findRange(int[] range);

    int count();

    
}
