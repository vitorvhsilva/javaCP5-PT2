package br.com.leroymarcel.store.dto;

import br.com.leroymarcel.store.entity.ClassificacaoFerramenta;
import br.com.leroymarcel.store.entity.Ferramenta;
import br.com.leroymarcel.store.entity.RoleUsuario;
import br.com.leroymarcel.store.entity.TipoFerramenta;
import lombok.Data;

@Data
public class FerramentaResponse {
    private String idFerramenta;
    private String nomeFerramenta;
    private TipoFerramenta tipoFerramenta;
    private ClassificacaoFerramenta classificacaoFerramenta;
    private double tamanhoFerramenta;
    private double precoFerramenta;
    private String emailCriador;
    private RoleUsuario roleCriador;

    public FerramentaResponse(Ferramenta f) {
        this.idFerramenta = f.getIdFerramenta();
        this.nomeFerramenta = f.getNomeFerramenta();
        this.tipoFerramenta = f.getTipoFerramenta();
        this.classificacaoFerramenta = f.getClassificacaoFerramenta();
        this.tamanhoFerramenta = f.getTamanhoFerramenta();
        this.precoFerramenta = f.getPrecoFerramenta();
        this.emailCriador = f.getUsuario().getEmailUsuario();
        this.roleCriador = f.getUsuario().getRoleUsuario();
    }
}