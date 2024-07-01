package com.mcb.datareference_service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DataCheckRequestDto {
    @NotEmpty
    private String countryName;
    @NotEmpty
    private String entityTypeUuid;
    @NotEmpty
    private String activityUuid;
    @NotEmpty
    private String purposeUuid;
}
