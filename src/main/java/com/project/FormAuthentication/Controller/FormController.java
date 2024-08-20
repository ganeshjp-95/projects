package com.project.FormAuthentication.Controller;

import com.project.FormAuthentication.Model.EmailModel;
import com.project.FormAuthentication.Model.FormModel;
import com.project.FormAuthentication.Model.FormRequestModel;
import com.project.FormAuthentication.Service.EmailService;
import com.project.FormAuthentication.Service.FormService;
import com.project.FormAuthentication.Table.FormTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("Forms")
public class FormController {
    @Autowired
    FormService formService;

    @Autowired
    EmailService emailService;

    @PostMapping("submit")
    public ResponseEntity<String> submitform(@RequestBody FormModel formModel){

        return formService.submitform(formModel);

    }

    @PostMapping("FetchForm")
    public ResponseEntity<?> getformdetails(@RequestBody FormRequestModel formRequestModel){
        return formService.getformdetails(formRequestModel);
    }

    @GetMapping("downloaded")
    public ResponseEntity<InputStreamResource> download(){
        ByteArrayInputStream inputStream =formService.downloadexcel();
        InputStreamResource response = new InputStreamResource(inputStream);
        String filename ="Forms.xlsx";

        ResponseEntity<InputStreamResource> responseEntity = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(response);
        return responseEntity;
    }


/*    @PostMapping("SendMail")
    public ResponseEntity<?> sendmail(@RequestBody EmailModel emailModel){
        return emailService.sendmail(emailModel);
    }*/
}
