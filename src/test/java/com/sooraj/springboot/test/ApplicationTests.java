package com.sooraj.springboot.test;

import com.sooraj.springboot.ApplicationMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by SOORAJ on 11-12-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationMain.class)
@WebAppConfiguration
public class ApplicationTests {

    protected final static Logger LOGGER= LoggerFactory.getLogger(ApplicationTests.class);

	@Test
	public void contextLoads() {
        LOGGER.info("Test initialised ");
	}

}
