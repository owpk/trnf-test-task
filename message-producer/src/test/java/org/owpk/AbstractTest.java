package org.owpk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@Sql(scripts = "classpath:test.sql", config = @SqlConfig(encoding = "UTF-8"))
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractTest {

    protected static final String baseCtxPath = "/rest/api/";

    @Autowired
    MockMvc mockMvc;

}
