package cn.spring.mvn.socket.test;

//import org.springframework.beans.factory.annotation.Autowired;

import cn.spring.mvn.socket.base.SocketBase;
import cn.spring.mvn.socket.entity.User;
import cn.spring.mvn.socket.entity.service.UserService;

public class UserServiceImplTest extends SocketBase{
//	@Autowired
	private static UserService userServiceImpl = getApplicationContext().getBean("UserService",UserService.class);; 
	public static void main(String[] args) {
//		UserService userServiceImpl = getApplicationContext().getBean("UserService",UserService.class);
//		User user = userServiceImpl.getUser("1001");
//		System.out.println(user.getUsername());
		System.out.println(getString("1001"));
	}
	
	public static String getString(String userid){
//		UserService userServiceImpl = getApplicationContext().getBean("UserService",UserService.class);
		User user = userServiceImpl.getUser(userid);
//		String str = user.get
		return user.toString();
	}
	
}
