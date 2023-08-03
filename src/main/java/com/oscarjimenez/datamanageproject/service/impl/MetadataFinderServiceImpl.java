package com.oscarjimenez.datamanageproject.service.impl;

import com.oscarjimenez.datamanageproject.client.FeignDataMinerConnection;
import com.oscarjimenez.datamanageproject.service.MetadataFinderService;
import com.oscarjimenez.datamanageproject.client.DTOS.MetadataResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataFinderServiceImpl implements MetadataFinderService {

    @Autowired
    FeignDataMinerConnection feignDataMinerConnection;

    @Override
    public MetadataResponseDTO getAllMetada() {
        return feignDataMinerConnection.getMetadata();
    }
}
