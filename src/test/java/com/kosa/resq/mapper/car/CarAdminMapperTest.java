package com.kosa.resq.mapper.car;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(locations = "classpath:application.yaml")
public class CarAdminMapperTest {
    @Autowired
    private CarAdminMapper mapper;


}