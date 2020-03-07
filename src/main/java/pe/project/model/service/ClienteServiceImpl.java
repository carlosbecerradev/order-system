package pe.project.model.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.project.model.dao.ClienteDao;
import pe.project.model.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService
{
    @Autowired
    @Qualifier("clienteDaoImpl")
    private ClienteDao clienteDao;
    
    @Override
    @Transactional(readOnly = false)
    public void insert(Cliente cliente) {
        clienteDao.insert(cliente);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Cliente cliente) {
        clienteDao.update(cliente);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id_producto) {
        clienteDao.delete(id_producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Integer id_cliente) {
        return clienteDao.findById(id_cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Cliente> findAll() {
        return clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(Integer id_cliente) {
        return clienteDao.isExist(id_cliente);
    }
    
}
