package co.com.amrsoftware.msvc_franchise.infrastructure.controller.franchise;

import co.com.amrsoftware.msvc_franchise.domain.model.branch.Branch;
import co.com.amrsoftware.msvc_franchise.domain.model.franchise.Franchise;
import co.com.amrsoftware.msvc_franchise.domain.usecase.franchise.FranchiseUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/franchises")
@RequiredArgsConstructor
public class FranchiseController {
    private final FranchiseUseCase useCase;

    @GetMapping(API)
    public Flux<Franchise> findAll() {
        return useCase.findAll();
    }

    @PostMapping(API)
    public ResponseEntity<Mono<Franchise>> save(@Valid @RequestBody Franchise franchise) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.save(franchise));
    }

    @PostMapping(API + "/assign/{id}")
    public ResponseEntity<Mono<Franchise>> assignBranch(@Valid @RequestBody Branch branch, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.createBranch(branch, id));
    }

    @GetMapping(API + "/{id}")
    public ResponseEntity<Mono<Franchise>> details(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.details(id));
    }

    @PutMapping(API + "/{id}")
    public ResponseEntity<Mono<Franchise>> updateById(@PathVariable Long id, @Valid @RequestBody Franchise franchise) {
        return ResponseEntity.ok(useCase.updateById(id, franchise));
    }
}
