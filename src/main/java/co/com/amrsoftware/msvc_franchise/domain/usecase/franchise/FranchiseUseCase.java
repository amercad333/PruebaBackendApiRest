package co.com.amrsoftware.msvc_franchise.domain.usecase.franchise;

import co.com.amrsoftware.msvc_franchise.domain.model.branch.Branch;
import co.com.amrsoftware.msvc_franchise.domain.model.branch.BranchDetails;
import co.com.amrsoftware.msvc_franchise.domain.model.branch.gateways.BranchRepository;
import co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.BranchProduct;
import co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.gategays.BranchProductRepository;
import co.com.amrsoftware.msvc_franchise.domain.model.franchise.Franchise;
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

import java.util.ArrayList;
import java.util.List;

import static co.com.amrsoftware.msvc_franchise.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_EXISTING_BRANCHE;
import static co.com.amrsoftware.msvc_franchise.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_FOUND_FRANCHISE;

@RequiredArgsConstructor
public class FranchiseUseCase {
    private final FranchiseRepository repository;
    private final FranchiseBranchRepository franchiseBranchRepository;
    private final BranchRepository branchRepository;
    private final BranchProductRepository branchProductRepository;
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

    public Mono<Franchise> details(Long id) {
        var add1 = new ArrayList<>();
        return repository.findById(id).flatMap(franchiseDB -> {
            return franchiseBranchRepository.findAllByFranchiseId(franchiseDB.getId()).map(FranchiseBranch::getBranchId)
                .collectList().flatMap(branchIds -> {
               return branchRepository.findAllById(branchIds).collectList().map(branches -> {
                    franchiseDB.setBranches(branches);
                    return franchiseDB;

               });
            });
        }).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND_FRANCHISE))
        );
    }

    public Mono<Franchise> updateById(Long id, Franchise franchise) {
        return repository.findById(id).flatMap(franchiseDB -> {
            franchiseDB.setName(franchise.getName());
            return repository.save(franchiseDB);
        }).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND_FRANCHISE))
        );
    }

    private List<BranchDetails> branchDetails(List<Branch> branchs, List<Product> products) {
         return branchs.stream().map(branch ->
            BranchDetails.builder().id(branch.getId())
            .name(branch.getName()).products(products).build()).toList();
    }

    public Mono<Branch> setProduct(Long branchId) {
        return branchRepository.findById(branchId).flatMap(branchDB -> {
            return branchProductRepository.findByBranchId(branchId).map(BranchProduct::getProductId)
                    .collectList()
                    .flatMap(ids -> {
                        return productRepository.findAllById(ids).collectList().map(productDB -> {
                            branchDB.setProducts(productDB);
                            return branchDB;
                        });
                    });
        });
    }

    public Flux<Product> setProd(List<Long> ids) {
        return productRepository.findAllById(ids);
    }
    
    
}
