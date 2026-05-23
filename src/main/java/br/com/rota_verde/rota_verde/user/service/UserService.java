package br.com.rota_verde.rota_verde.user.service;

import br.com.rota_verde.rota_verde.exception.UserNotFoundException;
import br.com.rota_verde.rota_verde.user.dto.UserDefaultViewDTO;
import br.com.rota_verde.rota_verde.user.dto.UserSingInDTO;
import br.com.rota_verde.rota_verde.user.model.UserModel;
import br.com.rota_verde.rota_verde.user.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDefaultViewDTO saveUser(UserSingInDTO userSingInDTO) {
        UserModel user = new UserModel();
        BeanUtils.copyProperties(userSingInDTO, user);
        UserModel userSaved = userRepository.save(user);
        return new UserDefaultViewDTO(userSaved);
    }

    public UserDefaultViewDTO findUser(Long oid) {
        Optional<UserModel> optionalUserModel = userRepository.findById(oid);

        if (optionalUserModel.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        } else {
            return new UserDefaultViewDTO(optionalUserModel.get());
        }
    }

    public List<UserDefaultViewDTO> findManyUsers() {
        return userRepository.findAll().stream().map(UserDefaultViewDTO::new).toList();
    }

    public void deleteUser(Long oid) {
        Optional<UserModel> optionalUserModel = userRepository.findById(oid);

        if (optionalUserModel.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        } else {
            userRepository.delete(optionalUserModel.get());
        }
    }

    public UserModel updateUser(UserModel userModel) {
        Optional<UserModel> optionalUserModel = userRepository.findById(userModel.getUserOid());

        if (optionalUserModel.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        } else {
            return userRepository.save(userModel);
        }
    }
}
