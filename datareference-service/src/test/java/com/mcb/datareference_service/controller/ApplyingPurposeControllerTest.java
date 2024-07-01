package com.mcb.datareference_service.controller;

import com.mcb.datareference_service.persistence.entity.ApplyingPurpose;
import com.mcb.datareference_service.service.ApplyingPurposeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplyingPurposeController.class)
class ApplyingPurposeControllerTest {

    @MockBean
    private ApplyingPurposeService purposeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllApplyingPurpose() throws Exception {
        // arrange
        String url = "/api/v1/data/reference/applying-purposes";
        var purposeList = List.of(new ApplyingPurpose(), new ApplyingPurpose());

        when(purposeService.getAllApplyingPurpose()).thenReturn(purposeList);

        // act and assert
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("data")));

    }

}