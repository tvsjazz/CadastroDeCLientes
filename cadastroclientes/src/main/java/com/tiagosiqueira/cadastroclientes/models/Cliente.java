package com.tiagosiqueira.cadastroclientes.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private String sexo;

    @Email(message = "email inválido")
    private String email;

    @Column(nullable = false, name = "data_nascimento")
    private LocalDate dataNascimento;
    private String naturalidade;
    private String nacionalidade;

    @Column(nullable = false, unique = true)
    @CPF(message = "CPF inválido")
    private String cpf;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;

}
