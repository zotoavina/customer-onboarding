package com.mcb.datareference_service.service.impl;

import com.mcb.datareference_service.persistence.entity.ApplyingPurpose;
import com.mcb.datareference_service.persistence.repository.ApplyingPurposeRepository;
import com.mcb.datareference_service.service.ApplyingPurposeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ApplyingPurposeServiceImpl implements ApplyingPurposeService {

    private final ApplyingPurposeRepository purposeRepository;

    public ApplyingPurposeServiceImpl(ApplyingPurposeRepository applyingPurposeRepository) {
        this.purposeRepository = applyingPurposeRepository;
    }


    @Override
    public @NonNull List<ApplyingPurpose> getAllApplyingPurpose() {
        return purposeRepository.findAll();
    }

    @Override
    public @NonNull Optional<ApplyingPurpose> getApplyingPurposeByUuid(String uuid) {
        return purposeRepository.findByPurposeUUID(uuid);
    }

    @Override
    public @NonNull ApplyingPurpose getApplyingPurposeByUuidOrElseThrow(String uuid) {
        return getApplyingPurposeByUuid(uuid)
                .orElseThrow(
                        () -> new EntityNotFoundException("No applying purpose with uuid identifier " + uuid)
                );
    }

}
