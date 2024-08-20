package com.project.FormAuthentication.Controller;

import com.project.FormAuthentication.Model.ApiResponse;
import com.project.FormAuthentication.Model.Sigininmodel;
import com.project.FormAuthentication.Service.CredentialService;

import com.project.FormAuthentication.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authenticationcontroller")
public class AuthenticationController {
    ApiResponse apiResponse;
    @Autowired
    CredentialService credentialService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signinmethod(@RequestBody Sigininmodel siginInModel) {

        String password = siginInModel.getPassword();
        String con_password = siginInModel.getConfirmpassword();

        if (password.equals(con_password)) {
            return credentialService.savecredentialstodb(siginInModel);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INCORRECT PASSWORD");
        }


    }


    @GetMapping("Login")
    public ResponseEntity<ApiResponse> validation(@RequestParam String username, @RequestParam String password) {

        return credentialService.validatecredentials(username, password);
    }

    @GetMapping("authentication")
    public ResponseEntity<ApiResponse> verifybearertoken(@RequestHeader(value = "Authorization",defaultValue = "") String token){

        apiResponse = new ApiResponse();


        try {
            //String data = jwtUtil.validateToken(token);
            apiResponse.setStatus(200);
           // apiResponse.setData(data);
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setStatus(401);
            apiResponse.setData("INVALID TOKEN");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }


    }
}
