package com.courses.tellus.autosalon.dao;



import com.courses.tellus.autosalon.config.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@ContextConfiguration(classes = {JdbcTemplatesConfig.class, AutoDaoJdbcTemplates.class})
@ExtendWith(SpringExtension.class)
class AutoDaoIntegrationTest {

    @Autowired
    private AutoDaoJdbcTemplates autoDao;

    @Test
    public void testGetAll() {
        Auto auto = new Auto();
        auto.setBrand("BMW");
        auto.setModel("X6");
        auto.setManufactYear(2017);
        auto.setProducerCountry("Germany");
        auto.setPrice(new BigDecimal(300000));

        List<Auto> autoList = autoDao.getAll();
    }
}
