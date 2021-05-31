package inmo.InmobiliariaDaneff.web;

import inmo.InmobiliariaDaneff.domain.Cliente;
import inmo.InmobiliariaDaneff.domain.Inversor;
import inmo.InmobiliariaDaneff.service.ClienteService;
import inmo.InmobiliariaDaneff.service.InversorService;
import java.io.ByteArrayInputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private InversorService inversorService;
    
    NumberFormat moneda = NumberFormat.getCurrencyInstance(Locale.US);

    @GetMapping({"/","/login"})
    public String inicio(Model model) {
        log.info("ejecutando controlador MVC");
        return "login";
    }
    @GetMapping("/menu")
    public String menu(Model model){
        return "index";
    }
    

    @GetMapping("/clientes")
    public String clientes(Model model) {

        model.addAttribute("moneda", moneda);
        
       List<Cliente> clientes = clienteService.listarClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/agregar")
    public String agregar(Cliente cliente) {

        return "modificar";

    }

    @PostMapping("/guardar")
    public String guardar(Cliente cliente) {

        clienteService.guardar(cliente);
        return "redirect:/clientes";

    }

    @GetMapping("/editar/{idCliente}")
    public String editar(Cliente cliente, Model model) {

        cliente = clienteService.encontrarCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{idCliente}")
    public String eliminar(Cliente cliente) {
        clienteService.eliminar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/inversores")
    public String inversores(Model model) {

        model.addAttribute("moneda", moneda);
        
        List<Inversor> inversores = inversorService.listarInversores();
        model.addAttribute("inversores", inversores);
        return "inversores";
    }

    @PostMapping("/guardarinv")
    public String guardarInv(Inversor inversor) {
        inversorService.guardar(inversor);
        return "redirect:/inversores";
    }

    @GetMapping("/editarinv/{idInversor}")
    public String editarInversor(Inversor inversor, Model model) {

        inversor = inversorService.encontrarInversor(inversor);
        model.addAttribute("inversor", inversor);
        return "modificarInv";
    }

    @GetMapping("/eliminarinv/{idInversor}")
    public String eliminarInversor(Inversor inversor) {
        inversorService.eliminar(inversor);
        return "redirect:/inversores";
    }
    
    @GetMapping("/exportarclientes")
    public ResponseEntity<InputStreamResource> exportarListaClientes() throws Exception {
        ByteArrayInputStream stream = clienteService.exportarListaClientes();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=clientes.xls");
        
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }
    
    @GetMapping("/exportarinversores")
    public ResponseEntity<InputStreamResource> exportarListaInversores() throws Exception {
        ByteArrayInputStream stream = inversorService.exportarListaInversores();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=inversores.xls");
        
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

}
