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

import com.spring.foodfusionproject.controller.user.login;
@RestController
@CrossOrigin
@Repository
public class Order {


		@Autowired
		private JdbcTemplate jdbcTemplate;
		@Value("${database.name}")
		private String Schema;
		
		
		
		@GetMapping("orderlist")
		public List<order> orderlist(){
			
		List<order> order = new ArrayList<order>();
		
		
	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(Schema).withProcedureName("getorderslist");

			//Map<String, Object> inParamMap = new HashMap<String, Object>();

			//inParamMap.put("i_Id", id);
		
			
			try {
				Map<String, Object> resultData = simpleJdbcCall.execute();

				for (java.util.Map.Entry<String, Object> e : resultData.entrySet()) {
					String key = e.getKey();
					
					if (e.getValue() instanceof List) {
						List obj = (List) e.getValue();
						for (int i = 0; i < obj.size(); i++) {
							
							order c = new order();
							Map map = (Map) obj.get(i);
							c.setid((Integer) map.get("id"));
							c.setname((String) map.get("name"));
							c.setquantity((String) map.get("quantity"));
							order.add(c);
							//
						}
					}

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			return order;
		}
		
		@PostMapping("updateorder")
		public List<order> updateorder(@RequestBody order s){
			List<order> order = new ArrayList<order>();
	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(Schema).withProcedureName("insertorder");
		
	Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("i_id", s.getid());
		inParamMap.put("i_name", s.getname());
		inParamMap.put("i_quantity", s.getquantity());
	
		
		try {
			Map<String, Object> resultData = simpleJdbcCall.execute(inParamMap);

			for (java.util.Map.Entry<String, Object> e : resultData.entrySet()) {
				String key = e.getKey();
				
				if (e.getValue() instanceof List) {
					List obj = (List) e.getValue();
					for (int i = 0; i < obj.size(); i++) {
						order c = new order();
						Map map = (Map) obj.get(i);
						c.setid((Integer) map.get("id"));
						c.setname((String) map.get("name"));
						c.setquantity((String) map.get("quantity"));
						
					    order.add(c);
						//
					}
				}

			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

	return order;
	}
	public static class order{
		public int id;
		public String name;
		public String quantity;
		
		public int getid() {
			return id;
		}
		


		public void setid(Integer integer) {
			// TODO Auto-generated method stub
			
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
		
		public String getquantity() {
			return quantity;
		}
		public void setquantity(String quantity) {
			this.quantity = quantity;
		}
	
	}
	public void setid(Integer integer) {
		// TODO Auto-generated method stub
		
	}

	public static void setname(String string) {
		// TODO Auto-generated method stub
		
	}

	public void add(order c) {
		// TODO Auto-generated method stub
		
	}

}
