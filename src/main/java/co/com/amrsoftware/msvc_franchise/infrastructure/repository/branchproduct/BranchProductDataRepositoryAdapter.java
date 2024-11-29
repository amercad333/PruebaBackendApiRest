package co.com.amrsoftware.msvc_franchise.infrastructure.repository.branchproduct;

import co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.BranchProduct;
import co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.gategays.BranchProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BranchProductDataRepositoryAdapter implements BranchProductRepository {
    private final BranchProductDataRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Mono<Boolean> existsByBranchIdAndProductId(Long branchId, Long productId) {
        return repository.existsByBranchIdAndProductId(branchId, productId);
    }

    @Override
    @Transactional
    public Mono<BranchProduct> save(BranchProduct branchProduct) {
        return repository.save(this.toEntity(branchProduct)).map(this::toDto);
    }

    @Override
    @Transactional
    public Mono<Void> deleteByBranchIdAndProductId(Long branchId, Long productId) {
        return repository.deleteByBranchIdAndProductId(branchId, productId);
    }

    @Override
    @Transactional
    public Mono<Void> deleteByProductId(Long productId) {
        return repository.deleteByProductId(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<BranchProduct> findByBranchId(Long branchId) {
        return repository.findByBranchId(branchId);
    }

    @Override
    @Transactional
    public Mono<Void> deleteByBranchId(Long id) {
        return repository.deleteByBranchId(id);
    }

    private BranchProduct toDto(BranchProductData data) {
        return BranchProduct.builder()
                .id(data.getId())
                .productId(data.getProductId())
                .branchId(data.getBranchId())
                .build();
    }

    private BranchProductData toEntity(BranchProduct data) {
        return BranchProductData.builder()
                .id(data.getId())
                .productId(data.getProductId())
                .branchId(data.getBranchId())
                .build();
    }
}
