package hello.infleranthetest.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Study {

	@Id @GeneratedValue
	private Long id;
	private StudyStatus status = StudyStatus.DRAFT;
	private int limit;
	private String name;
	private LocalDateTime openedDateTime;

	@ManyToOne
	private Member owner;

	public Study(int limit, String name) {
		this.limit = limit;
		this.name = name;
	}

	public Study(int limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("limit은 0보다 커야 한다.");
		}
		this.limit = limit;
	}

	public void publish() {
		this.openedDateTime = LocalDateTime.now();
		this.status = StudyStatus.OPENED;
	}





}
