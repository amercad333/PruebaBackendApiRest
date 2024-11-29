package co.com.amrsoftware.msvc_franchise.domain.model.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class Product {
    private Long id;
    @NotBlank
    private String name;
    @Min(1)
    @NotNull
    private Integer quantity;
}
