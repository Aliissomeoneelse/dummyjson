package org.company.dummyjson.controllers;

import lombok.RequiredArgsConstructor;
import org.company.dummyjson.dto.ProductListResponse;
import org.company.dummyjson.models.Product;
import org.company.dummyjson.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // GET ALL / LIMIT / SKIP / SORT
    @GetMapping
    public ProductListResponse getAll(
            @RequestParam(required = false) Integer limit,
            @RequestParam(defaultValue = "0") Integer skip,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order
    ) {
        return productService.getAll(limit, skip, sortBy, order);
    }

    // GET SINGLE
    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return productService.getById(id);
    }

    // SEARCH
    @GetMapping("/search")
    public ProductListResponse search(@RequestParam String q) {
        return productService.search(q);
    }

    // ALL CATEGORIES
    @GetMapping("/categories")
    public List<String> categories() {
        return productService.getCategories();
    }

    // PRODUCTS BY CATEGORY
    @GetMapping("/category/{category}")
    public ProductListResponse byCategory(@PathVariable String category) {
        return productService.getByCategory(category);
    }

    // ADD
    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Product update(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        return productService.update(id, product);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}