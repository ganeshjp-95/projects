package com.project.FormAuthentication.Service;

import com.project.FormAuthentication.Model.EmailModel;
import com.project.FormAuthentication.Model.FormModel;
import com.project.FormAuthentication.Model.FormRequestModel;
import com.project.FormAuthentication.Model.TokenDatas;
import com.project.FormAuthentication.Repository.CredentialRespository;
import com.project.FormAuthentication.Repository.FormRepository;
import com.project.FormAuthentication.Table.CredentialTable;
import com.project.FormAuthentication.Table.FormTable;
import com.project.FormAuthentication.Util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class FormService {

    @Autowired
    CredentialRespository credentialRespository;
    @Autowired
    FormRepository formRepository;

    @Autowired
    ExcelUtil excelUtil;

    @Autowired
    TokenDatas tokenDatas;

    @Autowired
    EmailService emailService;
    public ResponseEntity<String> submitform(FormModel formModel) {


        FormTable formTable = new FormTable();

        formTable.setName(formModel.getName());
        formTable.setAge(formModel.getAge());
        formTable.setRole(formModel.getRole());
        formTable.setSalary(formModel.getSalary());
        formTable.setOrganisation(formModel.getOrganisation());
        formTable.setEmployeeid(formModel.getEmployeeid());

        formRepository.save(formTable);

        return ResponseEntity.ok("Form Submitted Successfully");
    }

    public ResponseEntity<?> getformdetails(FormRequestModel formRequestModel){

        FormTable formTable = formRepository.findByuserid(formRequestModel.getUserid());

        if (formTable!=null){

            if (tokenDatas.getRole().equalsIgnoreCase("HR"))
            {
                if (formRequestModel.isFetchall())
                {
                    List<FormTable> formarry = formRepository.findAll();

                    //emailService.sendmail(formarry);
                   return new ResponseEntity<>(formarry,HttpStatus.OK);
                }else
                {
                    FormTable selectedname = formRepository.findByname(formRequestModel.getFetchselectedname());
                     //emailService.sendmailasobject(selectedname);
                   return new ResponseEntity<>(selectedname,HttpStatus.OK);
                }

            }else {

                if (formRequestModel.getFetchselectedname().equalsIgnoreCase(formTable.getName())){

                    // emailService.sendmailasobject(formTable);
                    return new ResponseEntity<>(formTable,HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("NOT AUTHORISED",HttpStatus.NOT_FOUND);
                }

            }
        }else {
            return new ResponseEntity<>("INVALID USERID",HttpStatus.NOT_FOUND);
        }
/*
CredentialTable credentialTable = credentialRespository.findByname(formRequestModel.getUserid());
if (credentialTable !=null){

        }else {
            return new ResponseEntity<>("INVALID USERID",HttpStatus.NOT_FOUND);
        }
  */





    }

    public ByteArrayInputStream downloadexcel(){
        List<FormTable> listofdatas = formRepository.findAll();
        return excelUtil.dataToExcel(listofdatas);

    }


}
