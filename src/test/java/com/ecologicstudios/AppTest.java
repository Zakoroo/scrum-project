package com.ecologicstudios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AppTest {

  @Test
  void testAddition() {
    App app = new App();
    assertEquals(5, app.add(2, 3));
  }
}
