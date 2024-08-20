package com.project.FormAuthentication.Model;

import com.project.FormAuthentication.Table.FormTable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmailModel {
    String from,to,subject,body;
    List<FormTable> arrayBody;
}
