package com.msyhu.naekaracubae.user.web.dto;

import com.msyhu.naekaracubae.user.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class UserDto {
    @NotBlank
    @Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$")
    private String email;

    @NotBlank
    private String name;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }

    public User toEntity() {
        return new User(name, email);
    }

}
