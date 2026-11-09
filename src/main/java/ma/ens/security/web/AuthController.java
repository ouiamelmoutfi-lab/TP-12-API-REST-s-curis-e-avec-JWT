package ma.ens.security.web;

import ma.ens.security.jwt.JwtUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

//@RestController : permet de créer un contrôleur REST qui retourne des données, généralement au format JSON.
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    //Il permet de charger les informations d’un utilisateur à partir de son nom d’utilisateur.
    private final UserDetailsService userDetailsService;
    //Elle permet de générer, valider et extraire les informations du token JWT.
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }
//Permet de définir une requête HTTP POST pour l’authentification de l’utilisateur.
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserDetails user = userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(user.getUsername());

        return Map.of(
                "token", token,
                "username", username,
                "type", "Bearer"
        );
    }
}
