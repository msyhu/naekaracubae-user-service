package com.msyhu.naekaracubae.user;

import com.msyhu.naekaracubae.user.domain.user.User;
import com.msyhu.naekaracubae.user.domain.user.UserRepository;
import com.msyhu.naekaracubae.user.web.dto.UserDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

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
    public void 해당_이메일이_존재한다() {

        // given
        String email = "mario@msyhu.com";
        String name = "Mario";
        User user = new User(name, email);
        User savedUser = userRepository.save(user);

        String url = "http://localhost:" + port + "/users/exists/mario@msyhu.com";

        // when
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(url, Boolean.class);

        // then
        then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(responseEntity.getBody()).isNotNull();
        then(responseEntity.getBody()).isEqualTo(true);

    }

    @Test
    public void 데이터가_잘_들어간다() {

        // given
        String email = "mario@msyhu.com";
        String name = "Mario";
        User user = new User(name, email);
        User savedUser = userRepository.save(user);

        String url = "http://localhost:" + port + "/users";

        // when
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, user, User.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getName()).isEqualTo(user.getName());

    }

}
