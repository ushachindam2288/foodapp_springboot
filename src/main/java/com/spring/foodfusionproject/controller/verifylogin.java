package com.spring.foodfusionproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.foodfusionproject.controller.menu.item;

@RestController
@CrossOrigin
@Repository

public class verifylogin {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Value("${database.name}")
	private String Schema;
	
	@GetMapping("verifyuserlogin")
	@ResponseBody
    public login verifyuserlogin(String name) {
		login logins = new login();

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(Schema).withProcedureName("verifylogin");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("i_name", name);
      
        

        try {
            Map<String, Object> resultData = simpleJdbcCall.execute(inParamMap);

            for (Map.Entry<String, Object> entry : resultData.entrySet()) {
                if (entry.getValue() instanceof List) {
                    List<Object> objList = (List<Object>) entry.getValue();
                    for (Object obj : objList) {
                        if (obj instanceof Map) {
                            Map<String, Object> map = (Map<String, Object>) obj;
                            
                            logins.setid((Integer) map.get("id"));
                            logins.setname((String) map.get("name"));
                            
                            
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return logins;
    }
public static class login{
	public int id;
	public String name;
	public String password;
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
}


}
