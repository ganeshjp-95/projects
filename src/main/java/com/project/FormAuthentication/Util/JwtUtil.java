package com.project.FormAuthentication.Util;


import com.project.FormAuthentication.ExceptionClasses.ExpiredException;
import com.project.FormAuthentication.ExceptionClasses.InvalidException;
import com.project.FormAuthentication.Repository.FormRepository;
import com.project.FormAuthentication.Table.FormTable;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {


    @Autowired
    FormRepository formRepository;
    private static String secretekey ="12233errrr";
    private static long expiryDuration = 60*60;

    public String GenerateJWT(FormTable formTable){

        long milli_time = System.currentTimeMillis();
        long expiry_time = milli_time + expiryDuration * 1000;
        Date issuedAt= new Date(milli_time);
        Date expiryAt = new Date(expiry_time);



        //Claims

        if (formTable!=null){
            Claims claims = Jwts.claims()
                    .setIssuer(formTable.getName())
                    .setIssuedAt(issuedAt)
                    .setExpiration(expiryAt);

            //Optional Claims
            claims.put("Role",formTable.getRole());

            return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,secretekey).compact();
        }else {
            return "INVALID USERNAME";
        }


    }

    public Claims validateToken(String token) throws Exception {
        try{

             String jwtToken = token.substring(7);
             return Jwts.parser().setSigningKey(secretekey).parseClaimsJws(jwtToken).getBody();
             //return "TOKEN VALIDATED SUCCESSFULLY";

        } catch (SignatureException e) {
            throw  new SignatureException("INVALID SIGNATURE");
        } catch (ExpiredJwtException e) {
            throw  new ExpiredException("INVALID TOKEN");
        } catch (Exception e){
            throw new InvalidException("INVALID TOKEN");
        }

    }

}
/*
 catch (SignatureException e) {
        throw  new SignatureException("INVALID SIGNATURE");
        } catch (ExpiredJwtException e) {
        throw  new ExpiredException("INVALID TOKEN");
        } catch (Exception e){
        throw new InvalidException("INVALID TOKEN");
        }*/
