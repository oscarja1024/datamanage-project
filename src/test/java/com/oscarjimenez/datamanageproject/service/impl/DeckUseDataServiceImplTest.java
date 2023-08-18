package com.oscarjimenez.datamanageproject.service.impl;
import com.oscarjimenez.datamanageproject.api.DTO.InsertDeckRequest;
import com.oscarjimenez.datamanageproject.api.utils.constants;
import com.oscarjimenez.datamanageproject.client.DTOS.DeckDTO;
import com.oscarjimenez.datamanageproject.client.DTOS.GetOneCardResponseDTO;
import com.oscarjimenez.datamanageproject.domain.entity.DeckEntity;
import com.oscarjimenez.datamanageproject.domain.entity.FavDeckEntity;
import com.oscarjimenez.datamanageproject.domain.entity.UserEntity;
import com.oscarjimenez.datamanageproject.domain.repository.DeckRepository;
import com.oscarjimenez.datamanageproject.domain.repository.FavDeckRepository;
import com.oscarjimenez.datamanageproject.domain.repository.UserRepository;
import com.oscarjimenez.datamanageproject.service.DTO.DeckReportDTO;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeckUseDataServiceImplTest {

    @InjectMocks
    private DeckUseDataServiceImpl deckUseDataService;

    @Mock
    private DeckFinderDataServiceImpl deckFinderDataService;

    @Mock
    private FavDeckRepository favDeckRepository;

    @Mock
    private DeckRepository deckRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUserIdandDeckId() {
        UUID deckId = UUID.randomUUID();
        UserEntity userEntity = UserEntity.builder().userId(UUID.randomUUID()).build();
        FavDeckEntity mockResponse = FavDeckEntity.builder().deckId(deckId).user(userEntity).build();

        when(favDeckRepository.findByDeckIdAndUser(deckId, userEntity)).thenReturn(mockResponse);

        FavDeckEntity result = deckUseDataService.findByUserIdandDeckId(deckId, userEntity);

        assertEquals(deckId, result.getDeckId());
        assertEquals(userEntity, result.getUser());
    }

    @Test
    public void testSaveOwnedDeck() {
        UUID userId = UUID.randomUUID();
        InsertDeckRequest deckDTO = InsertDeckRequest.builder().userId(userId).cardIds("cardIds").heroId("heroId").build();
        UserEntity userEntity = UserEntity.builder().userId(userId).build();

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(userEntity));

        FavDeckEntity mockResponse = FavDeckEntity.builder()
                .cardIds("cardIds")
                .heroId("heroId")
                .user(userEntity)
                .build();

        when(favDeckRepository.saveAndFlush(mockResponse)).thenReturn(mockResponse);

        FavDeckEntity result = deckUseDataService.saveOwnedDeck(deckDTO, userId);

        assertEquals("cardIds", result.getCardIds());
        assertEquals("heroId", result.getHeroId());
        assertEquals(userEntity, result.getUser());
    }


    @Test
    public void testGetDeckReport() {
        UUID deckReportId = UUID.randomUUID();
        DeckEntity mockResponse = DeckEntity.builder()
                .deckId(deckReportId)
                .cardCount("10")
                .attackMean("5")
                .manaMean("3")
                .healthMean("8")
                .spellsMean("2")
                .minionCount("7")
                .secretsCount("1")
                .attackIncrease("2")
                .healMean("4")
                .build();

        when(deckRepository.findById(deckReportId)).thenReturn(java.util.Optional.of(mockResponse));

        DeckEntity result = deckUseDataService.getDeckReport(deckReportId);

        assertEquals(deckReportId, result.getDeckId());
        assertEquals("10", result.getCardCount());
        assertEquals("5", result.getAttackMean());
        assertEquals("3", result.getManaMean());
        assertEquals("8", result.getHealthMean());
        assertEquals("2", result.getSpellsMean());
        assertEquals("7", result.getMinionCount());
        assertEquals("1", result.getSecretsCount());
        assertEquals("2", result.getAttackIncrease());
        assertEquals("4", result.getHealMean());
    }

    @Test
    public void testGenerateDeckResport() {
        UUID userId = UUID.randomUUID();
        UUID deckId = UUID.randomUUID();
        UserEntity userEntity = UserEntity.builder().userId(userId).build();
        FavDeckEntity mockFavDeckResponse = FavDeckEntity.builder()
                .cardIds("cardIds")
                .heroId("heroId")
                .user(userEntity)
                .deckId(deckId)
                .build();
        List<GetOneCardResponseDTO> mockCardList = Collections.singletonList(GetOneCardResponseDTO.builder()
                .attack("5")
                .manaCost("3")
                .health("8")
                .text("This card has +2 Attack")
                .cardTypeId("5")
                .build());

        var deckReport = DeckEntity.builder()
                .deckId(deckId)
                .cardCount("1")
                .attackMean("5.0")
                .manaMean("3.0")
                .healthMean("8.0")
                .spellsMean("1.0")
                .minionCount("1.0")
                .secretsCount("1.0")
                .attackIncrease("1.0")
                .healMean("0.0")
                .user(UserEntity.builder().userId(userId).build())
                .build();

        when(deckUseDataService.findByUserIdandDeckId(deckId, userEntity)).thenReturn(mockFavDeckResponse);
        when(deckFinderDataService.getDeckByCardListAndHero("cardIds", "heroId")).thenReturn(DeckDTO.builder()
                .cardCount(1)
                .cards(mockCardList)
                .build());
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(userEntity));

        when(deckRepository.saveAndFlush(deckReport)).thenReturn(deckReport);

        DeckEntity result = deckUseDataService.generateDeckResport(userEntity, deckId);



        assertEquals(deckId, result.getDeckId());
        assertEquals("1", result.getCardCount());
        assertEquals("5.0", result.getAttackMean());
        assertEquals("3.0", result.getManaMean());
        assertEquals("8.0", result.getHealthMean());
        assertEquals("1.0", result.getSpellsMean()); // You need to adjust this based on your calculations
        assertEquals("1.0", result.getMinionCount()); // You need to adjust this based on your calculations
        assertEquals("1.0", result.getSecretsCount()); // You need to adjust this based on your calculations
        assertEquals("1.0", result.getAttackIncrease()); // You need to adjust this based on your calculations
        assertEquals("0.0", result.getHealMean()); // You need to adjust this based on your calculations
        assertEquals(userEntity, result.getUser());
    }

    @Test
    public void testDeleteDeckReport() {
        UUID deckReportId = UUID.randomUUID();

        deckUseDataService.deleteDeckReport(deckReportId);

        verify(deckRepository, times(1)).deleteById(deckReportId);
    }

    @Test
    public void testDeleteOwnedDeck() {
        UUID ownedDeckId = UUID.randomUUID();

        deckUseDataService.deleteOwnedDeck(ownedDeckId);

        verify(favDeckRepository, times(1)).deleteById(ownedDeckId);
    }

    @Test
    public void testFindDecksByUserId() {
        UUID userId = UUID.randomUUID();
        UserEntity userEntity = UserEntity.builder().userId(userId).build();
        List<FavDeckEntity> mockFavDecks = Collections.singletonList(FavDeckEntity.builder().build());

        when(favDeckRepository.findByUser(userEntity)).thenReturn(mockFavDecks);

        List<FavDeckEntity> result = deckUseDataService.findDecksByUserId(userEntity);

        assertEquals(mockFavDecks, result);
    }

    @Test
    public void testFindDeckReportsByUserId() {
        UUID userId = UUID.randomUUID();
        UserEntity userEntity = UserEntity.builder().userId(userId).build();
        List<DeckEntity> mockDeckReports = Collections.singletonList(DeckEntity.builder().build());

        when(deckRepository.findByUser(userEntity)).thenReturn(mockDeckReports);

        List<DeckEntity> result = deckUseDataService.findDeckReportsByUserId(userEntity);

        assertEquals(mockDeckReports, result);
    }

}
