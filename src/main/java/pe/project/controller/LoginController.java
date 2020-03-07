package pe.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login() {
        return "login";
    }
    
    //cerrar sesión por spring security
    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) 
    {
        //se obtiene el usuario autenticado
        Authentication auth=SecurityContextHolder.
                            getContext().getAuthentication();
        
        //si existe el usuario autenticado, cerrar sesión
        if(auth!=null)    
        {
            //cerrar sesión
            new SecurityContextLogoutHandler().
                    logout(request,response,auth);
        }

        //si no existe el usuario autenticadi, redireccionar a login
        return "redirect:login?logout";
    }
    
}
