package com.oscarjimenez.datamanageproject.api.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscarjimenez.datamanageproject.api.DTO.InsertDeckRequest;
import com.oscarjimenez.datamanageproject.domain.entity.*;
import com.oscarjimenez.datamanageproject.service.*;
import com.oscarjimenez.datamanageproject.service.DTO.FilterDTO;
import com.oscarjimenez.datamanageproject.service.DTO.FilteredMetadataResponseDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ResultCardDTO;
import com.oscarjimenez.datamanageproject.service.DTO.ResultGameDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
public class UserDataAccesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CardDataClasifierService cardService;

    @Mock
    private DeckUserDataService deckDataService;

    @Mock
    private GameDataUserService gameDataService;

    @Mock
    private MetadataClasifierService metadataService;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private UserDataAccesController userDataAccesController;


    @Test
    public void testFilterMetadata() throws Exception {
        // Prepare mock behavior
        FilteredMetadataResponseDTO mockResponse = FilteredMetadataResponseDTO.builder().build();
        when(metadataService.filterMetadata(any(FilterDTO.class))).thenReturn(mockResponse);

        mockMvc = MockMvcBuilders.standaloneSetup(userDataAccesController).build();

        // Perform the request and assert the response
        mockMvc.perform(post("/userData/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"key\": \"value\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetGameReport() throws Exception {
        // Prepare mock behavior
        GameEntity mockGameEntity = new GameEntity(); // Create a mock game entity
        when(sessionService.sessionVerify(any(UUID.class), any(UserEntity.class))).thenReturn(true);
        when(gameDataService.getGameReportByGameIdAndUserId(any(UUID.class), any(UserEntity.class))).thenReturn(mockGameEntity);

        mockMvc = MockMvcBuilders.standaloneSetup(userDataAccesController).build();

        // Perform the request and assert the response
        mockMvc.perform(get("/userData/report/{gameId}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("sessionId", UUID.randomUUID())
                        .content(new ObjectMapper().writeValueAsString(UserEntity.builder().build())))
                .andExpect(status().isOk())
                .andExpect(content().json("{}")); // Replace with expected response content
    }

    @Test
    public void testSaveGameReport() throws Exception {
        // Prepare mock behavior
        ResultGameDTO mockResultGameDTO = ResultGameDTO.builder().build();// Create a mock result game DTO
        when(sessionService.sessionVerify(any(UUID.class), any(UserEntity.class))).thenReturn(true);
        when(gameDataService.saveGameReport(any(ResultGameDTO.class), any(UUID.class)))
                .thenReturn(new GameEntity()); // Replace with expected game entity

        mockMvc = MockMvcBuilders.standaloneSetup(userDataAccesController).build();

        // Perform the request and assert the response
        mockMvc.perform(post("/userData/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("sessionId", UUID.randomUUID())
                        .param("userId", UUID.randomUUID().toString())
                        .content("{}")) // Replace with actual content
                .andExpect(status().isOk())
                .andExpect(content().json("{}")); // Replace with expected response content
    }

    @Test
    public void testCompareCards() throws Exception {
        // Prepare mock behavior
        ResultCardDTO mockResultCardDTO = ResultCardDTO.builder().build();// Create a mock result card DTO
        when(cardService.resultCardVsCard(anyString(), anyString())).thenReturn(mockResultCardDTO);

        mockMvc = MockMvcBuilders.standaloneSetup(userDataAccesController).build();

        // Perform the request and assert the response
        mockMvc.perform(get("/userData/compare/{cardId1}/{cardId2}", "cardId1", "cardId2"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}")); // Replace with expected response content
    }


    @Test
    public void testInsertDeckReport() throws Exception {
        // Prepare mock behavior
        when(sessionService.sessionVerify(any(UUID.class), any(UserEntity.class))).thenReturn(true);
        when(deckDataService.generateDeckResport(any(UserEntity.class), any(UUID.class)))
                .thenReturn(new DeckEntity()); // Replace with expected deck entity

        mockMvc = MockMvcBuilders.standaloneSetup(userDataAccesController).build();

        // Perform the request and assert the response
        mockMvc.perform(post("/userData/insertReport/{deckId}/{userId}", UUID.randomUUID(), UUID.randomUUID())
                        .header("sessionId", UUID.randomUUID()))
                .andExpect(status().isCreated())
                .andExpect(content().json("{}")); // Replace with expected response content
    }

    @Test
    public void testDeleteDeckReport() throws Exception {
        // Prepare mock behavior
        when(sessionService.sessionVerify(any(UUID.class), any(UserEntity.class))).thenReturn(true);
        doNothing().when(deckDataService).deleteDeckReport(any(UUID.class));

        mockMvc = MockMvcBuilders.standaloneSetup(userDataAccesController).build();

        // Perform the request and assert the response
        mockMvc.perform(delete("/userData/deleteDeckReport/{deckId}/{deckReportId}/{userId}",
                        UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
                        .header("sessionId", UUID.randomUUID()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }








}
