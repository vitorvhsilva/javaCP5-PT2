package br.com.leroymarcel.store.controller;

import br.com.leroymarcel.store.dto.FerramentaRequest;
import br.com.leroymarcel.store.dto.FerramentaResponse;
import br.com.leroymarcel.store.entity.Ferramenta;
import br.com.leroymarcel.store.entity.Usuario;
import br.com.leroymarcel.store.exception.FerramentaNaoEncontradaException;
import br.com.leroymarcel.store.service.FerramentaService;
import br.com.leroymarcel.store.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ferramentas")
@AllArgsConstructor
public class FerramentaControllerView {

    private final FerramentaService ferramentaService;
    private final UsuarioService usuarioService;

    private Usuario getUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return usuarioService.buscarPorEmail(auth.getName());
    }

    @GetMapping
    public String listar(Model model) {
        Usuario logado = getUsuarioLogado();
        List<FerramentaResponse> ferramentas = ferramentaService.listarFerramentasParaUsuario(logado);
        model.addAttribute("ferramentas", ferramentas);
        return "ferramenta/listar";
    }

    @GetMapping("/novo")
    public String formNovo(Model model) {
        model.addAttribute("ferramenta", new FerramentaRequest());
        model.addAttribute("title", "Cadastrar Ferramenta");
        model.addAttribute("modo", "criar");
        return "ferramenta/adicionar";
    }

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

        Usuario logado = getUsuarioLogado();

        Ferramenta novo = Ferramenta.builder()
                .nomeFerramenta(request.getNomeFerramenta())
                .tipoFerramenta(request.getTipoFerramenta())
                .classificacaoFerramenta(request.getClassificacaoFerramenta())
                .tamanhoFerramenta(request.getTamanhoFerramenta())
                .precoFerramenta(request.getPrecoFerramenta())
                .usuario(logado) 
                .build();

        Ferramenta salvo = ferramentaService.criarFerramenta(novo);
        ra.addFlashAttribute("mensagem", "Ferramenta cadastrada com sucesso (ID: " + salvo.getIdFerramenta() + ").");
        return "redirect:/ferramentas";
    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable String id, Model model, RedirectAttributes ra) {
        try {
            Ferramenta f = ferramentaService.obterFerramentaPorId(id);
            FerramentaRequest form = new FerramentaRequest(
                    f.getNomeFerramenta(),
                    f.getTipoFerramenta(),
                    f.getClassificacaoFerramenta(),
                    f.getTamanhoFerramenta(),
                    f.getPrecoFerramenta()
            );
            model.addAttribute("idFerramenta", f.getIdFerramenta());
            model.addAttribute("ferramenta", form);
            model.addAttribute("title", "Editar Ferramenta");
            model.addAttribute("modo", "editar");
            return "ferramenta/editarFerramenta";
        } catch (FerramentaNaoEncontradaException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            return "redirect:/ferramentas";
        }
    }

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
            Ferramenta f = ferramentaService.obterFerramentaPorId(id);
            ferramentaService.atualizarFerramenta(f, request, getUsuarioLogado());
            ra.addFlashAttribute("mensagem", "Ferramenta atualizada com sucesso (ID: " + f.getIdFerramenta() + ").");
            return "redirect:/ferramentas";
        } catch (FerramentaNaoEncontradaException | SecurityException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            return "redirect:/ferramentas";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable String id, RedirectAttributes ra) {
        try {
            Ferramenta f = ferramentaService.obterFerramentaPorId(id);
            ferramentaService.deletarFerramentaPorId(f, getUsuarioLogado());
            ra.addFlashAttribute("mensagem", "Ferramenta excluída com sucesso");
        } catch (FerramentaNaoEncontradaException | SecurityException e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/ferramentas";
    }
}
