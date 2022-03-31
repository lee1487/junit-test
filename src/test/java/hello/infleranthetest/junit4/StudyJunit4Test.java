package hello.infleranthetest.junit4;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudyJunit4Test {

	//pom.xml에 org.junit.vintage dependency추가

	@Before
	public void before() {
		log.info("before");
	}

	@Test
	public void createTest() {
		log.info("test");
	}

	@Test
	public void createTest2() {
		log.info("test2");
	}
}
