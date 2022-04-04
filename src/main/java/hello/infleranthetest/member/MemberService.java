package hello.infleranthetest.member;

import java.util.Optional;

import hello.infleranthetest.domain.Member;
import hello.infleranthetest.domain.Study;

public interface MemberService {

	void validate(Long memberId);

	Optional<Member> findById(Long memberId);


}
