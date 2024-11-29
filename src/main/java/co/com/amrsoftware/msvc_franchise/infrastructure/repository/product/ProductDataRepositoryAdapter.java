package co.com.amrsoftware.msvc_franchise.infrastructure.repository.product;

import co.com.amrsoftware.msvc_franchise.domain.model.product.Product;
import co.com.amrsoftware.msvc_franchise.domain.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDataRepositoryAdapter implements ProductRepository {
    private final ProductDataRepository repository;

    @Override
    public Mono<Product> save(Product product) {
        return repository.save(this.toEntity(product)).map(this::toDto);
    }

    @Override
    public Mono<Product> findById(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Product> findAllById(List<Long> ids) {
        return repository.findAllById(ids).map(this::toDto);
    }

    @Override
    @Transactional
    public Mono<Void> deletById(Long id) {
        return repository.deleteById(id);
    }

    private Product toDto(ProductData data) {
        return Product.builder()
            .id(data.getId())
            .name(data.getName())
            .quantity(data.getQuantity())
            .build();
    }

    private ProductData toEntity(Product data) {
        return ProductData.builder()
                .id(data.getId())
                .name(data.getName())
                .quantity(data.getQuantity())
                .build();
    }
}
