package pe.project.model.dao;

import java.util.Collection;
import pe.project.model.entity.Usuario;

public interface UsuarioDao 
{
    public abstract void insert(Usuario usuario);
    public abstract void update(Usuario usuario);
    public abstract void delete(Integer id_usuario);
    public abstract Usuario findById(Integer id_usuario);
    public abstract Usuario findByUsername(String username);
    public abstract Collection<Usuario> findAll();
    public abstract boolean isExist(Integer id_usuario);
}
