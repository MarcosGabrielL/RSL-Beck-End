/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.security;

/**
 *
 * @author Marcos
 */
import com.softsaj.redelivre.models.Person;
import com.softsaj.redelivre.security.UserRepository;
import com.softsaj.redelivre.security.AuthRequest;
import com.softsaj.redelivre.security.User;
import com.softsaj.redelivre.security.JwtUtil;
import com.softsaj.redelivre.services.PersonService;
import com.softsaj.redelivre.repositories.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppController {
    
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PersonRepository personRepo;
    @Autowired
    private PersonService vs;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

	
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

	
     @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
              
        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //String encodedPassword = passwordEncoder.encode(authRequest.getPassword());
    //authRequest.setPassword(encodedPassword);
      //  System.out.println("Senha: "+authRequest.getPassword());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signupform";
    }

@PostMapping("/process_register")
public ResponseEntity<User> processRegister(@RequestBody User user) {
   // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //String encodedPassword = passwordEncoder.encode(user.getPassword());
    //user.setPassword(encodedPassword);
     
    User newUser = userRepo.save(user);
    
    //Cria Cinefilo
    Person person = new Person();
    person.setEmail(user.getEmail());
    person.setId(user.getId().intValue());
    person.setUser(user.getFirstName());
    person.setNome(user.getFirstName()+" "+user.getLastName());
    personRepo.addPerson(person);
    
     
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
}

    @GetMapping("/users")
    public String listUsers(Model model) {
    List<User> listUsers = userRepo.findAll();
    model.addAttribute("listUsers", listUsers);
     
    
    return "users";
}

 @GetMapping("/user/{email}")
    public ResponseEntity<User> getUserByEmail (@PathVariable("email") String email) {
        User user = userRepo.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}