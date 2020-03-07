package pe.project.model.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import pe.project.model.entity.Cliente;

@Repository
public class ClienteDaoImpl implements ClienteDao
{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void insert(Cliente cliente) {
        entityManager.persist(cliente);
    }

    @Override
    public void update(Cliente cliente) {
        entityManager.merge(cliente);
    }

    @Override
    public void delete(Integer id_cliente) {
        entityManager.remove(findById(id_cliente));
    }

    @Override
    public Cliente findById(Integer id_cliente) {
        return entityManager.find(Cliente.class, id_cliente);
    }

    @Override
    public Collection<Cliente> findAll() {
        Query query = entityManager.createNativeQuery("select * from clientes order by id_cliente", Cliente.class);
        return query.getResultList();
    }

    @Override
    public boolean isExist(Integer id_cliente) {
        return entityManager.contains(findById(id_cliente));
    }
    
}
