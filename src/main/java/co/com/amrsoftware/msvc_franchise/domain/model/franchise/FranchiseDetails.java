package co.com.amrsoftware.msvc_franchise.domain.model.franchise;

import co.com.amrsoftware.msvc_franchise.domain.model.branch.BranchDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class FranchiseDetails {
    private Long id;
    private String name;
    private List<BranchDetails> branches;

    public FranchiseDetails() {
        branches = new ArrayList<>();
    }
}
