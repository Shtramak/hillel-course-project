package com.courses.tellus.autosalon.dao;

import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutoTest {

    Auto auto = new Auto();

    @Test
    public void getIdTestWhenresTrue(){
        auto.setId(1L);
        Assertions.assertTrue(auto.getId().equals(1L));
    }

    @Test
    public void getIdTestWhenresFalse(){
        auto.setId(1L);
        Assertions.assertFalse(auto.getId().equals(2L));
    }
}
