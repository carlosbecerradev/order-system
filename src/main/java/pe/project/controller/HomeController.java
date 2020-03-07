package pe.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping(value = {"/", "/home"})
    public String home_GET(){
        return "home";
    }
       
    @GetMapping(value = "/acceso_denegado")
    public String acceso_denegado(){
        return "access_denied";
    }
    
    @GetMapping(value = "/producto")
    public String producto(){
        return "redirect:/producto/";
    }        
    
    @GetMapping(value = "/cliente")
    public String cliente_listar(){
        return "redirect:/cliente/";
    }
}
