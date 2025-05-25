package com.spider.ADMIN_API.service;

import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.file.Paths;


import com.spider.ADMIN_API.bindings.DashboardCard;
import com.spider.ADMIN_API.bindings.LoginForm;
import com.spider.ADMIN_API.bindings.UserAccForm;
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
		String subject="Recover Pwd";
		String body=readEmailBody("Forgot_PWD_EMAIL_BODY.txt", userEntity);
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


	@Override
	public UserAccForm getUserByEmail(String email) {
		UserEntity userEntity =	userRepo.findByEmail(email);
        UserAccForm user = new UserAccForm();
        BeanUtils.copyProperties(userEntity, user);
		return user;
	}
	private String readEmailBody(String filename, UserEntity user) {
		StringBuilder sb = new StringBuilder();
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			lines.forEach(line -> {
				line = line.replace("${FNAME}", user.getFullName());
				line = line.replace("${PWD}", user.getPwd());
				line = line.replace("${EMAIL}", user.getEmail());
				sb.append(line);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	}


