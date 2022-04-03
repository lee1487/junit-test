package hello.infleranthetest.instance;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import hello.infleranthetest.domain.Study;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestInstance(Lifecycle.PER_CLASS)
public class InstanceTest {
	
	@BeforeAll
	void beforeAll() {
		log.info("before all");
	}
	@AfterAll
	void afterAll() {
		log.info("after all");
	}

	int value = 1;
	
	@DisplayName("스터디 만들기1")
	@Test
	void instanceTest1() {
		log.info("this={}", this);
		log.info("value={}", value++);
		Study study = new Study(1);
		log.info("study={}", study);
	}
	
	@DisplayName("스터디 만들기2")
	@Test
	void instanceTest2() {
		log.info("this={}", this);
		Study study = new Study(value++);
		log.info("study={}", study);
	}
	
	
}
