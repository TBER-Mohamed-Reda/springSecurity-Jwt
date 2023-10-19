package dev.tber.security.jwt.repositories;

import dev.tber.security.jwt.models.enums.ERole;
import dev.tber.security.jwt.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
