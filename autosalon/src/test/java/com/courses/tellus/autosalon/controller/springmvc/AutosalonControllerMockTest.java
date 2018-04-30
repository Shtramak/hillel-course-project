package com.courses.tellus.autosalon.controller.springmvc;

import com.courses.tellus.autosalon.controller.AutosalonController;
import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.service.AutosalonService;
import com.courses.tellus.autosalon.service.AutosalonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutosalonControllerMockTest {

    @Mock
    Autosalon autosalon;

    @Mock
    Model model;

    @Mock
    AutosalonDao autosalonDao;

    AutosalonController autosalonController;
    AutosalonService autosalonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autosalonService = new AutosalonServiceImpl(autosalonDao);
        autosalonController = new AutosalonController((AutosalonServiceImpl) autosalonService);
    }

    @Test
    public void testListAutosalon(){
        List<Autosalon> autosalonList = new ArrayList<>();
        autosalonList.add(autosalon);
        assertEquals("allAutosalon", autosalonController.getAllAutosalon(model));
    }

    @Test
    public void  testCreateAutosalonMethodPost(){
        assertEquals("redirect:createautosalon", autosalonController.createAutosalon(autosalon));
    }

    @Test
    public void  testCreateAutosalonMethodGet(){
        assertEquals("createautosalon", autosalonController.createAutosalonPage());
    }

    @Test
    public void testIndex(){
        assertEquals("index", autosalonController.index());
    }

}
