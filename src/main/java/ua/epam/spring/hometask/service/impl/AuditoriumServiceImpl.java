package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.daos.AuditoriumDao;
import ua.epam.spring.hometask.daos.impl.AuditoriumDaoImpl;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * @author Yevheniia_Blokhina.
 */
@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumDao auditoriumDao;

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumDao.findAll();
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumDao.findByName(name);
    }

    public AuditoriumDao getAuditoriumDao() {
        return auditoriumDao;
    }

    public void setAuditoriumDao(AuditoriumDaoImpl auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }
}
