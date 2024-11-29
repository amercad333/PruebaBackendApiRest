package co.com.amrsoftware.msvc_franchise.infrastructure.repository.branchproduct;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BranchProductDataRepository extends ReactiveCrudRepository<BranchProductData, Long> {
    Mono<Boolean> existsByBranchIdAndProductId(Long branchId, Long productId);
    @Modifying
    Mono<Void> deleteByBranchIdAndProductId(Long branchId, Long productId);
}
