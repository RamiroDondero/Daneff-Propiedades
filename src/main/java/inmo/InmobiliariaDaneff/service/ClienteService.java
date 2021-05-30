
package inmo.InmobiliariaDaneff.service;

import inmo.InmobiliariaDaneff.domain.Cliente;
import java.io.ByteArrayInputStream;
import java.util.List;


public interface ClienteService {
    
    public List<Cliente> listarClientes();
    
    public void guardar(Cliente cliente);
    
    public void eliminar(Cliente cliente);
    
    public Cliente encontrarCliente(Cliente cliente);
    
    public ByteArrayInputStream exportarListaClientes() throws Exception;
    
}
