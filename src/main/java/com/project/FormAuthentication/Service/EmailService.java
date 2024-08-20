package com.project.FormAuthentication.Service;

import com.project.FormAuthentication.Model.EmailModel;
import com.project.FormAuthentication.Table.FormTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {


    @Autowired
    JavaMailSender mailSender;

/*    public ResponseEntity<?> sendmail(EmailModel emailModel)
    {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();


        simpleMailMessage.setFrom(emailModel.getFrom());
        simpleMailMessage.setTo(emailModel.getTo());
        simpleMailMessage.setSubject(emailModel.getSubject());

        StringBuilder body = new StringBuilder();
        for (FormTable list : emailModel.getArrayBody())
        {
            body.append(list).append("\n");
        }

        simpleMailMessage.setText(body.toString());

        mailSender.send(simpleMailMessage);

        return new ResponseEntity<>("MAIL SENT SUCCESSFULLY", HttpStatus.OK);
    }*/

    public ResponseEntity<?> sendmail(List<FormTable> emailModel)
    {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();


        simpleMailMessage.setFrom("ganeshjp95@gmail.com");
        simpleMailMessage.setTo("ganeshjp95@gmail.com");
        simpleMailMessage.setSubject("SMTP MAIL");

        StringBuilder body = new StringBuilder();
        for (FormTable list : emailModel)
        {
            body.append(list.toString()).append("\n");
        }

        simpleMailMessage.setText(body.toString());

        mailSender.send(simpleMailMessage);

        return new ResponseEntity<>("MAIL SENT SUCCESSFULLY", HttpStatus.OK);
    }

    public ResponseEntity<?> sendmailasobject(FormTable formTable) {
        StringBuilder body = new StringBuilder();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();


        simpleMailMessage.setFrom("ganeshjp95@gmail.com");
        simpleMailMessage.setTo("ganeshjp95@gmail.com");
        simpleMailMessage.setSubject("SMTP MAIL");
        simpleMailMessage.setText(body.append(formTable.toString()).toString());


        mailSender.send(simpleMailMessage);

        return new ResponseEntity<>("MAIL SENT SUCCESSFULLY", HttpStatus.OK);
    }
}
