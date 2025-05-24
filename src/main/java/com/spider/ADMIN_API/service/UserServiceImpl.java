package com.spider.ADMIN_API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spider.ADMIN_API.bindings.DashboardCard;
import com.spider.ADMIN_API.bindings.LoginForm;
import com.spider.ADMIN_API.entities.EligEntity;
import com.spider.ADMIN_API.entities.UserEntity;
import com.spider.ADMIN_API.repositires.EligRepo;
import com.spider.ADMIN_API.repositires.PlanRepo;
import com.spider.ADMIN_API.repositires.UserRepo;
import com.spider.ADMIN_API.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private EligRepo eligRepo;
	
	
	@Override
	public String login(LoginForm loginform) {

	UserEntity entity =	userRepo.findByEmailAndPwd(loginform.getEmail(), loginform.getPwd());
		
	if(entity==null) {
		return "Invalid Credentials ";
	}
	if ("Y".equals(entity.getActiveSw()) && "Unlocked".equals(entity.getAccStatus())) {
		return "Success";
	}else {
		return "Account Locked/In-Active";
	}
}


	@Override
	public boolean recoverPwd(String email) {	
	UserEntity userEntity =	userRepo.findByEmail(email);
	 if (null == userEntity) {
		return false;
	}else {
		String subject="";
		String body="";
		return  emailUtils.sendEmail(subject, body, email);
	}
	
}

	@Override
	public DashboardCard fetchDashboarddInfo() {
	
	long planCount = planRepo.count();
	
	List<EligEntity> eligList = eligRepo.findAll();
	
	long approvedCnt = eligList.stream().filter(ed -> ed
			                            .getPlanStatus().equals("AP")).count();
	
	long deniedCnt = eligList.stream().filter(ed ->ed
			                          .getPlanStatus().equals("DN")).count();
	
    Double total=eligList.stream().mapToDouble(ed -> ed
    		                      .getBenifitAmt()).sum();
	
	DashboardCard card = new DashboardCard();
	
	card.setPlansCnt(planCount);
	card.setApprovedCnt(approvedCnt);
	card.setBeniftAmtGiven(total);
	card.setDeniedCnt(deniedCnt);
		
		return card;
	}

	
}
