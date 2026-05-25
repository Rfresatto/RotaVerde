package br.com.rota_verde.rota_verde.user.dto;

import br.com.rota_verde.rota_verde.user.model.UserModel;
import br.com.rota_verde.rota_verde.user.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        Long userId,
        @NotBlank(message = "O nome do usuário é obrigatório")
        String name,
        @NotBlank(message = "O E-mail do usuário é obrigatório")
        @Email(message = "O E-mail do usuário é obrigatório")
        String email,
        @NotBlank(message = "A senha não pode estar vazia.")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres.")
        String password,

        UserRole role
) {


    public CreateUserDTO(UserModel userModel) {
        this(userModel.getUserId(), userModel.getName(), userModel.getEmail(), userModel.getPassword(), userModel.getRole()
        );
    }
}
