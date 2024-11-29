package co.com.amrsoftware.msvc_franchise.infrastructure.repository.franchese;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Table(value = "franchises")
public class FranchiseData {
    @Id
    private Long id;
    private String name;
}
