package br.com.leroymarcel.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_TDS_CP5_USUARIO")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID_USUARIO", length = 36, nullable = false)
    private String idUsuario;

    @Column(name = "EMAIL_USUARIO", length = 190, nullable = false, unique = true)
    private String emailUsuario;

    @Column(name = "SENHA_USUARIO", length = 100, nullable = false)
    private String senhaUsuario;

    @Column(name = "NOME_USUARIO", length = 120, nullable = false)
    private String nomeCompletoUsuario;

    @Column(name = "TELEFONE_USUARIO", length = 20)
    private String telefoneUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_USUARIO", length = 40, nullable = false)
    private RoleUsuario roleUsuario = RoleUsuario.ROLE_USER;

    @Column(name="CREATED_AT", nullable=false)
    private LocalDateTime criadoEm = LocalDateTime.now();

}
