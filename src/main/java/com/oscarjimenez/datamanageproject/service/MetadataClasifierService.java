package com.oscarjimenez.datamanageproject.service;

import com.oscarjimenez.datamanageproject.service.DTO.FilterDTO;
import com.oscarjimenez.datamanageproject.service.DTO.FilteredMetadataResponseDTO;

public interface MetadataClasifierService {


    FilteredMetadataResponseDTO filterMetadata(FilterDTO filters);

}
