package com.tsingglobal.system.user.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsingglobal.system.org.domain.OrganizationModel;
import com.tsingglobal.system.user.dao.UserDao;
import com.tsingglobal.system.user.domain.UserModel;
import com.tsingglobal.system.user.service.UserService;
import com.tsingglobal.utils.CommonUtil;
import com.tsingglobal.utils.SnowflakeIdWorker;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public UserModel loadUser(long id) {
		// TODO Auto-generated method stub
		return userDao.loadUser(id);
	}

	@Override
	public List<UserModel> queryUser(UserModel user) {
		
		return userDao.queryUsers(user);
	}

	@Override
	public long saveUser(UserModel user) {
		
		user.setId(new SnowflakeIdWorker(0, 0).nextId());
		
		userDao.saveUser(user);
		
		return user.getId();
	}

	@Override
	public long updateUser(UserModel user) {

		userDao.updateUser(user);
		
		return user.getId();
	}

	@Override
	public long delUser(long id) {

		userDao.delUser(id);
		
		return id;
	}

	@Override
	public long delUserByOrg(final String orgCode) {
		
		userDao.delUserByOrg(orgCode);
		
		return 0;
	}

	@Autowired
	private UserDao userDao;

	@Override
	public List<UserModel> queryUserByOrg( final OrganizationModel org, final UserModel user) {

		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("org", org);
		conditionMap.put("user", user);
		return userDao.queryUsersByOrg(conditionMap);
	}

	@Override
	public void delUsers(String ids) {
		
		if( CommonUtil.isEmpty(ids) ) {
			
			return ;
		}
		
		userDao.delUsers(ids.split(","));
	}

	@Override
	public long lockUser(final UserModel user) {
		
		UserModel lockUser = this.userDao.loadUser(user.getId());
		
		lockUser.setLockedOK(user.getLockedOK()) ;
		
		return this.updateUser( lockUser );		
	}
}
