package com.store.security.service;

import com.store.security.entity.Rol;
import com.store.security.enums.RolName;
import com.store.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolName(RolName rolName) {
        return rolRepository.findByRolName(rolName);
    }

    public void save(Rol rol) {
        rolRepository.save(rol);
    }

}
