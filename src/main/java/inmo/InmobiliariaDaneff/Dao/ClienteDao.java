
package inmo.InmobiliariaDaneff.Dao;

import inmo.InmobiliariaDaneff.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteDao extends JpaRepository<Cliente,Long> {
    
}
