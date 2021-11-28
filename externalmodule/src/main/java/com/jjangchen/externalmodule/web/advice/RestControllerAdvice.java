package com.jjangchen.externalmodule.web.advice;

import com.jjangchen.externalmodule.service.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Aspect
@Slf4j
public class RestControllerAdvice {
    private final JWTService jwtService;

    @Around("@annotation(ValidJwtToken)")
    public Object validToken(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        String accessToken = objects[0].toString();
        jwtService.parseToken(accessToken);
        return joinPoint.proceed();
    }
}
