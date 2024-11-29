package co.com.amrsoftware.msvc_franchise.domain.usecase.product;

import co.com.amrsoftware.msvc_franchise.domain.model.branchproduct.gategays.BranchProductRepository;
import co.com.amrsoftware.msvc_franchise.domain.model.product.Product;
import co.com.amrsoftware.msvc_franchise.domain.model.product.ProductUpdate;
import co.com.amrsoftware.msvc_franchise.domain.model.product.gateways.ProductRepository;
import co.com.amrsoftware.msvc_franchise.domain.usecase.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static co.com.amrsoftware.msvc_franchise.domain.usecase.util.constnt.Constant.MESSAGE_OBJECT_NOT_FOUND;

@RequiredArgsConstructor
public class ProductUseCase {
    private final ProductRepository repository;
    private final BranchProductRepository branchProductRepository;

    public Flux<Product> findAll() {
        return repository.findAll();
    }

    public Mono<Product> findById(Long id) {
        return repository.findById(id).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
        );
    }

    public Mono<Product> save(Product product) {
        product.setId(null);
        return repository.save(product);
    }

    public Mono<Void> deleteById(Long id) {
        return repository.findById(id).flatMap(productDB -> {
            branchProductRepository.deleteByProductId(productDB.getId());
            repository.deletById(id).subscribe();
            return Mono.just(true);
        }).switchIfEmpty(
                Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
        ).then();
    }

    public Mono<Product> updateByIdQuantity(Long id, ProductUpdate productUpdate) {
        return repository.findById(id).flatMap(productDB -> {
            productDB.setQuantity(productUpdate.getQuantity());
            return repository.save(productDB);
        }).switchIfEmpty(
            Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
        );
    }

    public Mono<Product> updateById(Long id, Product product) {
        return repository.findById(id).flatMap(productDB -> {
            productDB.setName(product.getName());
            productDB.setQuantity(product.getQuantity());
            return repository.save(productDB);
        }).switchIfEmpty(
                Mono.error(() -> new ObjectNotFoundException(MESSAGE_OBJECT_NOT_FOUND))
        );
    }
}
