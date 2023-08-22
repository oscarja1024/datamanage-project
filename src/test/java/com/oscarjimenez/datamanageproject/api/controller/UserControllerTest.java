package com.oscarjimenez.datamanageproject.api.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscarjimenez.datamanageproject.api.DTO.response.sessionResponse;
import com.oscarjimenez.datamanageproject.api.DTO.sesssionStartRequest;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.service.DTO.UserDataDTO;
import com.oscarjimenez.datamanageproject.service.SessionService;
import com.oscarjimenez.datamanageproject.service.UserDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private UserDataService userDataService;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Test
    public void testRegisterUser() throws Exception {
        UserDataDTO userData = UserDataDTO.builder().email("user@example.com").name("John Doe").passwd("hashedPassword").build();
        UUID userId = UUID.randomUUID();
        UserEntity registeredUser =  UserEntity.builder().userId(userId).email( "user@example.com").name("John Doe").passwd("hashedPassword").build();

        when(userDataService.userRegistration(userData)).thenReturn(registeredUser);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.email").value("user@example.com"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testGetUser() throws Exception {
        UUID userId = UUID.randomUUID();
        UserEntity user =  UserEntity.builder().userId(userId).email( "user@example.com").name("John Doe").passwd("hashedPassword").build();

        when(userDataService.getUserData(any())).thenReturn(user);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(get("/api/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.email").value("user@example.com"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        UUID userId = UUID.randomUUID();

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(delete("/api/users/delete/{userId}", userId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testStartSession() throws Exception {
        sesssionStartRequest sessionRequest =  sesssionStartRequest.builder().email("user@example.com").password("password").build();
        sessionResponse sessionResponse = com.oscarjimenez.datamanageproject.api.DTO.response.sessionResponse.builder().sessionId(UUID.randomUUID()).userId(UUID.randomUUID()).build();

        when(sessionService.getSession(eq("user@example.com"), anyString())).thenReturn(sessionResponse);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(get("/api/users/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sessionRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sessionId").exists())
                .andExpect(jsonPath("$.userId").exists());
    }

    @Test
    public void testDeleteSession() throws Exception {
        UUID sessionId = UUID.randomUUID();

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(delete("/api/users/session/{sessionId}", sessionId))
                .andExpect(status().isNoContent());
    }




}
