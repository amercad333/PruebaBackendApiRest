package co.com.amrsoftware.msvc_franchise.infrastructure.controller.branch;

import co.com.amrsoftware.msvc_franchise.domain.model.branch.Branch;
import co.com.amrsoftware.msvc_franchise.domain.model.product.Product;
import co.com.amrsoftware.msvc_franchise.domain.usecase.branch.BranchUseCase;
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
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchUseCase useCase;

    @GetMapping(API)
    public Flux<Branch> findAll() {
        return useCase.findAll();
    }

    @GetMapping(API + "/{id}")
    public ResponseEntity<Mono<Branch>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.findById(id));
    }

    @PostMapping(API)
    public ResponseEntity<Mono<Branch>> save(@Valid @RequestBody Branch branch) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.save(branch));
    }

    @PostMapping(API + "/assign/{id}")
    public ResponseEntity<Mono<Branch>> assignProduct(@Valid @RequestBody Product product, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.createProduct(product, id));
    }

    @DeleteMapping(API + "/delete/{branchId}/{productId}")
    public ResponseEntity<Mono<Void>> deleteProduct(@PathVariable Long branchId, @PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(useCase.deleteProduct(branchId, productId));
    }

    @PutMapping(API + "/{id}")
    public ResponseEntity<Mono<Branch>> updateById(@PathVariable Long id, @Valid @RequestBody Branch branch) {
        return ResponseEntity.ok(useCase.updateById(id, branch));
    }
}
