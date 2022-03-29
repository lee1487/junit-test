package hello.infleranthetest.repeat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import hello.infleranthetest.Study;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepeatTest {
	
	@DisplayName("스터디 만들기1")
	@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
	void repeatTest(RepetitionInfo repetitionInfo) {
		log.info("test {}/{}", repetitionInfo.getCurrentRepetition(), repetitionInfo.getTotalRepetitions());
	}
	
	@DisplayName("스터디 만들기2")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
	void parameterizedTest(String message) {
		log.info("message={}",message);
	}
	
	@DisplayName("스터디 만들기3")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
	@NullAndEmptySource
	void parameterizedTest2(String message) {
		log.info("message={}",message);
	}
	
	@DisplayName("스터디 만들기4")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@ValueSource(ints = {10, 20, 40})
	void valueSourceIntegerTest(Integer limit) {
		log.info("limit={}",limit);
	}
	
	@DisplayName("스터디 만들기5")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@ValueSource(ints = {10, 20, 40})
	void valueSourceIntegerTest2(@ConvertWith(StudyConverter.class) Study study) {
		log.info("limit={}",study.getLimit());
	}
	
	@DisplayName("스터디 만들기6")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, '자바 스터디'", "20, 스프링"})
	void csvSourceIntegerTest(Integer limit, String name) {
		Study study = new Study(limit, name);
		log.info("limit={}",study);
	}
	
	@DisplayName("스터디 만들기7")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, '자바 스터디'", "20, 스프링"})
	void csvSourceIntegerTest2(ArgumentsAccessor argumentsAccessor) {
		Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
		log.info("limit={}",study);
	}
	
	@DisplayName("스터디 만들기8")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, '자바 스터디'", "20, 스프링"})
	void csvSourceIntegerTest3(@AggregateWith(StudyAggregator.class) Study study) {
		log.info("limit={}",study);
	}
	
	static class StudyAggregator implements ArgumentsAggregator {

		@Override
		public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
				throws ArgumentsAggregationException {
			return new Study(accessor.getInteger(0), accessor.getString(1));
		}
	}
	
	static class StudyConverter extends SimpleArgumentConverter {

		@Override
		protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
			assertEquals(Study.class, targetType, "Can only convert to Study");
			return new Study(Integer.parseInt(source.toString()));
		}
		
	}
}
