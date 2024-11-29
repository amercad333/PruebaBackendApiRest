package co.com.amrsoftware.msvc_franchise.infrastructure.repository.franchisebranch;

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
@Table(value = "franchises_branches")
public class FranchiseBranchData {
    @Id
    private Long id;
    @Column(value = "franchise_id")
    private Long franchiseId;
    @Column(value = "branch_id")
    private Long branchId;
}
