package pe.project.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.project.model.entity.Cliente;
import pe.project.model.entity.Usuario;
import pe.project.model.service.ClienteService;
import pe.project.model.service.RolService;
import pe.project.model.service.UsuarioService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;

    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    @Autowired
    @Qualifier("rolServiceImpl")
    private RolService rolService;

    @GetMapping(value = {"/", "/listar"})
    public String cliente_listar(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "cliente_listar";
    }

    /* Regisrar producto */
    @GetMapping(value = "/guardar")
    public String cliente_registrar_GET(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("accion", "Registrar"); // h1 de la pagina
        model.addAttribute("cliente", cliente); // objeto para el formulario
        return "cliente_guardar";
    }

    @PostMapping(value = "/guardar")
    public String cliente_registrar_POST(@Valid Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("accion", "Registrar"); // h1 de la pagina
            return "cliente_guardar";
        }
        Usuario usuarioCliente = new Usuario();
        usuarioCliente.setUsername(cliente.getUsuario().getUsername());
        usuarioCliente.setContrasenia(cliente.getUsuario().getContrasenia());
        usuarioCliente.setRol(rolService.findByTipo("CLIENTE"));
        usuarioService.insert(usuarioCliente);
        
        cliente.setUsuario(usuarioCliente);
        clienteService.insert(cliente);
        return "redirect:/cliente/listar";
    }

    /* Editar producto */
    @GetMapping(value = "/editar/{id}")
    public String cliente_editar_GET(Model model, @PathVariable(value = "id") Integer id_cliente) {
        Cliente cliente = clienteService.findById(id_cliente);
        model.addAttribute("accion", "Editar"); // h1 de la pagina
        model.addAttribute("cliente", cliente); // objeto para el formulario
        return "cliente_guardar";
    }

    @PostMapping(value = "/editar/{id}")
    public String cliente_editar_POST(@Valid Cliente cliente, BindingResult result, Model model) 
    {
        if (result.hasErrors()) {
            model.addAttribute("accion", "Editar"); // h1 de la pagina
            return "cliente_guardar";
        }
        Usuario usuarioCliente = usuarioService.findById(cliente.getUsuario().getId_usuario());
        String nombre_usuario = cliente.getUsuario().getUsername(),
                contrasenia = cliente.getUsuario().getContrasenia();
        
        if (usuarioCliente.getUsername().equals(nombre_usuario)) {
            // return "Este nombre de usuario ya existe.Constraint unique";
        }
        usuarioCliente.setUsername(nombre_usuario);
        usuarioCliente.setContrasenia(contrasenia);
        usuarioCliente.setRol(rolService.findByTipo("CLIENTE"));
        
        usuarioService.update(usuarioCliente);           
        clienteService.update(cliente);
        return "redirect:/cliente/listar";
    }

    @GetMapping(value = "/eliminar/{id}")
    public String cliente_eliminar(@PathVariable(value = "id") Integer id_cliente) {
        if (id_cliente != null && clienteService.isExist(id_cliente)) {
            Cliente cliente = clienteService.findById(id_cliente);
            int id_usuario = cliente.getUsuario().getId_usuario();
            
            if (cliente.getLstPedidos().isEmpty()) {     
                clienteService.delete(id_cliente);   
                
                usuarioService.delete(id_usuario);
            }
        }
        return "redirect:/cliente/listar";
    }
}
