package br.com.leroymarcel.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_TDS_CP5_FERRAMENTA")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Ferramenta {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID_FERRAMENTA")
    private String idFerramenta;

    @Column(name = "NOME_FERRAMENTA", nullable = false, length = 50)
    private String nomeFerramenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_FERRAMENTA", nullable = false, length = 50)
    private TipoFerramenta tipoFerramenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLASSIFICACAO_FERRAMENTA", nullable = false, length = 50)
    private ClassificacaoFerramenta classificacaoFerramenta;

    @Column(name = "TAMANHO_FERRAMENTA", nullable = false, length = 50)
    private double tamanhoFerramenta;

    @Column(name = "PRECO_FERRAMENTA", nullable = false, length = 50)
    private double precoFerramenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;
}