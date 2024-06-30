package com.mcb.submission.dto;

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
