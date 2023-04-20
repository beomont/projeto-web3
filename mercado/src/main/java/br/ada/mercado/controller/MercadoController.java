package br.ada.mercado.controller;

import br.ada.mercado.model.Mercado;
import br.ada.mercado.service.MercadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/mercados")
@Slf4j
public class MercadoController {

    private MercadoService service;

    public MercadoController(MercadoService mercadoService) {
        this.service = mercadoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Mercado>> salvar(@RequestBody Mercado mercado) {
        return service.salvar(mercado)
                .map(atual -> ResponseEntity.ok().body(atual));
    }

    @GetMapping // TODO nao retorna noContent quando lista vazia
    public Mono<ResponseEntity<Flux<Mercado>>> listar() {
        return service.listar()
                .collectList()
                .map(mercados -> ResponseEntity.ok().body(Flux.fromIterable(mercados)) )
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Mercado>> getById(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(atual -> ResponseEntity.ok().body(atual))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Mercado>> atualizar(@RequestBody MercadoRequest mercado, @PathVariable String id) {
        return service.atualizar(mercado.create(), id)
                .map(atual -> ResponseEntity.ok().body(atual))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> remover(@PathVariable String id) {
        return service.remover(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

}