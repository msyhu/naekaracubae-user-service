package com.msyhu.naekaracubae.user;

import com.msyhu.naekaracubae.user.models.User;
import com.msyhu.naekaracubae.user.repositories.UserRepository;
import com.msyhu.naekaracubae.user.resources.UserController;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void 해당_이메일이_존재한다() throws Exception {

        // given
        String email = "mario@msyhu.com";
        String name = "Mario";
        Long id = Long.valueOf(1);
        User user = new User(id, name, email);
        User savedUser = userRepository.save(user);

        String url = "http://localhost:" + port + "/users/exists/mario@msyhu.com";

        // when
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(url, Boolean.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(true);

    }
}
