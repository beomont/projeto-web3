package br.ada.mercado.controller;

import br.ada.mercado.model.Mercado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MercadoRequest {

    private String nome;
    private String moeda;
    private Double saldo;
    public Mercado update(Mercado mercado) {
        // id, username and entrou not permited change
        mercado.setNome(this.nome);
        mercado.setMoeda(this.moeda);
        mercado.setSaldo(this.saldo);
        return mercado;
    }

    public Mercado create() {
        Mercado mercado = new Mercado();
        mercado.setNome(this.nome);
        mercado.setMoeda(this.moeda);
        mercado.setSaldo(this.saldo);
        return mercado;
    }

}