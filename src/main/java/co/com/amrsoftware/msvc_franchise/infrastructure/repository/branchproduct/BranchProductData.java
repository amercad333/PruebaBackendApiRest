package co.com.amrsoftware.msvc_franchise.infrastructure.repository.branchproduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@Table(value = "branches_products")
public class BranchProductData {
    @Id
    private Long id;
    @Column(value = "branch_id")
    private Long branchId;
    @Column(value = "product_id")
    private Long productId;
}
