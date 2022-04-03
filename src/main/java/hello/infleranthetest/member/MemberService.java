package hello.infleranthetest.member;

import java.util.Optional;

import hello.infleranthetest.domain.Member;
import hello.infleranthetest.domain.Study;

public interface MemberService {

//	void validate(Long memberId) throws InvalidMemberException;

	Optional<Member> findById(Long memberId);


}
