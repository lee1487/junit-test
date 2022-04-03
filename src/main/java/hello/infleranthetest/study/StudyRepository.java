package hello.infleranthetest.study;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.infleranthetest.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long>{


}
