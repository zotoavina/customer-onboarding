package com.mcb.datareference_service.service;

import com.mcb.datareference_service.persistence.entity.ApplyingPurpose;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface ApplyingPurposeService {

    @NonNull
    List<ApplyingPurpose> getAllApplyingPurpose();

    @NonNull
    Optional<ApplyingPurpose> getApplyingPurposeByUuid(String uuid);

    @NonNull
    ApplyingPurpose getApplyingPurposeByUuidOrElseThrow(String uuid);
}
