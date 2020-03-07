package pe.project.model.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import pe.project.model.entity.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao
{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void insert(Usuario usuario) {        
        entityManager.persist(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public void delete(Integer id_usuario) {
        int executeUpdate = entityManager.createNativeQuery("delete from usuarios where id_usuario = :param")
                .setParameter("param", id_usuario)
                .executeUpdate();
    }

    @Override
    public Usuario findById(Integer id_usuario) {
        return entityManager.find(Usuario.class, id_usuario);
    }

    @Override
    public Usuario findByUsername(String username) {
        Query query=entityManager.createNativeQuery("select * from usuarios where username=:param",Usuario.class);
        query.setParameter("param",username);
        
        return (Usuario)query.getSingleResult();
    }

    @Override
    public Collection<Usuario> findAll() {
        Query query=entityManager.createNativeQuery("select * from usuarios order by id_usuario",Usuario.class);        
        return query.getResultList();
    }

    @Override
    public boolean isExist(Integer id_usuario) {
        return entityManager.contains(findById(id_usuario));
    }
    
}
