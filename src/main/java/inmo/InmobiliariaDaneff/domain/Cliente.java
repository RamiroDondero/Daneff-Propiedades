
package inmo.InmobiliariaDaneff.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="cliente")
public class Cliente implements Serializable {
    
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcliente")
    private Long idCliente;
    
    private String direccion;
    
    @Column(name="cod_inmokey")
    private String codInmokey;
    
    @Column(name="cod_mardel_inmueble")
    private String codMardel;
    
    @Column(name="propietario_oficina")
    private String propietario;
    
    private String telefono;
    private Long valor;
    private String notas;
       
    
}
