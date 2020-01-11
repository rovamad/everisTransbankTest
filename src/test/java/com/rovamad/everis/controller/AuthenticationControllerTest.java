package com.rovamad.everis.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginTest() {

        assertThat(authenticationController.login()).isNotNull();
    }
}
