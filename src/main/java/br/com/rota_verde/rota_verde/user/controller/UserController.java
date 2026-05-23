package br.com.rota_verde.rota_verde.user.controller;

import br.com.rota_verde.rota_verde.user.dto.UserDefaultViewDTO;
import br.com.rota_verde.rota_verde.user.dto.UserSingInDTO;
import br.com.rota_verde.rota_verde.user.model.UserModel;
import br.com.rota_verde.rota_verde.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDefaultViewDTO> save(@RequestBody @Valid UserSingInDTO userSingInDTO) {
        try {
            UserDefaultViewDTO saved = userService.saveUser(userSingInDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDefaultViewDTO>> findMany() {
        try {
            return ResponseEntity.ok(userService.findManyUsers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userOid}")
    public ResponseEntity<UserDefaultViewDTO> find(@PathVariable Long userOid) {
            return ResponseEntity.ok(userService.findUser(userOid));
    }

    @DeleteMapping("/user/{userOid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long userOid) {
        try {
            userService.deleteUser(userOid);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/{userOid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserModel> update(@RequestBody UserModel userModel) {
        try {
            UserModel updated = userService.updateUser(userModel);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
