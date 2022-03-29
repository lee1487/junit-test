package hello.infleranthetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

	@Test
	@DisplayName("스터디 만들기 \uD83D\uDE31")
	@Disabled
	void assert1() {
		Study study = new Study(-10);
		assertAll(() -> assertNotNull(study),
				() -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
						() -> "스터디를 처음 만들면 " + StudyStatus.DRAFT + " 상태다."),
				() -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다."));
	}

	@Test
	@DisplayName("assertThrows 테스트")
	void assert2() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Study(-10);
		});

		String message = exception.getMessage();
		assertEquals("limit은 0보다 커야 한다.", message);
	}

	@Test
	@DisplayName("assertTimeout 테스트")
	void assert3() {
		assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
			new Study(10);
//			Thread.sleep(300);
		});
		// TODO ThreadLocal
	}

	@Test
	@DisplayName("assertThat 테스트")
	void assert4() {
		Study actual = new Study(10);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}

	@Test
	@DisplayName("assumeTrue 테스트")
	void assume1() {
//		assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST ENV")));
		String test_env = System.getenv("TEST_ENV");
		assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
			System.out.println("local");
			Study actual = new Study(100);
			assertThat(actual.getLimit()).isGreaterThan(0);
		});
		assumingThat("keesun".equalsIgnoreCase(test_env), () -> {
			System.out.println("keesun");
			Study actual = new Study(10);
			assertThat(actual.getLimit()).isGreaterThan(0);
		});
	}

	@Test
	@DisplayName("@EnabledOnOs 테스트")
	@EnabledOnOs({ OS.WINDOWS, OS.LINUX })
	void assume2() {
		System.out.println("@EnabledOnOs");
		Study actual = new Study(100);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("@EnabledOnJre 테스트")
	@EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_11 })
	void assume3() {
		System.out.println("@EnabledOnJre");
		Study actual = new Study(100);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("@EnabledOnJre 테스트")
	@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
	void assume4() {
		System.out.println("@EnabledIfEnvironmentVariable = LOCAL");
		Study actual = new Study(100);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("@EnabledOnJre 테스트")
	@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "keesun")
	void assume5() {
		System.out.println("@EnabledIfEnvironmentVariable = keesun");
		Study actual = new Study(100);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}

	@Test
	void create_new_study_again() {
		System.out.println("create1");
	}

	@BeforeAll
	static void beforeAll() {
		System.out.println("before all");
		System.out.println();
	}

	@AfterAll
	static void afterAll() {
		System.out.println("after all");
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("====Before each====");
	}

	@AfterEach
	void afterEach() {
		System.out.println("====After each====");
		System.out.println();
	}

}
