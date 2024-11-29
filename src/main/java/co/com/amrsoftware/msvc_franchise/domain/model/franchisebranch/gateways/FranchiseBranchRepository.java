package co.com.amrsoftware.msvc_franchise.domain.model.franchisebranch.gateways;

import co.com.amrsoftware.msvc_franchise.domain.model.franchisebranch.FranchiseBranch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseBranchRepository {
    Mono<Boolean> existsByFranchiseIdAndBranchId(Long franchiseId, Long branchId);
    Mono<FranchiseBranch> save(FranchiseBranch franchiseBranch);
    Flux<FranchiseBranch> findAllByFranchiseId(Long id);
    Mono<Void> deleteByBranchId(Long branchId);
    Mono<Void> deleteByFranchiseId(Long franchiseId);
}
