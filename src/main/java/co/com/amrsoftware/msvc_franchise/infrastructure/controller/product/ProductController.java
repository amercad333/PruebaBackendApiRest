package co.com.amrsoftware.msvc_franchise.infrastructure.controller.product;

import co.com.amrsoftware.msvc_franchise.domain.model.product.Product;
import co.com.amrsoftware.msvc_franchise.domain.model.product.ProductUpdate;
import co.com.amrsoftware.msvc_franchise.domain.usecase.product.ProductUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static co.com.amrsoftware.msvc_franchise.infrastructure.controller.util.constant.Constant.API;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductUseCase useCase;

    @GetMapping(API)
    public Flux<Product> findAll() {
        return useCase.findAll();
    }

    @GetMapping(API + "/{id}")
    public ResponseEntity<Mono<Product>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.findById(id));
    }

    @PostMapping(API)
    public ResponseEntity<Mono<Product>> findById(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.save(product));
    }

    @PutMapping(API + "/{id}")
    public ResponseEntity<Mono<Product>> updateByIdQuantity(@Valid @RequestBody ProductUpdate product, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.updateByIdQuantity(id, product));
    }

    @PutMapping(API + "/all/{id}")
    public ResponseEntity<Mono<Product>> updateById(@Valid @RequestBody Product product, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.updateById(id, product));
    }

    @DeleteMapping(API + "/{id}")
    public ResponseEntity<Mono<Void>> deleteById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(useCase.deleteById(id));
    }
}
