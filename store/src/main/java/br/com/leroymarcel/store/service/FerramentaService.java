package br.com.leroymarcel.store.service;

import br.com.leroymarcel.store.dto.FerramentaRequest;
import br.com.leroymarcel.store.dto.FerramentaResponse;
import br.com.leroymarcel.store.entity.Ferramenta;
import br.com.leroymarcel.store.exception.FerramentaNaoEncontradaException;
import br.com.leroymarcel.store.repository.FerramentaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FerramentaService {

    private final FerramentaRepository repository;

    public Ferramenta criarFerramenta(Ferramenta ferramenta) {
        return repository.save(ferramenta);
    }

    public List<FerramentaResponse> listarFerramentas() {
        try {
            return repository.findAll()
                    .stream()
                    .map(ferramenta -> new FerramentaResponse(
                            ferramenta.getIdFerramenta(),
                            ferramenta.getNomeFerramenta(),
                            ferramenta.getTipoFerramenta(),
                            ferramenta.getClassificacaoFerramenta(),
                            ferramenta.getTamanhoFerramenta(),
                            ferramenta.getPrecoFerramenta()
                    ))
                    .toList();
        } catch (Exception e) {
            System.out.println("Houve um erro ao tentar resgatar todos as ferramentas: ");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Ferramenta obterFerramentaPorId(String id) {
        return repository.findById(id).orElseThrow(() -> new FerramentaNaoEncontradaException("Ferramenta não encontrado com o ID: " + id));
    }

    public void deletarFerramentaPorId(String id) {
        if (!repository.existsById(id)) {
            throw new FerramentaNaoEncontradaException("Ferramenta não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public Ferramenta atualizarFerramenta(String id, FerramentaRequest request) {

        Ferramenta ferramentaExistente = obterFerramentaPorId(id);

        if (request.getNomeFerramenta() != null)
            ferramentaExistente.setNomeFerramenta(request.getNomeFerramenta());

        if (request.getTipoFerramenta() != null)
            ferramentaExistente.setTipoFerramenta(request.getTipoFerramenta());

        if (request.getClassificacaoFerramenta() != null)
            ferramentaExistente.setClassificacaoFerramenta(request.getClassificacaoFerramenta());

        if (request.getTamanhoFerramenta() != 0)
            ferramentaExistente.setTamanhoFerramenta(request.getTamanhoFerramenta());

        if (request.getPrecoFerramenta() != 0)
            ferramentaExistente.setPrecoFerramenta(request.getPrecoFerramenta());

        System.out.println("Ferramenta: " + ferramentaExistente.getIdFerramenta() + ", atualizada com sucesso para: "
                + ferramentaExistente.getNomeFerramenta() + " " + ferramentaExistente.getTipoFerramenta() + ", "
                + ferramentaExistente.getClassificacaoFerramenta() + ", " + ferramentaExistente.getTamanhoFerramenta() + ", "
                + ferramentaExistente.getPrecoFerramenta());
        return ferramentaExistente;
    }
}
