package com.ecom.userservice.api;

import com.ecom.userservice.domain.UserProfile;
import com.ecom.userservice.repo.UserProfileRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
  private final UserProfileRepository repo;
  public UserProfileController(UserProfileRepository repo) { this.repo = repo; }

  @GetMapping("/me")
  public UserProfile me(Authentication auth) {
    Jwt jwt = (Jwt) auth.getPrincipal();
    return repo.findById(jwt.getSubject()).orElseGet(() -> {
      UserProfile p = new UserProfile();
      p.setUserId(jwt.getSubject());
      p.setEmail(jwt.getClaimAsString("email"));
      p.setFullName(jwt.getClaimAsString("name"));
      return repo.save(p);
    });
  }

  @PutMapping("/me")
  public UserProfile update(Authentication auth, @RequestBody UserProfile req) {
    UserProfile p = me(auth);
    if (req.getEmail() != null) p.setEmail(req.getEmail());
    if (req.getFullName() != null) p.setFullName(req.getFullName());
    if (req.getPhone() != null) p.setPhone(req.getPhone());
    return repo.save(p);
  }
}
