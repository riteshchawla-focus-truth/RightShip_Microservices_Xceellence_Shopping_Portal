package app.config.server.controller;

import app.config.server.request.UltraVelocityUserRequestDTO;
import app.config.server.response.UltraVelocityUserResponse;
import app.config.server.services.IUltraVelocityUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UltraVelocityUserController {

    private final IUltraVelocityUserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UltraVelocityUserResponse create(@Valid @RequestBody UltraVelocityUserRequestDTO request) {
        return userService.create(request);
    }

    @GetMapping("/{id}")
    public UltraVelocityUserResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public Page<UltraVelocityUserResponse> list(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "lastName") Pageable pageable
    ) {
        return userService.list(search, pageable);
    }

    @PutMapping("/{id}")
    public UltraVelocityUserResponse update(@PathVariable Long id, @Valid @RequestBody UltraVelocityUserRequestDTO request) {
        return userService.update(id, request);
    }

    @PatchMapping("/{id}")
    public UltraVelocityUserResponse patch(@PathVariable Long id, @RequestBody UltraVelocityUserRequestDTO request) {
        // Partial update (validation handled in service)
        return userService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
