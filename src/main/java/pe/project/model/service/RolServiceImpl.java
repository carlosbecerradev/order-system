package pe.project.model.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.project.model.dao.RolDao;
import pe.project.model.entity.Rol;

@Service
public class RolServiceImpl implements RolService
{
    @Autowired
    @Qualifier("rolDaoImpl")
    private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public Rol findById(Integer id_role) {
        return rolDao.findById(id_role);
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findByTipo(String tipo) {
        return rolDao.findByTipo(tipo);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Rol> findAll() {
        return rolDao.findAll();
    }

    
    
}
