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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@Repository

public class user {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Value("${database.name}")
	private String Schema;
	
	
	
	@GetMapping("customerlist")
	public List<login> customerlist(){
		
	List<login> logins = new ArrayList<login>();
	
	
SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(Schema).withProcedureName("getcustomerslist");

		//Map<String, Object> inParamMap = new HashMap<String, Object>();

		//inParamMap.put("i_Id", id);
	
		
		try {
			Map<String, Object> resultData = simpleJdbcCall.execute();

			for (java.util.Map.Entry<String, Object> e : resultData.entrySet()) {
				String key = e.getKey();
				
				if (e.getValue() instanceof List) {
					List obj = (List) e.getValue();
					for (int i = 0; i < obj.size(); i++) {
						
						login c = new login();
						Map map = (Map) obj.get(i);
						c.setid((Integer) map.get("id"));
						c.setname((String) map.get("name"));
						c.setaddress((String) map.get("address"));
						c.setemail((String) map.get("email"));
					    c.setpassword((String) map.get("password"));
						logins.add(c);
						//
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return logins;
	}
	
	@PostMapping("customerupdates")
	public List<login> customerupdates(@RequestBody login s){
		List<login> logins = new ArrayList<login>();
SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(Schema).withProcedureName("registeruser");
	
Map<String, Object> inParamMap = new HashMap<String, Object>();
	inParamMap.put("i_id", s.getid());
	inParamMap.put("i_name", s.getname());
	inParamMap.put("i_address", s.getaddress());
	inParamMap.put("i_email", s.getemail());
	inParamMap.put("i_password", s.getpassword());
	inParamMap.put("i_flag", s.getflag());
	
	try {
		Map<String, Object> resultData = simpleJdbcCall.execute(inParamMap);

		for (java.util.Map.Entry<String, Object> e : resultData.entrySet()) {
			String key = e.getKey();
			
			if (e.getValue() instanceof List) {
				List obj = (List) e.getValue();
				for (int i = 0; i < obj.size(); i++) {
					login c = new login();
					Map map = (Map) obj.get(i);
					c.setid((Integer) map.get("id"));
					c.setname((String) map.get("name"));
					c.setaddress((String) map.get("address"));
					c.setemail((String) map.get("email"));
				    c.setpassword((String) map.get("password"));
					logins.add(c);
					//
				}
			}

		}
	} catch (Exception e) {
		System.out.print(e.getMessage());
	}

return logins;
}
public static class login{
	public int id;
	public String name;
	public String address;
	public String email;
	public String password;
	private String flag;
	public int getid() {
		return id;
	}
	
	public String getflag() {
		return flag;
	}
	public void setflag(String flag) {
		this.flag = flag;
	}

	public void setid(Integer integer) {
		// TODO Auto-generated method stub
		
	}

	public void setlogin_id(int id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	
	public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
		this.address = address;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}

}
public void setid(Integer integer) {
	// TODO Auto-generated method stub
	
}

public static void setname(String string) {
	// TODO Auto-generated method stub
	
}

public void add(user c) {
	// TODO Auto-generated method stub
	
}


}
	
	

