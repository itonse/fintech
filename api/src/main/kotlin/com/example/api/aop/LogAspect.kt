package com.example.api.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
@Component
class LogAspect {
    val logger = KotlinLogging.logger { }

    // api 모듈인지를 확인하는 Join points 를 가져오는 Pointcut
    @Pointcut("within(com.example.api..*)")  // api 패키지 내의 메소드들이 실행될 때 모두 로그를 찍음
    private fun isApi() {}

    @Around("isApi()")
    fun loggingAspect(joinPoint: ProceedingJoinPoint): Any {   // join Point 를 가져와서 그 다음 join Point 로 넘김
        val stopwatch = StopWatch()
        stopwatch.start()   // 스탑워치 시작 (성능 측정)

        val result = joinPoint.proceed()   // joinPoint 를 실행해서 응답값을 받음

        stopwatch.stop()   // 실행이 완료되면 스탑워치 종료

        // 로그 찍기: jointPoint 의 이름, jointPoint 에 어떤 args 가 들어왔는지, 해당 joinPoint 를 실행할 때 걸린 시간
        logger.info { "${joinPoint.signature.name} ${joinPoint.args[0]} ${stopwatch.lastTaskTimeMillis}" }

        return result  // 그 다음 join Point 로 넘어감
    }
}