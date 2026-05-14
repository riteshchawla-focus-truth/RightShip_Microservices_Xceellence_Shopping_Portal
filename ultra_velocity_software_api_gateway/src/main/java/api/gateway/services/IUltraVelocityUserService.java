package api.gateway.services;

import app.config.server.request.UltraVelocityUserRequestDTO;
import app.config.server.response.UltraVelocityUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUltraVelocityUserService {
    UltraVelocityUserResponse create(UltraVelocityUserRequestDTO request);
    UltraVelocityUserResponse getById(Long id);
    Page<UltraVelocityUserResponse> list(String search, Pageable pageable);
    UltraVelocityUserResponse update(Long id, UltraVelocityUserRequestDTO request);
    UltraVelocityUserResponse patch(Long id, UltraVelocityUserRequestDTO request);
    void delete(Long id);
}
