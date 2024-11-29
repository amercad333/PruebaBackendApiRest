package co.com.amrsoftware.msvc_franchise.domain.model.branch;

import co.com.amrsoftware.msvc_franchise.domain.model.product.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class Branch {
    private Long id;
    @NotBlank
    private String name;

    @Valid
    private List<Product> products;

    public Branch() {
        products = new ArrayList<>();
    }
}
