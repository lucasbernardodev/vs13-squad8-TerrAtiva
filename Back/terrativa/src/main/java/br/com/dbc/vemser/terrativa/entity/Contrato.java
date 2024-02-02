package br.com.dbc.vemser.terrativa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "CONTRATOS")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTRATO")
    @SequenceGenerator(name = "SEQ_CONTRATO", sequenceName = "SEQ_CONTRATO", allocationSize = 1)
    @Column(name = "CONTRATO_ID")
    private Integer id;

    @Column(name = "LOCATARIO_ID")
    private Integer locatarioID;

    @Column(name = "TERRENO_ID")
    private Integer terrenoID;

    @Column(name = "ATIVO")
    private String ativo;

    @Column(name = "DATA_ASSINATURA")
    private LocalDate dataAssinatura;

    @Column(name = "DATA_INICIO")
    private LocalDate dataInicio;

    @Column(name = "DATA_FINAL")
    private LocalDate dataFinal;

    @Column(name = "DIA_VENCIMENTO_ALUGUEL")
    private Integer dataVencimentoAluguel;

    @Column(name = "CRIADO")
    private String criado;

    @Column(name = "EDITADO")
    private String editado;
}