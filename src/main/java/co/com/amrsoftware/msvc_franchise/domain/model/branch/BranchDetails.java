package co.com.amrsoftware.msvc_franchise.domain.model.branch;

import co.com.amrsoftware.msvc_franchise.domain.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class BranchDetails {
    private Long id;
    private String name;
    private List<Product> products;

    public BranchDetails() {
        products = new ArrayList<>();
    }
}
