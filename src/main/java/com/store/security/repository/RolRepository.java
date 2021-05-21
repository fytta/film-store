package com.store.security.repository;

import com.store.security.entity.Rol;
import com.store.security.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolName(RolName rolName);

}
