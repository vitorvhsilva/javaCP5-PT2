package br.com.leroymarcel.store.service;

import br.com.leroymarcel.store.dto.FerramentaRequest;
import br.com.leroymarcel.store.dto.FerramentaResponse;
import br.com.leroymarcel.store.entity.Ferramenta;
import br.com.leroymarcel.store.entity.RoleUsuario;
import br.com.leroymarcel.store.entity.Usuario;
import br.com.leroymarcel.store.exception.FerramentaNaoEncontradaException;
import br.com.leroymarcel.store.repository.FerramentaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FerramentaService {

    private final FerramentaRepository repository;

    public Ferramenta criarFerramenta(Ferramenta ferramenta) {
        return repository.save(ferramenta);
    }

    public List<FerramentaResponse> listarFerramentasParaUsuario(Usuario logado) {
        List<Ferramenta> ferramentas;
        if (logado.getRoleUsuario() == RoleUsuario.ROLE_ADMIN) {
            ferramentas = repository.findAll();
        } else {
            ferramentas = repository.findByUsuario(logado);
        }
        return ferramentas.stream()
                .map(FerramentaResponse::new)
                .toList();
    }

    public Ferramenta obterFerramentaPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new FerramentaNaoEncontradaException("Ferramenta não encontrada com o ID: " + id));
    }

    public void deletarFerramentaPorId(Ferramenta f, Usuario logado) {
        if (!logado.getRoleUsuario().equals(RoleUsuario.ROLE_ADMIN) &&
                !f.getUsuario().getIdUsuario().equals(logado.getIdUsuario())) {
            throw new SecurityException("Não autorizado");
        }
        repository.delete(f);
    }

    @Transactional
    public Ferramenta atualizarFerramenta(Ferramenta f, FerramentaRequest request, Usuario logado) {
        if (!logado.getRoleUsuario().equals(RoleUsuario.ROLE_ADMIN) &&
                !f.getUsuario().getIdUsuario().equals(logado.getIdUsuario())) {
            throw new SecurityException("Não autorizado");
        }

        if (request.getNomeFerramenta() != null) f.setNomeFerramenta(request.getNomeFerramenta());
        if (request.getTipoFerramenta() != null) f.setTipoFerramenta(request.getTipoFerramenta());
        if (request.getClassificacaoFerramenta() != null) f.setClassificacaoFerramenta(request.getClassificacaoFerramenta());
        if (request.getTamanhoFerramenta() != 0) f.setTamanhoFerramenta(request.getTamanhoFerramenta());
        if (request.getPrecoFerramenta() != 0) f.setPrecoFerramenta(request.getPrecoFerramenta());

        return f;
    }
}
