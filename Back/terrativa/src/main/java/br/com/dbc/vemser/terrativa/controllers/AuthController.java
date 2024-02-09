package br.com.dbc.vemser.terrativa.controllers;

import br.com.dbc.vemser.terrativa.dto.requests.LoginDTO;
import br.com.dbc.vemser.terrativa.entity.Usuario;
import br.com.dbc.vemser.terrativa.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.terrativa.services.UsuarioService;
import br.com.dbc.vemser.terrativa.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@Tag(name = "Login", description = "Endpoints de login usuario")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> auth(@RequestBody @Valid LoginDTO loginDTO) {
        Optional<Usuario> usuarioOptional = usuarioService.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            String token = tokenService.generateToken(usuario);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

    @GetMapping("usuario/logado")
    public ResponseEntity<Optional<Usuario>> usuarioLogado() throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.getLoggedUser(), HttpStatus.OK);
    }
}
