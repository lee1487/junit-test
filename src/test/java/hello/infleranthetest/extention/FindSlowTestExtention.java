package hello.infleranthetest.extention;

import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import hello.infleranthetest.annotation.SlowTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindSlowTestExtention implements BeforeTestExecutionCallback, AfterTestExecutionCallback{

	private long THRESHOLD;
	
	public FindSlowTestExtention(long THRESHOLD) {
		this.THRESHOLD = THRESHOLD; 
	}

	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		Store store = getStore(context);
		store.put("START_TIME", System.currentTimeMillis());
	}
	
	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		Method requiredTestMethod = context.getRequiredTestMethod();
		SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);
		
		String testMethodName = context.getRequiredTestMethod().getName();
		Store store = getStore(context);
		long start_time = store.remove("START_TIME", long.class);
		long duration = System.currentTimeMillis() - start_time;
		if (duration > THRESHOLD && annotation == null) {
			log.info("Please consider mark method [{}] with @SlowTest.\n", testMethodName);
		}
	}

	private Store getStore(ExtensionContext context) {
		String testClassName = context.getRequiredTestClass().getName();
		String testMethodName = context.getRequiredTestMethod().getName();
		Store store = context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
		return store;
	}

}
