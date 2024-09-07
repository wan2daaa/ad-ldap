package me.wane.adldap;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public String addUser() {
        userService.saveUser();
        return "User added successfully!";
    }

//    @GetMapping("/{username}")
//    public User getUser(@PathVariable String username) {
//        return userService.findUserByUsername(username);
//    }
}
