package pe.project.model.dao;

import java.util.Collection;
import pe.project.model.entity.Rol;

public interface RolDao 
{
    public abstract Rol findById(Integer id_role);
    public abstract Rol findByTipo(String tipo);
    
    public abstract Collection<Rol> findAll();
}
