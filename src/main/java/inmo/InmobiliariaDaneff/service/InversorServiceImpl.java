
package inmo.InmobiliariaDaneff.service;

import inmo.InmobiliariaDaneff.Dao.InversorDao;
import inmo.InmobiliariaDaneff.domain.Inversor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InversorServiceImpl implements InversorService {

    @Autowired
    private InversorDao inversorDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Inversor> listarInversores() {
        return inversorDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Inversor inversor) {
        inversorDao.save(inversor);
    }

    @Override
    @Transactional
    public void eliminar(Inversor inversor) {
        inversorDao.delete(inversor);
    }

    @Override
    @Transactional(readOnly = true)
    public Inversor encontrarInversor(Inversor inversor) {
        return inversorDao.findById(inversor.getIdInversor()).orElse(null);
    }

    @Override
    public ByteArrayInputStream exportarListaInversores() throws Exception {
        String[] columnas = {"Id","Direccion","Cod.Inmokey", "Cod.Mardel", "Propietario","Telefono","Valor","Notas","Comision"};
        Workbook libro = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Sheet hoja = (Sheet) libro.createSheet("Inversores");
        Row registro = hoja.createRow(0);
        
        for(int i = 0 ; i < columnas.length ; i++){
            Cell celda = registro.createCell(i);
            celda.setCellValue(columnas[i]);
        }
        
        List<Inversor> inversores = inversorDao.findAll();
        int initRow =1;
        for( Inversor inversor : inversores){
            registro = hoja.createRow(initRow);
            registro.createCell(0).setCellValue(inversor.getIdInversor());
            registro.createCell(1).setCellValue(inversor.getDireccion());
            registro.createCell(2).setCellValue(inversor.getCodInmokey());
            registro.createCell(3).setCellValue(inversor.getCodMardel());
            registro.createCell(4).setCellValue(inversor.getPropietario());
            registro.createCell(5).setCellValue(inversor.getTelefono());
            if(inversor.getValor()==null){
                registro.createCell(6).setCellValue(0);
            }else{
            registro.createCell(6).setCellValue(inversor.getValor());
            }
            registro.createCell(7).setCellValue(inversor.getNotas());
            if(inversor.getCom()==null){
                registro.createCell(8).setCellValue(0);
            }else{
            registro.createCell(8).setCellValue(inversor.getCom());
            }
            initRow++;
        }
        libro.write(stream);
        libro.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
    
}
