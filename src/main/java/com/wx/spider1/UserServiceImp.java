package com.wx.spider1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

public class UserServiceImp implements UserService {

	public void save(User user) {
		
		System.out.println("保存user"+user);
		DBHelper db=new DBHelper();
		String sql="insert into userinfo(name,introduce,ask,answer) values(?,?,?,?)";
		List<String> valuelist=new ArrayList<String>();
		valuelist.add(user.getName());
		valuelist.add(user.getIntroduce());
		valuelist.add(user.getAsk());
		valuelist.add(user.getAnswer());
		int executeUpdate = db.executeUpdate(sql, valuelist);
		if(user==null)
		{
			db.close();
		}
		
	}

}
