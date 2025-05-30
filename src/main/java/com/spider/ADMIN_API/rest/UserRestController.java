package com.spider.ADMIN_API.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spider.ADMIN_API.bindings.DashboardCard;
import com.spider.ADMIN_API.bindings.LoginForm;
import com.spider.ADMIN_API.bindings.UserAccForm;
import com.spider.ADMIN_API.service.AccountService;
import com.spider.ADMIN_API.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		String status = userService.login(loginForm);
		
		if (status.equals("Success")) {
			return "redirect:/dashboard?email="+loginForm.getEmail();
		}else {
			return status;
		}
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<DashboardCard>buildDashboard(@RequestParam("email")String email){
	UserAccForm user = userService.getUserByEmail(email);
	DashboardCard dashboardCard  =	userService.fetchDashboarddInfo();
	dashboardCard.setUser(user);
	
	return new ResponseEntity<>(dashboardCard,HttpStatus.OK);
	}
}
