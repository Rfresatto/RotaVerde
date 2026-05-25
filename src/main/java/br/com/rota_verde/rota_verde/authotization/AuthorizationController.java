package br.com.rota_verde.rota_verde.authotization;

import br.com.rota_verde.rota_verde.config.security.TokenDTO;
import br.com.rota_verde.rota_verde.config.security.TokenService;
import br.com.rota_verde.rota_verde.user.dto.CreateUserDTO;
import br.com.rota_verde.rota_verde.user.dto.LoginDTO;
import br.com.rota_verde.rota_verde.user.dto.UserDefaultViewDTO;
import br.com.rota_verde.rota_verde.user.model.UserModel;
import br.com.rota_verde.rota_verde.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((UserModel) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@RequestBody @Valid CreateUserDTO createUserDTO) {
        UserDefaultViewDTO userDefaultViewDTO = null;

        userDefaultViewDTO = userService.saveUser(createUserDTO);

        return ResponseEntity.ok(userDefaultViewDTO);
    }
}
