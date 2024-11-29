package co.com.amrsoftware.msvc_franchise.domain.usecase.branch;

import co.com.amrsoftware.msvc_franchise.domain.model.branch.Branch;
import co.com.amrsoftware.msvc_franchise.domain.model.branch.gateways.BranchRepository;
import co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.BranchProduct;
import co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.gategays.BranchProductRepository;
import co.com.amrsoftware.msvc_franchise.domain.model.franchisebranch.FranchiseBranch;
import co.com.amrsoftware.msvc_franchise.domain.model.product.Product;
import co.com.amrsoftware.msvc_franchise.domain.model.product.gateways.ProductRepository;
import co.com.amrsoftware.msvc_franchise.domain.usecase.exception.ObjectNotExistingException;
import co.com.amrsoftware.msvc_franchise.domain.usecase.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.com.amrsoftware.msvc_franchise.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_EXISTING_BRANCHE;
import static co.com.amrsoftware.msvc_franchise.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_FOUND;
import static co.com.amrsoftware.msvc_franchise.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_FOUND_BRANCHE;

@RequiredArgsConstructor
public class BranchUseCase {
    private final BranchRepository repository;
    private final ProductRepository productRepository;
    private final BranchProductRepository branchProductRepository;

    public Flux<Branch> findAll() {
        return repository.findAll();
    }

    public Mono<Branch> save(Branch branch) {
        branch.setId(null);
        return repository.save(branch);
    }

    public Mono<Branch> createProduct(Product product, Long id) {
        return repository.findById(id).flatMap(branchDB -> {

            return productRepository.save(product).flatMap(productDB -> {
                branchDB.setProducts(List.of(productDB));

                return branchProductRepository
                        .existsByBranchIdAndProductId(productDB.getId(), id)
                            .flatMap(existsBranchAndProduct -> {

                            if (existsBranchAndProduct) {
                                throw new ObjectNotExistingException(MESSAGE_OBJECT_NOT_EXISTING_BRANCHE);
                            }

                            var branchProduct = BranchProduct.builder()
                                    .branchId(branchDB.getId())
                                    .productId(productDB.getId())
                                    .build();

                            return branchProductRepository.save(branchProduct).map(franchiseBranchDB -> {
                                return branchDB;
                            });

                        });
            });

        }).switchIfEmpty(
                Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND_BRANCHE))
        );
    }

    public Mono<Void> deleteProduct(Long branchId, Long productId) {
        return repository.findById(branchId).flatMap(branchDB -> {

            return productRepository.findById(productId).flatMap(productDB -> {
                branchProductRepository.deleteByBranchIdAndProductId(branchDB.getId(), productDB.getId()).subscribe();
                productRepository.deletById(productDB.getId()).subscribe();
                return   Mono.just(true);
            }).switchIfEmpty(
                Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
            );

        }).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND_BRANCHE))
        ).then();
    }
}
