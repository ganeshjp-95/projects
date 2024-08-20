package com.project.FormAuthentication.Repository;

import com.project.FormAuthentication.Table.CredentialTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRespository extends JpaRepository<CredentialTable,Integer> {

         CredentialTable findByname(String userid);
}
