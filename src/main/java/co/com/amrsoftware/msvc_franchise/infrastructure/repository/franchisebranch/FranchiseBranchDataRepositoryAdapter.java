package co.com.amrsoftware.msvc_franchise.infrastructure.repository.franchisebranch;

import co.com.amrsoftware.msvc_franchise.domain.model.franchisebranch.FranchiseBranch;
import co.com.amrsoftware.msvc_franchise.domain.model.franchisebranch.gateways.FranchiseBranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class FranchiseBranchDataRepositoryAdapter implements FranchiseBranchRepository {
    private final FranchiseBranchDataRepository repository;

    @Override
    @Transactional
    public Mono<Boolean> existsByFranchiseIdAndBranchId(Long franchiseId, Long branchId) {
        return repository.existsByFranchiseIdAndBranchId(franchiseId, branchId);
    }

    @Override
    @Transactional
    public Mono<FranchiseBranch> save(FranchiseBranch franchiseBranch) {
        return repository.save(this.toEntity(franchiseBranch)).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FranchiseBranch> findAllByFranchiseId(Long id) {
        return repository.findAllByFranchiseId(id).map(this::toDto);
    }

    private FranchiseBranch toDto(FranchiseBranchData data) {
        return FranchiseBranch.builder()
                .id(data.getId())
                .franchiseId(data.getFranchiseId())
                .branchId(data.getBranchId())
                .build();
    }

    private FranchiseBranchData toEntity(FranchiseBranch data) {
        return FranchiseBranchData.builder()
                .id(data.getId())
                .franchiseId(data.getFranchiseId())
                .branchId(data.getBranchId())
                .build();
    }
}
