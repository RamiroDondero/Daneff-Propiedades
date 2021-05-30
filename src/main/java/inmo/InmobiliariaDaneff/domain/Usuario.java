package inmo.InmobiliariaDaneff.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idusuario")
    private Long idUsuario;
    
    @NotEmpty
    @Column(name="username")
    private String username;

    @NotEmpty
    @Column(name="password")
    private String password;
    
    @OneToMany()
    @JoinColumn(name="idusuario")
    private List<Rol> roles;

}
