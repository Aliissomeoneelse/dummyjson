package org.company.dummyjson.services;

import lombok.RequiredArgsConstructor;
import org.company.dummyjson.dto.ProductListResponse;
import org.company.dummyjson.models.Product;
import org.company.dummyjson.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // GET ALL / LIMIT & SKIP / SORT
    public ProductListResponse getAll(
            Integer limit,
            Integer skip,
            String sortBy,
            String order
    ) {
        List<Product> products = productRepository.findAll();

        // SORT
        if (sortBy != null) {
            Comparator<Product> comparator = switch (sortBy) {
                case "price" -> Comparator.comparing(Product::getPrice);
                case "rating" -> Comparator.comparing(Product::getRating);
                default -> Comparator.comparing(Product::getId);
            };

            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }
            products = products.stream().sorted(comparator).toList();
        }

        int total = products.size();
        int from = Math.min(skip, total);
        int to = limit != null ? Math.min(from + limit, total) : total;

        List<Product> result = products.subList(from, to);

        return new ProductListResponse(
                result,
                total,
                skip != null ? skip : 0,
                limit != null ? limit : total
        );
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductListResponse search(String q) {
        List<Product> products = productRepository.search(q);
        return new ProductListResponse(products, products.size(), 0, products.size());
    }

    public List<String> getCategories() {
        return productRepository.findAllCategories();
    }

    public ProductListResponse getByCategory(String category) {
        List<Product> products = productRepository.findByCategoryIgnoreCase(category);
        return new ProductListResponse(products, products.size(), 0, products.size());
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product product = getById(id);

        product.setTitle(updated.getTitle());
        product.setDescription(updated.getDescription());
        product.setPrice(updated.getPrice());
        product.setRating(updated.getRating());
        product.setStock(updated.getStock());
        product.setBrand(updated.getBrand());
        product.setCategory(updated.getCategory());

        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}