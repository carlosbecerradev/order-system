package pe.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.project.model.service.PedidoService;
import pe.project.model.service.ReporteService;

@Controller
public class ReporteController 
{
    @Autowired
    @Qualifier("reporteServiceImpl")
    private ReporteService reporteService;
    
    @Autowired
    @Qualifier("pedidoServiceImpl")
    private PedidoService pedidoService;
    
    @GetMapping(value = "/reporte")
    public String reporte_GET(Model model)
    {
        model.addAttribute("pedidos", pedidoService.findAll());
        model.addAttribute("recaudado", reporteService.getRecaudado());
        
        return "reporte";
    }
       
}
