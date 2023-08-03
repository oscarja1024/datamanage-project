package com.oscarjimenez.datamanageproject.api.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@Jacksonized
public class InsertDeckRequest {
        private List<String> cardIds;
        private String heroId;
        private UUID userId;
        private UUID deckId;
}
