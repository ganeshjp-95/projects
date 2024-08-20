package com.project.FormAuthentication.Service;

import com.project.FormAuthentication.Model.ApiResponse;
import com.project.FormAuthentication.Model.Sigininmodel;
import com.project.FormAuthentication.Repository.CredentialRespository;
import com.project.FormAuthentication.Repository.FormRepository;
import com.project.FormAuthentication.Table.CredentialTable;
import com.project.FormAuthentication.Table.FormTable;
import com.project.FormAuthentication.Util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CredentialService {
    ApiResponse apiResponse;
    @Autowired
    CredentialRespository credentialRespository;
    @Autowired
    FormRepository formRepository;

    @Autowired
    JwtUtil jwtUtil;
    public ResponseEntity<String> savecredentialstodb(Sigininmodel sigininmodel){

        CredentialTable credentialTable = new CredentialTable();

        credentialTable.setName(sigininmodel.getName());
        credentialTable.setUserid(sigininmodel.getUserid());
        credentialTable.setPassword(sigininmodel.getPassword());
        credentialRespository.save(credentialTable);

        return ResponseEntity.ok("USER ID CREATED SUCCESSFULLY,KINDLY LOGIN AGAIN");

    }

    public ResponseEntity<ApiResponse> validatecredentials(String username, String password){

        CredentialTable credentialarray = credentialRespository.findByname(username);
        FormTable formTable = formRepository.findByname(username);
        apiResponse = new ApiResponse();
        if (formTable==null){

            apiResponse.setData("INVALID USERNAME");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }else {
            String db_password = credentialarray.getPassword();

            if (db_password.equals(password)){

                String token = jwtUtil.GenerateJWT(formTable);


                Map<String,Object> map = new HashMap<>();
                map.put("Bearer Token",token);
                map.put("Data",credentialarray);
                apiResponse.setData(map);
                apiResponse.setStatus(200);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }else {

                apiResponse.setData("INVALID PASSWORD");
                return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
            }
        }
    }


}
