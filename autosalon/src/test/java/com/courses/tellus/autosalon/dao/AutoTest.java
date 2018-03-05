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

    @Test
    public void getBrandTestTrue(){
        auto.setBrand("BMW");
        Assertions.assertTrue(auto.getBrand().equals("BMW"));
    }

    @Test
    public void getBrandTestFalse(){
        auto.setBrand("BMW");
        Assertions.assertFalse(auto.getBrand().equals("Toyota"));
    }

    @Test
    public void getModelTestTrue(){
        auto.setModel("X5");
        Assertions.assertTrue(auto.getModel().equals("X5"));
    }

    @Test
    public void getModelTestFalse(){
        auto.setModel("X5");
        Assertions.assertFalse(auto.getModel().equals("X6"));
    }

}
