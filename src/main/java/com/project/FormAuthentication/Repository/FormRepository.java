package com.project.FormAuthentication.Repository;

import com.project.FormAuthentication.Table.FormTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<FormTable,Integer> {


   FormTable findByname(String name);

   FormTable findByuserid(String userid);

}
