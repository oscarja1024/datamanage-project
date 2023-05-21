package com.oscarjimenez.datamanageproject.domain.DTOresponse;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class DeletedCount {

    String deletedCount;

}
