package com.manager.clients.controller;

import com.manager.clients.models.Users;
import com.manager.clients.repository.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/users"})
public class UsersController {

    // REPOSITORIO
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;

    UsersController(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.repository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // MÉTODOS

    // Busca geral
    @GetMapping(path = "/")
    public List<Users> findAll(){
        return repository.findAll();
    }

    // Busca especifica
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Users> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Users users) {
        System.out.println("Recebido: " + users);
        if (users.getPassword() != null && !users.getPassword().isBlank()) {
            users.setPassword(passwordEncoder.encode(users.getPassword()));
        }
        Users savedUser = repository.save(users);
        System.out.println("Salvo no banco: " + savedUser);
        return ResponseEntity.ok(savedUser);
    }

    // Atualizar
    @PutMapping(path = "/{id}")
    public ResponseEntity<Users> update(@PathVariable("id") long id, @RequestBody Users users){
        return repository.findById(id)
                .map(record -> {
                    record.setName(users.getName());
                    record.setEmail(users.getEmail());
                    if (users.getPassword() != null && !users.getPassword().isBlank()) {
                        // encode new password
                        record.setPassword(passwordEncoder.encode(users.getPassword()));
                    }
                    record.setPhone(users.getPhone());
                    record.setCpf(users.getCpf());
                    record.setCourse(users.getCourse());
                    Users updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Deletar
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
