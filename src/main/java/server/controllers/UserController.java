package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.entities.User;
import server.exceptions.DbException;
import server.payload.UserSummary;
import server.repositories.UserRepository;
import server.security.CurrentUser;
import server.security.UserPrincipal;
import server.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(
                currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(), currentUser.getLastName());
        return userSummary;
    }

    @GetMapping("/users")
    public ResponseEntity getAll() throws DbException {
        List list = userService.getAll();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws DbException {
        User user = userService.getById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/name/{name}")
    public ResponseEntity getByName(@PathVariable String name) throws DbException {
        User user = userService.getByName(name);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user) throws DbException {
        user = userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody User user) throws DbException {
        user = userService.update(id, user);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if (userService.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
