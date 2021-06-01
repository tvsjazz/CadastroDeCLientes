package com.tiagosiqueira.cadastroclientes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;

    @NotEmpty(message = "Nome é obrigatório!")
    @Size(min = 2, max = 100)
    private String nome;

    @Size(max = 9)
    private String sexo;

    @Size(max = 100)
    @Email(message = "E-mail inválido!")
    private String email;

    @NotEmpty(message = "Data de nascimento é obrigatório!")
    @Size(max = 10)
    private String dataNascimento;

    @Size(max = 200)
    private String naturalidade;

    @Size(max = 200)
    private String nacionalidade;

    @NotEmpty(message = "CPF é obrigatório!")
    @Size(max = 14)
    @CPF(message = "CPF inválido!")
    private String cpf;

    private String dataCadastro;

    private String dataAtualizacao;

}
