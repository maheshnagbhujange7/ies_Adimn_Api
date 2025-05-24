package com.spider.ADMIN_API.service;

import java.util.List;

import com.spider.ADMIN_API.bindings.UnlockAccForm;
import com.spider.ADMIN_API.bindings.UserAccForm;

public interface AccountService {


	public List<UserAccForm> fetchUserAccounts();
	
	public UserAccForm getUserAccById(Integer accId);
	
	public String changeAccStatus(Integer accId, String status);
	
	public String unlockUserAccount(UnlockAccForm unlockAccForm);

	public boolean createUserAccount(UserAccForm accForm);
}
