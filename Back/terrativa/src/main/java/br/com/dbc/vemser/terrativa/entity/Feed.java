//package br.com.dbc.vemser.terrativa.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Feed {
//
//   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   @Column(name = "terreno_id")
//   private int terrenoId;
//
//   @Column(name = "titulo")
//   private String titulo;
//
//   @Column(name = "descricao")
//   private String descricao;
//
//   @Column(name = "preco")
//   private double preco;
//
//   @Column(name = "tamanho")
//   private int tamanho;
//
//   @Column(name = "nome_estado")
//   private String estado;
//
//   @Column(name = "nome_municipio")
//   private String cidade;
//
//   @Column(name = "estado_cod")
//   private Integer cod_estado;
//
//   private String quantidade;
//
//   @ManyToOne
//   @JoinColumn(name = "estado_cod", referencedColumnName = "estado_cod")
//   private EstadosMunicipios estadosMunicipios;
//}
