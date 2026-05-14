package com.ecom.productservice.api;

import com.ecom.productservice.domain.Product;
import com.ecom.productservice.repo.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductRepository repo;
  public ProductController(ProductRepository repo) { this.repo = repo; }

  @GetMapping
  public List<Product> list() { return repo.findAll(); }

  @GetMapping("/{id}")
  public Product get(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public Product create(@RequestBody Product p) { return repo.save(p); }
}
