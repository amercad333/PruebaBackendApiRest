package co.com.amrsoftware.msvc_franchise.domain.model.branchproduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class BranchProduct {
    private Long id;
    private Long branchId;
    private Long productId;
}
