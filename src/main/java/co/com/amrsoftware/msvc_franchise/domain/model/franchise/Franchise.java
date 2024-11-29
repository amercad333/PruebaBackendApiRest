package co.com.amrsoftware.msvc_franchise.domain.model.franchise;

import co.com.amrsoftware.msvc_franchise.domain.model.branch.Branch;
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
public class Franchise {
    private Long id;
    @NotBlank
    private String name;
    @Valid
    private List<Branch> branches;

    public Franchise() {
        branches = new ArrayList<>();
    }
}
