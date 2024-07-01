package com.mcb.datareference_service.service;


import com.mcb.datareference_service.dto.DataCheckRequestDto;
import com.mcb.datareference_service.dto.DataCheckRespDto;
import lombok.NonNull;

public interface DataRefService {

    @NonNull
    DataCheckRespDto checkDataRef(@NonNull DataCheckRequestDto requestDto);
}
