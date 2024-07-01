package com.mcb.submission.service.impl;

import com.mcb.submission.persistence.repository.SubmissionRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SubmissionServiceImplTest {

    @InjectMocks
    private SubmissionServiceImpl submissionService;

    @Mock
    private SubmissionRepository submissionRepository;

//    @Test
//    void findSubmissionUsingApplicationId() {
//
//        // arrange
//        Application application = new Application();
//        when(submissionRepository.findCustomerApplicationByApplicationId(application.getApplicationId()))
//                .thenReturn(Optional.of(application));
//
//        // act
//        var foundApplication = submissionService.findSubmissionByApplicationIdOrElseThrow(application.getApplicationId());
//
//        // assert
//        assertNotNull(foundApplication);
//    }
}