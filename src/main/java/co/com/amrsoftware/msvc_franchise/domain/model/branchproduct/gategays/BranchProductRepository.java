package co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.gategays;

import co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.BranchProduct;
import reactor.core.publisher.Mono;

public interface BranchProductRepository {
    Mono<Boolean> existsByBranchIdAndProductId(Long branchId, Long productId);
    Mono<BranchProduct> save(BranchProduct branchProduct);
    Mono<Void> deleteByBranchIdAndProductId(Long branchId, Long productId);
}
