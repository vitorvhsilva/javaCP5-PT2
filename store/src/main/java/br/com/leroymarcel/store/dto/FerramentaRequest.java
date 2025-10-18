package br.com.leroymarcel.store.dto;

import br.com.leroymarcel.store.entity.ClassificacaoFerramenta;
import br.com.leroymarcel.store.entity.TipoFerramenta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FerramentaRequest {

    @NotBlank(message = "O nome da ferramenta não pode ficar em branco")
    private String nomeFerramenta;

    @NotNull(message = "O tipo da ferramenta não pode ser nulo")
    private TipoFerramenta tipoFerramenta;

    @NotNull(message = "A classificação da ferramenta não pode ser nulo")
    private ClassificacaoFerramenta classificacaoFerramenta;
    
    @NotNull(message = "O tamanho da ferramenta não pode ficar em branco")
    private double tamanhoFerramenta;
    
    @NotNull(message = "O preço da ferramenta não pode ficar em branco")
    private double precoFerramenta;
}