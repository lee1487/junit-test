package hello.infleranthetest.study;

import java.util.Optional;

import hello.infleranthetest.domain.Member;
import hello.infleranthetest.domain.Study;
import hello.infleranthetest.member.MemberService;

public class StudyService {

	private final MemberService memberService;
	private final StudyRepository repository;

	public StudyService(MemberService memberService, StudyRepository repository) {
		assert memberService != null;
		assert repository != null;
		this.memberService = memberService;
		this.repository = repository;
	}

	public Study createNewStudy(Long memberId, Study study) {
		Optional<Member> member = memberService.findById(memberId);
		study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'")));
		Study newStudy = repository.save(study);
		memberService.notify(newStudy);
//		memberService.notify(member.get());
		return newStudy;
	}


}
