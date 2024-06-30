package com.mcb.datareference_service.service.impl;

import com.mcb.datareference_service.dto.Country;
import com.mcb.datareference_service.dto.DataCheckRequestDto;
import com.mcb.datareference_service.dto.DataCheckRespDto;
import com.mcb.datareference_service.persistence.entity.ApplyingPurpose;
import com.mcb.datareference_service.persistence.entity.BusinessActivity;
import com.mcb.datareference_service.persistence.entity.EntityType;
import com.mcb.datareference_service.service.ApplyingPurposeService;
import com.mcb.datareference_service.service.BusinessActivityService;
import com.mcb.datareference_service.service.CountryService;
import com.mcb.datareference_service.service.EntityTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataRefServiceImplTest {

    @InjectMocks
    private DataRefServiceImpl refService;

    @Mock
    private CountryService countryService;
    @Mock
    private EntityTypeService typeService;
    @Mock
    private BusinessActivityService activityService;

    @Mock
    private ApplyingPurposeService purposeService;

    @Test
    void checkDataRefUsingValidParam() {
        // arrange
        DataCheckRequestDto reqDto = new DataCheckRequestDto();
        reqDto.setCountryName("England");
        reqDto.setActivityUuid(UUID.randomUUID().toString());
        reqDto.setPurposeUuid(UUID.randomUUID().toString());
        reqDto.setEntityTypeUuid(UUID.randomUUID().toString());

        var country = new Country("England", "eng");

        var entityType = new EntityType();
        entityType.setId(1);
        entityType.setName("Association");

        var purpose = new ApplyingPurpose();
        var activity = new BusinessActivity();

        when(typeService.getEntityTypeByUuidOrElseThrow(anyString()))
                .thenReturn(entityType);

        when(activityService.checkAndGetUsingUuid(anyString()))
                .thenReturn(activity);
        when(purposeService.getApplyingPurposeByUuidOrElseThrow(anyString()))
                .thenReturn(purpose);

        when(countryService.findCountryByNameOrElseThrow(anyString()))
                .thenReturn(country);

        // act
        DataCheckRespDto respDto = refService.checkDataRef(reqDto);

        // assert
        assertAll(
                () -> assertNotNull(respDto),
                () -> assertEquals("England", respDto.getCountry().name()),
                () -> assertNotNull(respDto.getActivity()),
                () -> assertNotNull(respDto.getEntityType()),
                () -> assertNotNull(respDto.getPurpose())
        );
    }
}