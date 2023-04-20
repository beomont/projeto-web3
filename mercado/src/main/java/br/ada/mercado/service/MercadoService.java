package br.ada.mercado.service;

import br.ada.mercado.model.Mercado;
import br.ada.mercado.repository.MercadoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class MercadoService {

    private MercadoRepository repository;

    public MercadoService(MercadoRepository repository) {
        this.repository = repository;
    }

    public Mono<Mercado> salvar(Mercado mercado) {
        return repository.save(mercado);
    }

    public Flux<Mercado> listar() {
        return repository.findAll();
    }

    public Mono<Mercado> atualizar(Mercado mercado, String id) {
        return repository.findById(id)
                .flatMap(atual -> repository.save(atual.update(mercado)));
    }

    public Mono<?> remover(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("mercado not found id " + id)))
                .flatMap(u -> repository.deleteById(id))
                .then();
    }

    public Mono<Mercado> buscarPorId(String id) {
        return repository.findById(id);
    }

//    public Flux<Mercado> buscarPorMercadonames(String... mercados) {
//        return repository.findByMercadonameIn(Arrays.asList(mercados));
//    }

}
