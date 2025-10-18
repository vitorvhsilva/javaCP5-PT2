package br.com.leroymarcel.store.controller;

import br.com.leroymarcel.store.dto.FerramentaRequest;
import br.com.leroymarcel.store.dto.FerramentaResponse;
import br.com.leroymarcel.store.entity.Ferramenta;
import br.com.leroymarcel.store.exception.FerramentaNaoEncontradaException;
import br.com.leroymarcel.store.service.FerramentaService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ferramentas")
public class FerramentaControllerView {

    private final FerramentaService ferramentaService;

    public FerramentaControllerView(FerramentaService ferramentaService) {
        this.ferramentaService = ferramentaService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public String listar(Model model,
                         @ModelAttribute("mensagem") String mensagem,
                         @ModelAttribute("erro") String erro) {

        List<FerramentaResponse> ferramentas = ferramentaService.listarFerramentas();
        model.addAttribute("ferramentas", ferramentas);
        return "ferramenta/listar";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/novo")
    public String formNovo(Model model) {
        model.addAttribute("ferramenta", new FerramentaRequest());
        model.addAttribute("title", "Cadastrar Ferramenta");
        model.addAttribute("modo", "criar");
        return "ferramenta/adicionar";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping
    public String criar(@Valid @ModelAttribute("ferramenta") FerramentaRequest request,
                        BindingResult result,
                        RedirectAttributes ra,
                        Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Cadastrar Ferramenta");
            model.addAttribute("modo", "criar");
            return "ferramenta/adicionar";
        }

        Ferramenta novo = Ferramenta.builder()
                .nomeFerramenta(request.getNomeFerramenta())
                .tipoFerramenta(request.getTipoFerramenta())
                .classificacaoFerramenta(request.getClassificacaoFerramenta())
                .tamanhoFerramenta(request.getTamanhoFerramenta())
                .precoFerramenta(request.getPrecoFerramenta())
                .build();

        Ferramenta salvo = ferramentaService.criarFerramenta(novo);
        ra.addFlashAttribute("mensagem", "Ferramenta cadastrada com sucesso (ID: " + salvo.getIdFerramenta() + ").");
        return "redirect:/ferramentas";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public String detalhes(@PathVariable String id, Model model, RedirectAttributes ra) {
        try {
            Ferramenta ferramenta = ferramentaService.obterFerramentaPorId(id);
            model.addAttribute("ferramenta", ferramenta);
            return "ferramenta/editarFerramenta";
        } catch (FerramentaNaoEncontradaException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            return "redirect:/ferramentas";
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable String id, Model model, RedirectAttributes ra) {
        try {
            Ferramenta ferramenta = ferramentaService.obterFerramentaPorId(id);
            FerramentaRequest form = new FerramentaRequest(
                    ferramenta.getNomeFerramenta(),
                    ferramenta.getTipoFerramenta(),
                    ferramenta.getClassificacaoFerramenta(),
                    ferramenta.getTamanhoFerramenta(),
                    ferramenta.getPrecoFerramenta()
            );
            model.addAttribute("idFerramenta", ferramenta.getIdFerramenta());
            model.addAttribute("ferramenta", form);
            model.addAttribute("title", "Editar Ferramenta");
            model.addAttribute("modo", "editar");
            return "ferramenta/editarFerramenta";
        } catch (FerramentaNaoEncontradaException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            return "redirect:/ferramentas";
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}")
    public String atualizar(@PathVariable String id,
                            @Valid @ModelAttribute("ferramenta") FerramentaRequest request,
                            BindingResult result,
                            RedirectAttributes ra,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("idFerramenta", id);
            model.addAttribute("title", "Editar Ferramenta");
            model.addAttribute("modo", "editar");
            return "ferramenta/editarFerramenta";
        }
        try {
            Ferramenta atualizado = ferramentaService.atualizarFerramenta(id, request);
            ra.addFlashAttribute("mensagem", "Ferramenta atualizada com sucesso (ID: " + atualizado.getIdFerramenta() + ").");
            return "redirect:/ferramentas";
        } catch (FerramentaNaoEncontradaException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            return "redirect:/ferramentas";
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable String id, RedirectAttributes ra) {
        try {
            ferramentaService.deletarFerramentaPorId(id);
            ra.addFlashAttribute("sucesso", "Ferramenta excluída com sucesso");
        } catch (FerramentaNaoEncontradaException e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/ferramentas";
    }
}
