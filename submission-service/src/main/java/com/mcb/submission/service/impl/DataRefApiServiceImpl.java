package com.mcb.submission.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.submission.dto.DataCheckRequestDto;
import com.mcb.submission.dto.DataCheckRespDto;
import com.mcb.submission.dto.ResponseFormatDto;
import com.mcb.submission.exception.ApiCallException;
import com.mcb.submission.persistence.entity.CustomerApplication;
import com.mcb.submission.service.DataRefApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DataRefApiServiceImpl implements DataRefApiService {

    private final RestTemplate restTemplate;

    @Value("${ms.endpoint.dataref}")
    private String msDataRefUrl;

    public DataRefApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void checkDataRef(CustomerApplication customerApplication) {
        log.info("Checking data ref for customer application: {}", customerApplication.getApplicationId());
        var dataRefReq = new DataCheckRequestDto();
        dataRefReq.setCountryName(customerApplication.getCountryName());
        dataRefReq.setActivityUuid(customerApplication.getActivityUuid());
        dataRefReq.setEntityTypeUuid(customerApplication.getEntityTypeUuid());
        dataRefReq.setPurposeUuid(customerApplication.getApplyingPurposeUuid());

        log.info("Data ref to be checked {}", dataRefReq);
        try {
            var resp = restTemplate.postForObject(msDataRefUrl, dataRefReq, ResponseFormatDto.class);
            var mapper = new ObjectMapper();
            assert resp != null;
            var respDto = mapper.convertValue(resp.getData(), DataCheckRespDto.class);
            log.info("Data ref check response: {}", respDto);
            customerApplication.setCountryName(respDto.getCountry().name());
            customerApplication.setEntityTypeName(respDto.getEntityType().name());
            customerApplication.setApplyingPurpose(respDto.getPurpose().purposeName());
            customerApplication.setActivityName(respDto.getActivity().activityName());
        } catch (Exception e) {
            log.error("Data ref check failed: {}", e.getMessage());
            throw new ApiCallException(msDataRefUrl, "Error while checking dataRef " + e.getMessage());
        }
    }
}
