package br.com.leroymarcel.store.service;

import br.com.leroymarcel.store.entity.RoleUsuario;
import br.com.leroymarcel.store.entity.Usuario;
import br.com.leroymarcel.store.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = repo.findByEmailUsuario(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new User(
                u.getEmailUsuario(),
                u.getSenhaUsuario(),
                true, true, true, true,
                List.of(new SimpleGrantedAuthority(u.getRoleUsuario().name()))
        );
    }


    public Usuario register(String nome, String email, String senha, String telefone, String role) {
        String emailNormalizado = email.toLowerCase().trim();
        if (repo.existsByEmailUsuario(emailNormalizado)) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        Usuario u = new Usuario();
        u.setNomeCompletoUsuario(nome);
        u.setEmailUsuario(emailNormalizado);
        u.setSenhaUsuario(encoder.encode(senha));
        u.setTelefoneUsuario(telefone);

        if (role.equalsIgnoreCase("ADMIN")) {
            u.setRoleUsuario(RoleUsuario.ROLE_ADMIN);
        } else {
            u.setRoleUsuario(RoleUsuario.ROLE_USER);
        }

        return repo.save(u);
    }
}