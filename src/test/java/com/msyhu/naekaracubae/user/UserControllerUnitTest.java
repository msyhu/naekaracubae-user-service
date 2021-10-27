package com.msyhu.naekaracubae.user;

import com.google.gson.Gson;
import com.msyhu.naekaracubae.user.domain.user.User;
import com.msyhu.naekaracubae.user.service.UserService;
import com.msyhu.naekaracubae.user.web.UserController;
import com.msyhu.naekaracubae.user.web.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerUnitTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @DisplayName("데이터 저장 성공")
    @Test
    public void saveSuccess() throws Exception {

        // given
        final UserDto userDto = userDto();
//        doReturn(false).when(userService).checkEmailDuplicate(userDto.getEmail());
        doReturn(1L).when(userService).save(any(UserDto.class));

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userDto)));

        // then
        final MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
        final String token = mvcResult.getResponse().getContentAsString();
        assertThat(token).isNotNull();

    }

    private UserDto userDto() {

        final UserDto userDto = new UserDto("test@test.test", "test");
        return userDto;
    }

    @DisplayName("사용자 목록 조회")
    @Test
    void getUserList() throws Exception {
        // given
        doReturn(userList()).when(userService).findAll();

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/users"));

        // then
        final MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
        String temp = mvcResult.getResponse().getContentAsString();
        final List<User> response = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), List.class);
        assertThat(response.size()).isEqualTo(5);
    }

    private List<User> userList() {
        final List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userList.add(new User("test", "test@test.test"));
        }
        return userList;
    }

}
