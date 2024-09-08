package me.wane.adldap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.wane.adldap.dto.CreateUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public String addUser(
            @RequestBody CreateUserRequest request
            ) {
        userService.saveUser(request);
        return "User added successfully!";
    }

    @GetMapping("/{cn}/password/{password}")
    public Boolean isPasswordMatch(
            @PathVariable String cn,
            @PathVariable String password
    ) {
        return userService.isPasswordMatch(cn, password);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{cn}")
    public User getUser(@PathVariable String cn) {
        return userService.findUserByCN(cn);
    }
}
