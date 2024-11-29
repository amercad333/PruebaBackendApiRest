package co.com.amrsoftware.msvc_franchise.domain.model.branch.gateways;

import co.com.amrsoftware.msvc_franchise.domain.model.branch.Branch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BranchRepository {
    Mono<Branch> save(Branch branch);
    Flux<Branch> findAllById(List<Long> ids);
    Flux<Branch> findAll();
    Mono<Branch> findById(Long id);
}
