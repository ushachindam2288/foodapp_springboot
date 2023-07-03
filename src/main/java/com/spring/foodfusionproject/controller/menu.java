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
public class menu {


	@Autowired
	 private JdbcTemplate jdbcTemplate;
	@Value("${database.name}")
	private String Schema;
	  @GetMapping("menulist")
	    public List<item> menulist() {
	        List<item> item = new ArrayList<item>();

	        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(Schema)
	                .withProcedureName("getmenuitemslist");

	        try {
	            Map<String, Object> resultData = simpleJdbcCall.execute();

	            for (java.util.Map.Entry<String, Object> e : resultData.entrySet()) {
	                String key = e.getKey();
	                if (e.getValue() instanceof List) {
	                    List obj = (List) e.getValue();
	                    for (int i = 0; i < obj.size(); i++) {
	                    	
	                    	item c = new item();
	                        Map map = (Map) obj.get(i);
	                        c.setid((Integer) map.get("id")); 
	                        c.setname((String) map.get("name"));
	                        c.setprice((Integer) map.get("price"));
	                     
	                        item.add(c);
	                    }
	                }
	            }
	        } catch (Exception e) {
	        	   System.out.print(e.getMessage());
	        }
      return item;
	    }
	    @PostMapping("menuupdates")
		public List<item> menuupdates(@RequestBody item s){
			List<item> item = new ArrayList<item>();
	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(Schema).withProcedureName("allmenuitemupdates");
			
	        Map<String, Object> inParamMap = new HashMap<String, Object>();
	        inParamMap.put("i_id", s.getid());
    		inParamMap.put("i_name", s.getname());
    		inParamMap.put("i_price", s.getprice());
    		
    		inParamMap.put("i_flag", s.getFlag());


	        try {
	            Map<String, Object> resultData = simpleJdbcCall.execute(inParamMap);
	            
	         for (java.util.Map.Entry<String, Object> e : resultData.entrySet()) {
	                String key = e.getKey();

	                if (e.getValue() instanceof List) {
	                    List obj = (List) e.getValue();
	                    for (int i = 0; i < obj.size(); i++) {
	                    	
	                    	item c = new item();
	                        Map map = (Map) obj.get(i);
	                        c.setid((Integer) map.get("id")); 
	                        c.setname((String) map.get("name"));
	                        c.setprice((Integer) map.get("price"));
	                     
	                        item.add(c);
	                    }
	                }
	            }
	        } catch (Exception e) {
             System.out.print(e.getMessage());
	        }
        return item;
	    }
	    public static class item {
	        public int id;
	        public String name;
	        public int price;
	      
	        public String flag;
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
			public int getprice() {
				return price;
			}
			public void setprice(int price) {
				this.price = price;
			}
			
			public String getFlag() {
				return flag;
			}
			public void setFlag(String flag) {
				this.flag = flag;
			}
	    }}

