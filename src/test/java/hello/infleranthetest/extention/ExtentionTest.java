package hello.infleranthetest.extention;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import hello.infleranthetest.annotation.FastTest;
import hello.infleranthetest.annotation.SlowTest;
import hello.infleranthetest.domain.Study;
import hello.infleranthetest.extention.FindSlowTestExtention;


//@ExtendWith(FindSlowTestExtention.class)
class ExtentionTest {

	//테스트 파일 - run configuration - include and exclude tags Configuration 설정으로 fast추가하면 fast만 실행
	//maven test -> pom.xml profiles 태그 참조, 프로젝트 우클릭 Maven Select maven profiles 에서 id 선택후 maven test 실행

	@RegisterExtension
	static FindSlowTestExtention extention = new FindSlowTestExtention(1000L);

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
	void tagging2() throws InterruptedException {
		Thread.sleep(1005L);
		System.out.println("tagging slow");
	}

	@FastTest
	@DisplayName("태깅 테스트 fast")
	void tagging3() {
		System.out.println("custom tagging fast");
		Study study = new Study(100);
		assertThat(study.getLimit()).isGreaterThan(0);
	}

	@SlowTest
	@DisplayName("태깅 테스트 slow")
	void tagging4() throws InterruptedException {
		Thread.sleep(1005L);
		System.out.println("custom tagging slow");
	}

}
