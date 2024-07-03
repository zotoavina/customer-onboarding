package com.mcb.datareference_service.controller;

import com.mcb.datareference_service.persistence.entity.EntityType;
import com.mcb.datareference_service.service.EntityTypeService;
import org.junit.jupiter.api.Test;
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

@WebMvcTest(EntityTypeController.class)
class EntityTypeControllerTest {

    @MockBean
    private EntityTypeService typeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllEntityTypes() throws Exception {
        // arrange
        var types = List.of(new EntityType());
        when(typeService.findAllEntityTypes()).thenReturn(types);

        // act and assert
        mockMvc.perform(get("/api/data/reference/entity-types"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("data")));
    }
}