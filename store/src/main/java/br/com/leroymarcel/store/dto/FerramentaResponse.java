package br.com.leroymarcel.store.dto;

import br.com.leroymarcel.store.entity.ClassificacaoFerramenta;
import br.com.leroymarcel.store.entity.TipoFerramenta;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FerramentaResponse {
    private String idFerramenta;
    private String nomeFerramenta;
    private TipoFerramenta tipoFerramenta;
    private ClassificacaoFerramenta classificacaoFerramenta;
    private double tamanhoFerramenta;
    private double precoFerramenta;
}