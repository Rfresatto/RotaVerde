package br.com.rota_verde.rota_verde.user.dto;

import br.com.rota_verde.rota_verde.user.model.UserModel;

public record UserDefaultViewDTO(Long userId, String name, String email) {
    public UserDefaultViewDTO(UserModel userModel) {
        this(userModel.getUserId(), userModel.getName(), userModel.getEmail());
    }
}
