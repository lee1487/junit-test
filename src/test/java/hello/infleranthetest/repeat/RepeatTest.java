package hello.infleranthetest.repeat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepeatTest {
	
	@DisplayName("스터디 만들기")
	@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
	void repeatTest(RepetitionInfo repetitionInfo) {
		log.info("test {}/{}", repetitionInfo.getCurrentRepetition(), repetitionInfo.getTotalRepetitions());
	}
	
	@DisplayName("스터디 만들기")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
	void parameterizedTest(String message) {
		log.info("message={}",message);
	}
}
