package pe.project.model.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.project.model.dao.UsuarioDao;
import pe.project.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService
{
    @Autowired
    @Qualifier("usuarioDaoImpl")
    private UsuarioDao usuarioDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = false)
    public void insert(Usuario usuario) {
        String bCrypt=passwordEncoder.encode(usuario.getContrasenia());
        usuario.setContrasenia(bCrypt);
        usuarioDao.insert(usuario);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Usuario usuario) {
        if (usuario.getContrasenia().length() != 60) {            
            String bCrypt=passwordEncoder.encode(usuario.getContrasenia());
            usuario.setContrasenia(bCrypt);
        }
        usuarioDao.update(usuario);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id_usuario) {
        usuarioDao.delete(id_usuario);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Integer id_usuario) {
        return usuarioDao.findById(id_usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Usuario> findAll() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(Integer id_usuario) {
        return usuarioDao.isExist(id_usuario);
    }
    
    
    
}
