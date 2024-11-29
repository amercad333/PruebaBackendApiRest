package co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.gategays;

import co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.BranchProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BranchProductRepository {
    Mono<Boolean> existsByBranchIdAndProductId(Long branchId, Long productId);
    Mono<BranchProduct> save(BranchProduct branchProduct);
    Mono<Void> deleteByBranchIdAndProductId(Long branchId, Long productId);
    Mono<Void> deleteByProductId(Long productId);
    Flux<BranchProduct> findByBranchId(Long branchId);
    Flux<BranchProduct> findAllByBranchIdIn(List<Long> ids);
}
