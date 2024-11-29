package co.com.amrsoftware.msvc_franchise.infrastructure.repository.franchisebranch;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseBranchDataRepository extends ReactiveCrudRepository<FranchiseBranchData, Long> {
    Mono<Boolean> existsByFranchiseIdAndBranchId(Long franchiseId, Long branchId);
    Flux<FranchiseBranchData> findAllByFranchiseId(Long id);
    Mono<Void> deleteByBranchId(Long branchId);
    Mono<Void> deleteByFranchiseId(Long franchiseId);
}
