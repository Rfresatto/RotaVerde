package br.com.rota_verde.rota_verde.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email invalido")
        String email,

        @NotBlank(message = "senha obrigatória.")
        @Size(message = "Senha deve conter entre 6 e 20 caracteres.")
        String password
) {

}
