package co.com.amrsoftware.msvc_franchise.domain.model.product.gateways;

import co.com.amrsoftware.msvc_franchise.domain.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductRepository {
    Mono<Product> save(Product product);
    Mono<Product> findById(Long id);
    Flux<Product> findAllById(List<Long> ids);
    Mono<Void> deletById(Long id);
}
