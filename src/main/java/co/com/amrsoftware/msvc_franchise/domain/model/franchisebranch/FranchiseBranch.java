package co.com.amrsoftware.msvc_franchise.domain.model.franchisebranch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class FranchiseBranch {
    private Long id;
    private Long franchiseId;
    private Long branchId;
}
