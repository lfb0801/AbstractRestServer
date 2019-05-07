package com.abstractrestapplication.utils;

import com.abstractrestapplication.annotations.LoginRequired;
import com.abstractrestapplication.models.HateoasResponse;
import com.abstractrestapplication.service.AuthenticationService;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author peaseloxes
 */
@Aspect
public class TokenAspect {
    @Autowired
    @Setter
    private AuthenticationService authService;

    /**
     * Checks whether a valid login token has been passed.
     *
     * @param jointPoint    the original joint point.
     * @param loginRequired the LoginRequired annotation.
     * @return the original response if token valid, Forbidden otherwise.
     * @throws Throwable catch!
     */
    @Around("hasLogin(loginRequired)")
    public Object handleLinkAnnotation(final ProceedingJoinPoint jointPoint,
                                       final LoginRequired loginRequired) throws Throwable {
        for (Object o : jointPoint.getArgs()) {
            if (o.getClass().isAssignableFrom(HttpServletRequest.class)){
                HttpServletRequest request = (HttpServletRequest) o;
                // if login is not required we can skip right ahead
                if (!loginRequired.value()) {
                    return jointPoint.proceed();
                }
                // we should be in a method with a request parameter
                if (authService.isAuthorized(request.getHeader("Authorization")))
                    return jointPoint.proceed();

                // tell the user the bad news
                return new ResponseEntity<>(
                        new HateoasResponse("You are not logged in or your token has expired."),
                        HttpStatus.FORBIDDEN)
                        ;
            }
        }
        // tell the user the bad news
        return new ResponseEntity<>(
                new HateoasResponse("No header found, access denied!"),
                HttpStatus.FORBIDDEN);
    }

    @Pointcut("@annotation(loginRequired)")
    private void hasLogin(final LoginRequired loginRequired) {
    }
}
