package com.jjangchen.externalmodule.web.interceptor;

import com.jjangchen.externalmodule.service.JWTService;
import com.jjangchen.externalmodule.web.exception.BearerException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws IOException {
        try {
            Claims claims = JWTService.parseToken(request.getHeader("accesstoken"));
            request.setAttribute("social", claims.get("social"));
            request.setAttribute("id", Long.valueOf(claims.get("id").toString()));
            request.setAttribute("role", claims.get("role"));
        } catch (SignatureException e) {
            response.sendError(470, "유효하지 않은 토큰입니다.");
            return false;
        } catch (BearerException e) {
            response.sendError(471, "유효하지 않은 토큰입니다.");
            return false;
        } catch (ExpiredJwtException e) {
            response.sendError(472, "유효기간이 만료된 토큰입니다.");
            return false;
        } catch (NullPointerException e) {
            response.sendError(469, "토큰을 찾을 수 없습니다.");
            return false;
        }
        return true;
    }

}
