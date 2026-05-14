package functional.programming.repository;


import functional.programming.entities.UltraVelocityUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UltraVelocityUserRepository extends JpaRepository<UltraVelocityUserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UltraVelocityUserEntity> findByEmail(String email);

    Page<UltraVelocityUserEntity> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String firstName, String lastName, String email, Pageable pageable
    );
}
