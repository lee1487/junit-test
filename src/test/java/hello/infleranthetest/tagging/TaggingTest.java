package hello.infleranthetest.tagging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import hello.infleranthetest.Study;

class TaggingTest {


	@Test
	@DisplayName("태깅 테스트 fast")
	@Tag("fast")
	void tagging1() {
		System.out.println("tagging fast");
		Study study = new Study(100);
		assertThat(study.getLimit()).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("태깅 테스트 slow")
	@Tag("slow")
	void tagging2() {
		System.out.println("tagging slow");
	}

}
