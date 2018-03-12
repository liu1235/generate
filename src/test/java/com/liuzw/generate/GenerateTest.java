package com.liuzw.generate;


import com.liuzw.generate.service.IGenerateService;
import org.apache.log4j.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@WebAppConfiguration
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applications/*.xml"})
public class GenerateTest extends AbstractTransactionalJUnit4SpringContextTests {

	private Logger logger = Logger.getLogger(GenerateTest.class);

	@Autowired
	private IGenerateService generateService;
	
	@Test
	public void generateEntity(){
		String tableNames = "t_hr_time_outside_bill";
		generateService.generateEntity(tableNames);
	}
	
	@Test
	public void generateService(){
		String tableNames="hr_si_org";
		generateService.generateService(tableNames);

	}
	
	@Test
	public void generateServiceImpl(){
		String tableNames="hr_si_org";
		generateService.generateServiceImpl(tableNames);
	}
	
	@Test
	public void generateDao(){
		String tableNames="hr_si_org";
		generateService.generateDao(tableNames);
	}
	
	@Test
	public void generateMapper(){
		String tableNames="t_hr_time_outside_bill";
		generateService.generateMapper(tableNames);
	}

	@Test
	public void generateAll(){
		String tableNames="t_hr_time_outside_bill";
		generateService.generateAll(tableNames);
	}


}
