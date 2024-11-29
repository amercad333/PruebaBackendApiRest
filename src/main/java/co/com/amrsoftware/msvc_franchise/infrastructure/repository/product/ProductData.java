package co.com.amrsoftware.msvc_franchise.infrastructure.repository.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@Table(value = "products")
public class ProductData {
    @Id
    private Long id;
    private String name;
    private Integer quantity;
}
