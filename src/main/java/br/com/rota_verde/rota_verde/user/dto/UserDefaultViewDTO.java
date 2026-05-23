package br.com.rota_verde.rota_verde.user.dto;

import br.com.rota_verde.rota_verde.user.model.UserModel;

public record UserDefaultViewDTO(Long userOid, String name, String email) {
    public UserDefaultViewDTO(UserModel userModel) {
        this(userModel.getUserOid(), userModel.getName(), userModel.getEmail());
    }
}
