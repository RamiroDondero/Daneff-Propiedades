
package inmo.InmobiliariaDaneff.Dao;

import inmo.InmobiliariaDaneff.domain.Inversor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InversorDao extends JpaRepository<Inversor,Long>{
    
    List<Inversor> findByOrderByDireccionAsc();
    
}
