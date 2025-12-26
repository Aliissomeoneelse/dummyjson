package org.company.dummyjson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.company.dummyjson.models.Product;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductListResponse {
    private List<Product> products;
    private long total;
    private int skip;
    private int limit;
}

