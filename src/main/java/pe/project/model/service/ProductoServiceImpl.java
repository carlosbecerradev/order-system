package pe.project.model.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.project.model.dao.ProductoDao;
import pe.project.model.entity.Producto;

@Service
public class ProductoServiceImpl implements ProductoService
{
    @Autowired
    @Qualifier("productoDaoImpl")
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = false)
    public void insert(Producto producto) {
        productoDao.insert(producto);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Producto producto) {
        productoDao.update(producto);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id_producto) {
        productoDao.delete(id_producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Integer id_producto) {
        return productoDao.findById(id_producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Producto> findAll() {
        return productoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(Integer id_producto) {
        return productoDao.isExist(id_producto);
    }
    
}
