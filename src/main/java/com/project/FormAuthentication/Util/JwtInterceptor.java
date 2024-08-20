package com.project.FormAuthentication.Util;


import com.project.FormAuthentication.Model.TokenDatas;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    TokenDatas tokenDatas;

    public JwtInterceptor(TokenDatas tokenDatas) {

        this.tokenDatas = tokenDatas;
    }


/*    public JwtInterceptor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        System.out.println(request.getRequestURI());
        System.out.println(request.getHeader("Authorization"));

        String token = request.getHeader("Authorization");

        if (request.getRequestURI().equalsIgnoreCase("/authenticationcontroller/Login")){
            System.out.println("if Part");
            //return super.preHandle(request, response, handler);
            return true;
        }else {
            try{
                System.out.println("Else Part");
                Claims claims = jwtUtil.validateToken(token);


                tokenDatas.setRole(claims.get("Role").toString());
                tokenDatas.setUsername(claims.getIssuer().toString());

                return true;
            } catch (SignatureException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("INVALID SIGNATURE");
                return false;
            }  catch (ExpiredJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("TOKEN EXPIRED");
                return false;
            } catch (Exception e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("INVALID TOKEN");
                return false;
            }
        }


       // return super.preHandle(request, response, handler);
    }
}
