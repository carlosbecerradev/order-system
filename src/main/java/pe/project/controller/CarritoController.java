package pe.project.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.project.model.entity.DetallePedido;
import pe.project.model.entity.Producto;
import pe.project.model.service.ProductoService;

@Controller
public class CarritoController {

    @Autowired
    @Qualifier("productoServiceImpl")
    private ProductoService productoService;

    @GetMapping(value = "/catalogo")
    public String catalogo(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "catalogo";
    }


    @GetMapping(value = "/carrito_agregar")
    public String carritoAgregar(HttpSession httpSession,
            @RequestParam(value = "id_producto") Integer id_producto,
            @RequestParam(value = "cantidad") Integer cantidad) 
    {
        ArrayList<DetallePedido> articulos = httpSession.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) httpSession.getAttribute("carrito");
        boolean flag = false;

        if (articulos.size() > 0 && cantidad > 0) {
            for (DetallePedido detallePedido : articulos) {
                if (id_producto == detallePedido.getProducto().getId_producto()) {
                    detallePedido.setCantidad(detallePedido.getCantidad() + cantidad);
                    detallePedido.setImporte(detallePedido.getCantidad() * detallePedido.getProducto().getPrecio());
                    flag = true;
                    break;
                }
            }
        }

        if (!flag && cantidad > 0) {
            DetallePedido detallePedido = new DetallePedido();
            Producto producto = productoService.findById(id_producto);
            detallePedido.setProducto(producto);
            detallePedido.setCantidad(cantidad);
            detallePedido.setImporte(cantidad * producto.getPrecio());
            articulos.add(detallePedido);
        }

        httpSession.setAttribute("carrito", articulos);
        return "redirect:/carrito";
    }
    
    @GetMapping(value = "/carrito")
    public String carrito(Model model, HttpSession httpSession) {
        ArrayList<DetallePedido> articulos = httpSession.getAttribute("carrito") == null ? null : (ArrayList) httpSession.getAttribute("carrito");
        
        if (articulos != null) 
        {
            Double monto_final = 0.0;
            for (DetallePedido articulo : articulos) 
            {
                monto_final += articulo.getImporte();
            }
            model.addAttribute("carrito", articulos);
            model.addAttribute("monto_final", monto_final);
        }
        

        return "carrito";
    }

    @GetMapping(value = "/carrito_quitar")
    public String carritoQuitar(HttpSession httpSession,
            @RequestParam(value = "id_producto") Integer id_producto) {
        ArrayList<DetallePedido> articulos = httpSession.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) httpSession.getAttribute("carrito");

        if (articulos != null) {
            for (DetallePedido detallePedido : articulos) {
                if (id_producto == detallePedido.getProducto().getId_producto()) {
                    articulos.remove(detallePedido);
                    break;
                }
            }
        }

        httpSession.setAttribute("carrito", articulos);

        return "redirect:/carrito";
    }

}
