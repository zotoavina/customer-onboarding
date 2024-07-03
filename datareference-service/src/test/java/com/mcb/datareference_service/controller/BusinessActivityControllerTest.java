package com.mcb.datareference_service.controller;

import com.mcb.datareference_service.persistence.entity.BusinessActivity;
import com.mcb.datareference_service.service.BusinessActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BusinessActivityController.class)
class BusinessActivityControllerTest {

    @MockBean
    private BusinessActivityService activityService;

    @Autowired
    private MockMvc mockMvc;


    @org.junit.jupiter.api.Test
    void findAllActivities() throws Exception {

        // arrange
        var activities = List.of(new BusinessActivity());
        when(activityService.getAll()).thenReturn(activities);

        // act and assert
        mockMvc.perform(get("/api/data/reference/activities"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("data")));
    }
}