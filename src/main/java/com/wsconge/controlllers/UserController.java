package com.wsconge.controlllers;

import com.wsconge.DTO.LoginRequestDTO;
import com.wsconge.DTO.LoginResponseDTO;
import com.wsconge.Utility.JwtTokenUtil;
import com.wsconge.entities.Utilisateur;


import com.wsconge.entities.*;
import com.wsconge.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/wsconge")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
   private IUtilisateurService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @GetMapping("/getuser/{id}")
    public Utilisateur getUtilisateursById(@PathVariable Long id){
        return userService.getUtilisateurById(id);
    }

    @GetMapping("/getAllusers")
    public ResponseEntity<List<Utilisateur>> getAllUsers() {
        List<Utilisateur> users = userService.getAllUtilisateurs();
        users.forEach(user -> user.setUtilisateurDeps(null));
        return ResponseEntity.ok(users);
    }
    @PostMapping("/adduser")
    public void addUtilisateur(@RequestBody Utilisateur u){
        userService.addUtilisateur(u);
    }

    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    System.out.println("User deleted successfully!");
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        boolean login = userService.Login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        if (login) {
            Utilisateur utilisateur = userService.getUtilisateurByNom(loginRequestDTO.getUsername());

            if (utilisateur != null && passwordEncoder.matches(loginRequestDTO.getPassword(), utilisateur.getMdp())) {
                String token = jwtTokenUtil.generateToken(utilisateur);
                String roleString = (utilisateur.getRole() != null) ? utilisateur.getRole().toString():"UNKNOWN";
                LoginResponseDTO loginResponse = new LoginResponseDTO(token, roleString,utilisateur.getId());
                return ResponseEntity.ok(loginResponse);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO(null, "Invalid credentials",null));
    }

    @PutMapping ("/updateuser/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Utilisateur updatedUser) {
        userService.updateUser(id, updatedUser);
    }
    @PostMapping("/testsolde")
    public SoldeConge testsolde(@RequestBody SoldeConge s){
            return s;
}

    @GetMapping("/getcurrentuser")
    public String getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        // Extract the token from the Authorization header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            return jwtTokenUtil.extractUsername(token); // Use the JwtTokenUtil to extract username
        }
        throw new RuntimeException("Invalid Authorization header");
    }

}
