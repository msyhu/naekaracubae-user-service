package com.msyhu.naekaracubae.user.web;

import com.msyhu.naekaracubae.user.domain.user.User;
import com.msyhu.naekaracubae.user.domain.user.UserRepository;
import com.msyhu.naekaracubae.user.service.UserService;
import com.msyhu.naekaracubae.user.web.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@RestController
@ApiOperation("상태별 Subscribers 조회")
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping
    public List<UserDto> findAll() {
        log.info("Find All");

        return userService.findAll();
    }

    @GetMapping("/count")
    public long count() {
        log.info("count");
        return userRepository.count();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        log.info("Find by Id " + id);

        return userService.findById(id);
    }

    @PostMapping
    public User saveUser(@RequestBody User User) {
        log.info("Save Subscribers " + User);

        User savedUser = userRepository.save(User);
        return savedUser;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        log.info("Delete by Id " + id);

        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User User) {
        log.info("Update Subscribers id " + id + " Subscribers " + User);

        Optional<User> userInfo = userRepository.findById(id);
        User findUser = userInfo.orElseThrow(() -> new NoSuchElementException("There is not any resource by id: " + id));

        if (findUser.getEmail() != null) {
            findUser.setEmail(User.getEmail());
        }

        if (findUser.getName() != null) {
            findUser.setName(User.getName());
        }

        return userRepository.save(findUser);
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkEmailDuplicate(email));
    }
}
