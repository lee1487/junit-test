package hello.infleranthetest.study;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import hello.infleranthetest.domain.Member;
import hello.infleranthetest.domain.Study;
import hello.infleranthetest.member.MemberService;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

	@Mock MemberService memberService;
	@Mock StudyRepository studyRepository;

	@Test
	void createNewStudyTest() {
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("dlgustp1487@naver.com");

		when(memberService.findById(any())).thenReturn(Optional.of(member));

		Study study = new Study(10, "java");
		assertEquals("dlgustp1487@naver.com", memberService.findById(1L).get().getEmail());
		assertEquals("dlgustp1487@naver.com", memberService.findById(2L).get().getEmail());

		doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
		assertThrows(IllegalArgumentException.class, () -> {
			memberService.validate(1L);
		});
		memberService.validate(2L);
//		studyService.createNewStudy(1L, study);


	}

	@Test
	void createNewStudyTest2() {

		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("dlgustp1487@naver.com");

		when(memberService.findById(any()))
				.thenReturn(Optional.of(member))
				.thenThrow(new RuntimeException())
				.thenReturn(Optional.empty());

		Optional<Member> findById = memberService.findById(1L);
		assertEquals("dlgustp1487@naver.com", memberService.findById(1L).get().getEmail());
		assertThrows(RuntimeException.class, () -> {
			memberService.findById(2L);
		});

		assertEquals(Optional.empty(), memberService.findById(3L));
	}

	@Test
	void practiceTest() {
		StudyService studyService = new StudyService(memberService, studyRepository);
		Study study = new Study(10, "테스트");

		Member member = new Member();
		member.setId(1L);
		member.setEmail("dlgustp1487@naver.com");

		when(memberService.findById(1L)).thenReturn(Optional.of(member));
		when(studyRepository.save(study)).thenReturn(study);

		studyService.createNewStudy(1L, study);
		assertNotNull(study.getOwner());
		assertEquals(member, study.getOwner());
	}

	@Test
	void mockObjectCheckTest() {
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("dlgustp1487@naver.com");

		Study study = new Study(10, "테스트");

		when(memberService.findById(1L)).thenReturn(Optional.of(member));
		when(studyRepository.save(study)).thenReturn(study);

		studyService.createNewStudy(1L, study);
		assertEquals(member, study.getOwner());


		verify(memberService, times(1)).notify(study); // 몇번을 호출하는지

		verifyNoMoreInteractions(memberService);	// 특정 시점 이후에 아무 일도 벌어지지 않았는지

//		verify(memberService, times(1)).notify(member);
//		verify(memberService, never()).validate(any());
//
//		InOrder inOrder = inOrder(memberService);	// 어떤 순서대로 호출했는지
//		inOrder.verify(memberService).notify(study);

//		inOrder.verify(memberService).notify(member);

	}

}
