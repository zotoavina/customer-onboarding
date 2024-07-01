package com.mcb.datareference_service.service.impl;

import com.mcb.datareference_service.dto.Country;
import com.mcb.datareference_service.dto.DataCheckRequestDto;
import com.mcb.datareference_service.dto.DataCheckRespDto;
import com.mcb.datareference_service.persistence.entity.ApplyingPurpose;
import com.mcb.datareference_service.persistence.entity.BusinessActivity;
import com.mcb.datareference_service.persistence.entity.EntityType;
import com.mcb.datareference_service.service.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class DataRefServiceImpl implements DataRefService {

    private final CountryService countryService;

    private final BusinessActivityService activityService;

    private final EntityTypeService typeService;
    private final ApplyingPurposeService purposeService;

    public DataRefServiceImpl(CountryService countryService,
                              BusinessActivityService businessActivityService,
                              EntityTypeService entityTypeService,
                              ApplyingPurposeService applyingPurposeService) {
        this.countryService = countryService;
        activityService = businessActivityService;
        typeService = entityTypeService;
        purposeService = applyingPurposeService;
    }

    @Override
    public @NonNull DataCheckRespDto checkDataRef(@NonNull DataCheckRequestDto requestDto) {
        Objects.requireNonNull(requestDto);
        EntityType entityType = typeService.getEntityTypeByUuidOrElseThrow(requestDto.getEntityTypeUuid());
        BusinessActivity activity = activityService.checkAndGetUsingUuid(requestDto.getActivityUuid());
        ApplyingPurpose purpose = purposeService.getApplyingPurposeByUuidOrElseThrow(requestDto.getPurposeUuid());
        Country country = countryService.findCountryByNameOrElseThrow(requestDto.getCountryName());
        return new DataCheckRespDto(country, activity, purpose, entityType);
    }
}
