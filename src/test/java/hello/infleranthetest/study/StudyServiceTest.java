package hello.infleranthetest.study;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import hello.infleranthetest.member.MemberService;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

//	@Mock MemberService memberService;
//	@Mock StudyRepository studyRepository;

	@Test
	void createStudyService(@Mock MemberService memberService,
							@Mock StudyRepository studyRepository) {
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);
	}

}
