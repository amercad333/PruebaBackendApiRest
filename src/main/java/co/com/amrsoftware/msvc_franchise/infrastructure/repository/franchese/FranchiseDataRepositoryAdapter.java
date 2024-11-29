package co.com.amrsoftware.msvc_franchise.infrastructure.repository.franchese;

import co.com.amrsoftware.msvc_franchise.domain.model.franchise.Franchise;
import co.com.amrsoftware.msvc_franchise.domain.model.franchise.gategays.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class FranchiseDataRepositoryAdapter implements FranchiseRepository {
    private final FranchiseDataRepository repository;

    @Override
    @Transactional
    public Mono<Franchise> save(Franchise franchise) {
        return repository.save(this.toEntity(franchise)).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Franchise> findAll() {
        return repository.findAll().map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Franchise> findById(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    private Franchise toDto(FranchiseData data) {
        return Franchise.builder()
            .id(data.getId())
            .name(data.getName())
            .build();
    }

    private FranchiseData toEntity(Franchise data) {
        return FranchiseData.builder()
            .id(data.getId())
            .name(data.getName())
            .build();
    }
}
