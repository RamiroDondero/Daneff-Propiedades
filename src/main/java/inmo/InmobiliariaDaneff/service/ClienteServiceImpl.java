package inmo.InmobiliariaDaneff.service;

import inmo.InmobiliariaDaneff.Dao.ClienteDao;
import inmo.InmobiliariaDaneff.domain.Cliente;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
      return   clienteDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void eliminar(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente encontrarCliente(Cliente cliente) {
        return  clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }

    @Override
    public ByteArrayInputStream exportarListaClientes() throws IOException {
        String[] columnas = {"Id","Direccion","Cod.Inmokey", "Cod.Mardel", "Propietario","Telefono","Valor","Notas"};
        Workbook libro = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Sheet hoja = (Sheet) libro.createSheet("Clientes");
        Row registro = hoja.createRow(0);
        
        for(int i = 0 ; i < columnas.length ; i++){
            Cell celda = registro.createCell(i);
            celda.setCellValue(columnas[i]);
        }
        
        List<Cliente> clientes = clienteDao.findAll();
        int initRow =1;
        for( Cliente cliente : clientes){
            registro = hoja.createRow(initRow);
            registro.createCell(0).setCellValue(cliente.getIdCliente());
            registro.createCell(1).setCellValue(cliente.getDireccion());
            registro.createCell(2).setCellValue(cliente.getCodInmokey());
            registro.createCell(3).setCellValue(cliente.getCodMardel());
            registro.createCell(4).setCellValue(cliente.getPropietario());
            registro.createCell(5).setCellValue(cliente.getTelefono());
            if(cliente.getValor()==null){
                registro.createCell(6).setCellValue(0);
            }else{
            registro.createCell(6).setCellValue(cliente.getValor());
            }
            registro.createCell(7).setCellValue(cliente.getNotas());
            initRow++;
        }
        libro.write(stream);
        libro.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }

}
