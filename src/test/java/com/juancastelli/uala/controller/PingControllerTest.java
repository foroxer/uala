package com.juancastelli.uala.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PingControllerTest {

    @Test
    public void test_ping() {
        // Arrange
        PingController pingController = new PingController();

        // Act
        String result = pingController.ping();

        // Assert
        assertEquals("pong", result);
    }

}