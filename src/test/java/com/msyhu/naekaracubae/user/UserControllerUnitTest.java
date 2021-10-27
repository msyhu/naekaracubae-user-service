package com.msyhu.naekaracubae.user;

import com.msyhu.naekaracubae.user.domain.user.User;
import com.msyhu.naekaracubae.user.service.UserService;
import com.msyhu.naekaracubae.user.web.UserController;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("findById Test")
    void findByIdTest() throws Exception {
        User user = User.builder().email("msyhu@korea.ac.kr").name("msyhu").build();
    }
}
