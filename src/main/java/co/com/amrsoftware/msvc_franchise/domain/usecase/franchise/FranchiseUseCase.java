package co.com.amrsoftware.msvc_franchise.domain.usecase.franchise;

import co.com.amrsoftware.msvc_franchise.domain.model.branch.Branch;
import co.com.amrsoftware.msvc_franchise.domain.model.branch.BranchDetails;
import co.com.amrsoftware.msvc_franchise.domain.model.branch.gateways.BranchRepository;
import co.com.amrsoftware.msvc_franchise.domain.model.franchise.Franchise;
import co.com.amrsoftware.msvc_franchise.domain.model.franchise.FranchiseDetails;
import co.com.amrsoftware.msvc_franchise.domain.model.franchise.gategays.FranchiseRepository;
import co.com.amrsoftware.msvc_franchise.domain.model.franchisebranch.FranchiseBranch;
import co.com.amrsoftware.msvc_franchise.domain.model.franchisebranch.gateways.FranchiseBranchRepository;
import co.com.amrsoftware.msvc_franchise.domain.model.product.Product;
import co.com.amrsoftware.msvc_franchise.domain.model.product.gateways.ProductRepository;
import co.com.amrsoftware.msvc_franchise.domain.usecase.exception.ObjectNotExistingException;
import co.com.amrsoftware.msvc_franchise.domain.usecase.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.com.amrsoftware.msvc_franchise.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_EXISTING_BRANCHE;
import static co.com.amrsoftware.msvc_franchise.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_FOUND_FRANCHISE;

@RequiredArgsConstructor
public class FranchiseUseCase {
    private final FranchiseRepository repository;
    private final FranchiseBranchRepository franchiseBranchRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public Flux<Franchise> findAll() {
        return repository.findAll();
    }

    public Mono<Franchise> save(Franchise franchise) {
        franchise.setId(null);
        return repository.save(franchise);
    }

    public Mono<Franchise> createBranch(Branch branch, Long id) {
        return repository.findById(id).flatMap(franchiseDB -> {

            return branchRepository.save(branch).flatMap(branchDB -> {
                franchiseDB.setBranches(List.of(branchDB));

                return franchiseBranchRepository
                    .existsByFranchiseIdAndBranchId(franchiseDB.getId(), id).flatMap(existsFranchiseAndBranch -> {

                        if (existsFranchiseAndBranch) {
                            throw new ObjectNotExistingException(MESSAGE_OBJECT_NOT_EXISTING_BRANCHE);
                        }

                        var franchiseBranch = FranchiseBranch.builder()
                                .franchiseId(franchiseDB.getId())
                                .branchId(branchDB.getId())
                                .build();

                        return franchiseBranchRepository.save(franchiseBranch).map(franchiseBranchDB -> {
                            return franchiseDB;
                        });

                    });
            });

        }).switchIfEmpty(
              Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND_FRANCHISE))
          );
    }

    public Mono<FranchiseDetails> details(Long id) {
        return repository.findById(id).flatMap(franchiseDB -> {
            return franchiseBranchRepository.findAllByFranchiseId(id).collectList().flatMap(franchiseBranchesDB -> {
                var ids = franchiseBranchesDB.stream().map(FranchiseBranch::getBranchId).toList();
                return branchRepository.findAllById(ids).collectList().flatMap(branches -> {
                    var productIds = branches.stream().map(Branch::getId).toList();
                    return productRepository.findAllById(productIds).collectList().map(products -> {
                        return FranchiseDetails.builder().id(franchiseDB.getId())
                            .name(franchiseDB.getName())
                            .branches(branchDetails(branches, products)).build();
                    });

                });
            });
        }).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND_FRANCHISE))
        );
    }

    private List<BranchDetails> branchDetails(List<Branch> branchs, List<Product> products) {
         return branchs.stream().map(branch ->
            BranchDetails.builder().id(branch.getId())
            .name(branch.getName()).products(products).build()).toList();
    }
}
