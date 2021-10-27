package com.msyhu.naekaracubae.user.web;

import com.msyhu.naekaracubae.user.domain.user.UserRepository;
import com.msyhu.naekaracubae.user.service.UserService;
import com.msyhu.naekaracubae.user.web.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Long save(@RequestBody UserDto userDto) {
        log.info("Save Subscribers " + userDto);

        return userService.save(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        log.info("Delete by Id " + id);

        userService.delete(id);
    }

    @PutMapping("/{id}")
    public Long updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        log.info("Update Subscribers id " + id + " Subscribers " + userDto);

        return userService.update(id, userDto);
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkEmailDuplicate(email));
    }
}
