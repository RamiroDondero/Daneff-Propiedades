
package inmo.InmobiliariaDaneff.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="inversor", schema="inmo")
public class Inversor implements Serializable {
    
    private static final long serialVersionUID=1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idinversor")
    private Long idInversor; 
    
    @Column(name="direccion")
    private String direccion;
    
    @Column(name="cod_inmokey")
    private String codInmokey;
            
    @Column(name="cod_mardel_inmueble")
    private String codMardel;
    
    @Column(name="propietario")
    private String propietario;

    @Column(name="valor")
    private Long valor;
    
    @Column(name="telefono")
    private String telefono;
    
    @Column(name="notas")
    private String notas;
    
    @Column(name="com")
    private Long com;
    
    
}
