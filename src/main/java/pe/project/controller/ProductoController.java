package pe.project.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.project.model.entity.Producto;
import pe.project.model.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    @Qualifier("productoServiceImpl")
    private ProductoService productoService;

    /* Listar productos */
    @GetMapping(value = {"/", "/listar"})
    public String producto_listar(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "producto_listar";
    }

    /* Regisrar producto */
    @GetMapping(value = "/guardar")
    public String producto_registrar_GET(Model model) {
        Producto producto = new Producto();
        model.addAttribute("accion", "Registrar"); // h1 de la pagina
        model.addAttribute("producto", producto); // objeto para el formulario
        return "producto_guardar";
    }

    @PostMapping(value = "/guardar")
    public String producto_registrar_POST(@Valid Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("accion", "Registrar"); // h1 de la pagina
            return "producto_guardar";
        }
        productoService.insert(producto);
        return "redirect:/producto/listar";
    }

    /* Editar producto */
    @GetMapping(value = "/editar/{id}")
    public String producto_editar_GET(Model model, @PathVariable(value = "id") Integer id_producto) {
        Producto producto = productoService.findById(id_producto);
        model.addAttribute("accion", "Editar"); // h1 de la pagina
        model.addAttribute("producto", producto); // objeto para el formulario
        return "producto_guardar";
    }

    @PostMapping(value = "/editar/{id}")
    public String producto_editar_POST(@Valid Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("accion", "Editar"); // h1 de la pagina
            return "producto_guardar";
        }
        productoService.update(producto);
        return "redirect:/producto/listar";
    }
    
    @GetMapping(value = "/eliminar/{id}")
    public String producto_elimnar(@PathVariable(value = "id") Integer id_producto){
        if (id_producto != null && productoService.isExist(id_producto)) 
        {
            Producto producto = productoService.findById(id_producto);
            if (producto.getLstDetallePedido().isEmpty()) {
                productoService.delete(id_producto);
            }
        }
        return "redirect:/producto/listar";
    }
           

}
