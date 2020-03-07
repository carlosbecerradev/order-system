package pe.project.model.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import pe.project.model.entity.Rol;

@Repository
public class RolDaoImpl implements RolDao
{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Rol findById(Integer id_role) {
        return entityManager.find(Rol.class, id_role);
    }

    @Override
    public Rol findByTipo(String tipo) {
        Query query=entityManager.createNativeQuery("select * from roles where tipo=:param",Rol.class);
        query.setParameter("param",tipo);
        
        return (Rol)query.getSingleResult();
    }

    @Override
    public Collection<Rol> findAll() {
        Query query=entityManager.createNativeQuery("select * from roles order by id_rol",Rol.class);
        return query.getResultList();
    }
    
}
