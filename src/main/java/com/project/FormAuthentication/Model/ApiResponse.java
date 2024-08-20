package com.project.FormAuthentication.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    Integer status;
    Object Data;
    Object Error;
}
