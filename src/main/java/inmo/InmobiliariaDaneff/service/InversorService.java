
package inmo.InmobiliariaDaneff.service;

import inmo.InmobiliariaDaneff.domain.Inversor;
import java.io.ByteArrayInputStream;
import java.util.List;


public interface InversorService {
    
    public List<Inversor> listarInversores();
    
    public void guardar(Inversor inversor);
    
    public void eliminar(Inversor inversor);
    
    public Inversor encontrarInversor(Inversor inversor);
    
    public ByteArrayInputStream exportarListaInversores() throws Exception;
}
