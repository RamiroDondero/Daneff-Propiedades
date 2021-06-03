
package inmo.InmobiliariaDaneff.Dao;

import inmo.InmobiliariaDaneff.domain.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteDao extends JpaRepository<Cliente,Long> {

    List<Cliente> findByOrderByDireccionAsc();
    
}
