package com.mcb.submission.utils;

import com.mcb.submission.exception.InvalidTokenException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilTest {

    @Test
    void generateToken() {
        // arrange, act, assert
        assertNotNull(JwtTokenUtil.generateToken("test@gmail.com", null));
    }

    @Test
    void generateTokenThrowsExceptionIfNullIsPassed() {
        // arrange, act and assert
        assertThrows(Exception.class, () -> JwtTokenUtil.generateToken(null, null));
    }

    @Test
    void validateToken() {
        // arrange
        String userName = "test1@gmail.com";

        // act
        String token = JwtTokenUtil.generateToken(userName, null);

        // assert
        assertDoesNotThrow(() -> JwtTokenUtil.validateToken(token, userName));
        assertThrows(InvalidTokenException.class, () -> JwtTokenUtil.validateToken(token, "invalid@gmail.com"));
    }

}