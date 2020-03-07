package pe.project.controller;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.project.model.entity.DetallePedido;
import pe.project.model.entity.Pedido;
import pe.project.model.entity.Usuario;
import pe.project.model.service.DetallePedidoService;
import pe.project.model.service.PedidoService;
import pe.project.model.service.UsuarioService;

@Controller
public class PedidoController {

    @Autowired
    @Qualifier("pedidoServiceImpl")
    private PedidoService pedidoService;

    @Autowired
    @Qualifier("detallePedidoServiceImpl")
    private DetallePedidoService detallePedidoService;

    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    /* Registrar pedido */
    @GetMapping(value = "/registrar_pedido")
    public String registrar_pedido(HttpSession httpSession) {
        // Traemos el carrito para rellenar el pedido
        ArrayList<DetallePedido> articulos = httpSession.getAttribute("carrito") == null ? new ArrayList<>() : (ArrayList) httpSession.getAttribute("carrito");

        if (!articulos.isEmpty()) {
            // Obtenemos el cliente a partir del usuario logueado
            //se obtiene el usuario autenticado
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioService.findByUsername(auth.getName());

            // Creamos un pedido        
            Pedido nuevoPedido = new Pedido();

            /* Calculamos el monto total*/
            Double monto_total = 0.0;
            for (DetallePedido detallePedido : articulos) {
                monto_total += detallePedido.getImporte();
            }

            nuevoPedido.setFecha(new Date()); // fecha de hoy
            nuevoPedido.setMonto_total(monto_total);
            nuevoPedido.setCliente(usuario.getCliente());
            pedidoService.insert(nuevoPedido);

            for (DetallePedido detallePedido : articulos) {
                detallePedido.setPedido(nuevoPedido);
                detallePedidoService.insert(detallePedido);
            }
            
            //Limpiamos la memoria
            httpSession.setAttribute("carrito", null);    
            nuevoPedido = null;
            monto_total = 0.0;
            articulos = null;
        }

        return "redirect:/mis_pedidos";
    }

    /* Listar pedidos del cliente logueado */
    @GetMapping(value = "/mis_pedidos")
    public String mis_pedidos_GET(Model model) {
        //se obtiene el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByUsername(auth.getName());

        Integer id_cliente = usuario.getCliente().getId_cliente();
        //model.addAttribute("mis_pedidos", usuario.getCliente().getLstPedidos());
        model.addAttribute("mis_pedidos", pedidoService.findAllByIdCliente(id_cliente));
        model.addAttribute("mis_detalles_pedidos", detallePedidoService.findAll());

        return "mis_pedidos";
    }

    // El cliente elimina sus pedidos
    @GetMapping(value = "/cancelar_pedido/{id_pedido}")
    public String cancelar_pedido(@PathVariable(value = "id_pedido") Integer id_pedido) 
    {
        if (id_pedido != null && pedidoService.isExist(id_pedido)) {
            detallePedidoService.deleteAllByIdPedido(id_pedido);
            pedidoService.delete(id_pedido);            
        }
        return "redirect:/mis_pedidos";
    }

}
