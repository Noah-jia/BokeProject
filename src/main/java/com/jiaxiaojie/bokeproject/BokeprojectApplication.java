package com.jiaxiaojie.bokeproject;

import com.jiaxiaojie.bokeproject.domain.User;
import com.jiaxiaojie.bokeproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import java.util.List;

@SpringBootApplication
public class BokeprojectApplication {

    public static void main(String[] args) {

                SpringApplication.run(BokeprojectApplication.class, args);
    }

}
