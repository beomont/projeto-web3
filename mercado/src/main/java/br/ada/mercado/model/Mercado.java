package br.ada.mercado.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Mercado {
    @Id
    private String id;

    private String nome;
    private String moeda;
    private Double saldo;


    public Mercado update(Mercado mercado) {
        // id, username and since not permited changing
        this.setNome(mercado.nome);
        this.setMoeda(mercado.moeda);
        this.setSaldo(mercado.saldo);
        return this;
    }

}
