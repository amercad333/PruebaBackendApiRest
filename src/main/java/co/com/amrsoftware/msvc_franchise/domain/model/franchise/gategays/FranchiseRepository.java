package co.com.amrsoftware.msvc_franchise.domain.model.franchise.gategays;

import co.com.amrsoftware.msvc_franchise.domain.model.franchise.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> save(Franchise franchise);
    Flux<Franchise> findAll();
    Mono<Franchise> findById(Long id);
}
