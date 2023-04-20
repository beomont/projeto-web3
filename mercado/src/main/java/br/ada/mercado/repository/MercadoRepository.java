package br.ada.mercado.repository;

import br.ada.mercado.model.Mercado;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;


public interface MercadoRepository extends ReactiveMongoRepository<Mercado, String> {
//        Flux<Mercado> findByMercadonameIn(List<String> usernames);
}
