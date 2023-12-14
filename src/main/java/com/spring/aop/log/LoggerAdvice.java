package com.spring.aop.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/* 
	# 스프링 로그파일 설정 방법 

	1) pom.xml에서 log4j의존성 추가 (기본값으로 셋팅되어 있음)
	
	2) log4j.xml 설정 파일에서 appender 설정
	
	<!-- DailyFile Appenders -->
	<appender name="dailyFileAppender" class="org.apache.log4j.DailyRollingFileAppender">
		<param name="File" value="파일명" />
		<layout class="org.apache.log4j.PatternLayout">
		  <param name="DatePattern"   value="'.'yyyy-MM-dd-HH-mm"/>
		  <param name="ConversionPattern"   value="[%d{HH:mm:ss}][%-5p](%F:%L)-%m%n"/>
		</layout>
	</appender>
	
	
	3) log4j.xml 설정 파일에서 appender 추가
	<!-- Root Logger -->
	<root>
		<priority value="warn" />
		<appender-ref ref="console" />
		<appender-ref ref="dailyFileAppender" />  
	</root>

*/

@Aspect
@Component
public class LoggerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class); // 같은 형식에 클래스 이름만 해당 클래스로 바꾸면 됨.
	/* 
	
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;

 	Logger객체 생성 (private) static Logger logger = LoggerFactory.getLogger(클래스이름.class);

 	- 여러개발자가 협업을 하는 경우 log4j 로깅방식을 통해서 로깅방법을 통일할 수 있다.
 	- log4j를 사용하여 로그의 레벨별로 체계적인 로그관리가 가능하다.
 	- 로깅 레벨을 설정하면 그 이상 레벨을 로깅한다.
 	
	 	level 1) FATAL : 가장 심각한 에러 발생시 사용
	 	level 2) ERROR : 일반 에러 발생시 사용
	 	level 3) WARN  : 에러는 아니지만 주의할 필요가 있을경우 사용
	 	level 4) INFO  : 일반 정보를 나타낼 경우 사용
	 	level 5) DEBUG : 일반 정보를 상세히 나타낼 경우 사용
		level 6) TRACE : 디버그 레벨이 너무 광범위한것을 해결하기 위해서 좀 더 상세한 이벤트를 나타내기 위해 사용
  	
	 */	

	@Around("execution(public void com.spring.aop.*.*(..))")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		pjp.proceed();
		long endTime = System.currentTimeMillis();
		
		//System.out.println(pjp.getSignature().getName() + "메서드 동작시간 : " + (endTime - startTime)/1000.0 + "초");
		logger.info(pjp.getSignature().getName() + "메서드 동작시간 : " + (endTime - startTime)/1000.0 + "초");
		
	}
	
	
	
	
	
	
}
