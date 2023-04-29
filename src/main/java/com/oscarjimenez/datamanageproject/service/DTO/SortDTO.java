package com.oscarjimenez.datamanageproject.service.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SortDTO {

    //Valid values include manaCost:asc, manaCost:desc, attack:asc, attack:desc, health:asc, health:desc, class:asc,
    // class:desc, groupByClass:asc, groupByClass:desc, name:asc, and name:desc.
    String sort;
}
