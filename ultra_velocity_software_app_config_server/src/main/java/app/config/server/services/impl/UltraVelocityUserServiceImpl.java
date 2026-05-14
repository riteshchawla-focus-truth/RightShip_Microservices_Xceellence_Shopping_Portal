package app.config.server.services.impl;

import app.config.server.entities.UltraVelocityUserEntity;
import app.config.server.exception.DuplicateEmailException;
import app.config.server.exception.ResourceNotFoundException;
import app.config.server.helpers.UltraVelocityEmailSanitizer;
import app.config.server.repository.UltraVelocityUserRepository;
import app.config.server.request.UltraVelocityUserRequestDTO;
import app.config.server.response.UltraVelocityUserResponse;
import app.config.server.services.IUltraVelocityUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UltraVelocityUserServiceImpl implements IUltraVelocityUserService {

    private final UltraVelocityUserRepository userRepository;
    private final UltraVelocityEmailSanitizer emailSanitizer; // custom Spring bean

    @Override
    public UltraVelocityUserResponse create(UltraVelocityUserRequestDTO request) {
        String email = emailSanitizer.sanitize(request.email());
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already in use: " + email);
        }
        UltraVelocityUserEntity rightDigitsUserEntity = UltraVelocityUserEntity.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(email)
                .phone(request.phone())
                .build();
        return toResponse(userRepository.save(rightDigitsUserEntity));
    }

    @Override
    public UltraVelocityUserResponse getById(Long id) {
        UltraVelocityUserEntity rightDigitsUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UltraVelocityUser not found: id=" + id));
        return toResponse(rightDigitsUserEntity);
    }

    @Override
    public Page<UltraVelocityUserResponse> list(String search, Pageable pageable) {
        if (search == null || search.isBlank()) {
            return userRepository.findAll(pageable).map(this::toResponse);
        }
        return userRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        search, search, search, pageable
                ).map(this::toResponse);
    }

    @Override
    public UltraVelocityUserResponse update(Long id, UltraVelocityUserRequestDTO request) {
        UltraVelocityUserEntity rightDigitsUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UltraVelocityUser not found: id=" + id));

        String newEmail = emailSanitizer.sanitize(request.email());
        if (!rightDigitsUserEntity.getEmail().equalsIgnoreCase(newEmail) && userRepository.existsByEmail(newEmail)) {
            throw new DuplicateEmailException("Email already in use: " + newEmail);
        }

        rightDigitsUserEntity.setFirstName(request.firstName());
        rightDigitsUserEntity.setLastName(request.lastName());
        rightDigitsUserEntity.setEmail(newEmail);
        rightDigitsUserEntity.setPhone(request.phone());

        return toResponse(userRepository.save(rightDigitsUserEntity));
    }

    @Override
    public UltraVelocityUserResponse patch(Long id, UltraVelocityUserRequestDTO request) {
        UltraVelocityUserEntity rightDigitsUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UltraVelocityUser not found: id=" + id));

        if (request.firstName() != null && !request.firstName().isBlank()) {
            rightDigitsUserEntity.setFirstName(request.firstName());
        }
        if (request.lastName() != null && !request.lastName().isBlank()) {
            rightDigitsUserEntity.setLastName(request.lastName());
        }
        if (request.email() != null && !request.email().isBlank()) {
            String newEmail = emailSanitizer.sanitize(request.email());
            if (!rightDigitsUserEntity.getEmail().equalsIgnoreCase(newEmail) && userRepository.existsByEmail(newEmail)) {
                throw new DuplicateEmailException("Email already in use: " + newEmail);
            }
            rightDigitsUserEntity.setEmail(newEmail);
        }
        if (request.phone() != null) {
            rightDigitsUserEntity.setPhone(request.phone());
        }
        return toResponse(userRepository.save(rightDigitsUserEntity));
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("UltraVelocityUser not found: id=" + id);
        }
        userRepository.deleteById(id);
    }

    private UltraVelocityUserResponse toResponse(UltraVelocityUserEntity u) {
        return new UltraVelocityUserResponse(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getEmail(),
                u.getPhone(),
                u.getCreatedAt(),
                u.getUpdatedAt()
        );
    }
}
