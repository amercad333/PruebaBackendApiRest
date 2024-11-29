package co.com.amrsoftware.msvc_franchise.infrastructure.repository.branch;

import co.com.amrsoftware.msvc_franchise.domain.model.branch.Branch;
import co.com.amrsoftware.msvc_franchise.domain.model.branch.gateways.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BranchDataRepositoryAdapter implements BranchRepository {
    private final BranchDataRepository repository;

    @Override
    @Transactional
    public Mono<Branch> save(Branch branch) {
        return repository.save(this.toEntity(branch)).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Branch> findAllById(List<Long> ids) {
        return repository.findAllById(ids).map(this::toDto);
    }

    @Override
    @Transactional
    public Flux<Branch> findAll() {
        return repository.findAll().map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Branch> findById(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    @Override
    @Transactional
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    private Branch toDto(BranchData data) {
        return Branch.builder()
                .id(data.getId())
                .name(data.getName())
                .build();
    }

    private BranchData toEntity(Branch data) {
        return BranchData.builder()
                .id(data.getId())
                .name(data.getName())
                .build();
    }
}
