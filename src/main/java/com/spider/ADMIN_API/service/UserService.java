package com.spider.ADMIN_API.service;

import com.spider.ADMIN_API.bindings.DashboardCard;
import com.spider.ADMIN_API.bindings.LoginForm;
import com.spider.ADMIN_API.bindings.UserAccForm;

public interface UserService {

	public  String login(LoginForm loginform);
	
	public boolean recoverPwd(String email);
	
	public DashboardCard fetchDashboarddInfo();

	public UserAccForm getUserByEmail(String email);
	
	 
}
