package pe.project.model.service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.project.model.entity.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    //se inyecta la dependencia al bean "userServiceImpl"
    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        //usuario buscado por username
        Usuario usuario = usuarioService.findByUsername(username);
        
        if (usuario != null) {
            //rol del usuario (vacío)
            Collection<GrantedAuthority> roles=new ArrayList();
            
            roles.add(new SimpleGrantedAuthority("ROLE_"+ usuario.getRol().getTipo()));
            
            return new User(
                    usuario.getUsername(), 
                    usuario.getContrasenia(), 
                    true, true, true, true, 
                    roles);
        }
        throw new UsernameNotFoundException("Error, "+username+" no existe en el sistema!");
    }
    
}
