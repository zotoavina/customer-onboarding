package com.mcb.datareference_service.dto;

import com.mcb.datareference_service.persistence.entity.ApplyingPurpose;
import com.mcb.datareference_service.persistence.entity.BusinessActivity;
import com.mcb.datareference_service.persistence.entity.EntityType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DataCheckRespDto {
    private Country country;
    private BusinessActivity activity;
    private ApplyingPurpose purpose;
    private EntityType entityType;
}
