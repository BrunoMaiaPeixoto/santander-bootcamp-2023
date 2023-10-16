package me.dio.santanderbootcamp2023;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
        return ResponseEntity.ok("Usuário criado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Long id) {
        UserModel user = userService.getUserById(id);

        if (user != null) {
            return ResponseEntity.ok(user);

        } else {
            System.out.println("404 (Not Found)");

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserModel updatedUser) {

        UserModel existingUser = userService.getUserById(id);

        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setName(updatedUser.getName());
            existingUser.setSenha(updatedUser.getSenha());

            // Salve as alterações no banco de dados.
            userService.updateUser(existingUser);

            return ResponseEntity.ok("Usuário atualizado com sucesso!");
        } else {


            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        UserModel existingUser = userService.getUserById(id);

        if (existingUser != null) {
            userService.deleteUser(id);

            return ResponseEntity.ok("Usuário excluído com sucesso!");
        } else {


            return ResponseEntity.notFound().build();
        }
    }
}
