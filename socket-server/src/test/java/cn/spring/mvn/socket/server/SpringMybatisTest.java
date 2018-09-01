package cn.spring.mvn.socket.server;

import cn.spring.mvn.socket.entity.User;
import cn.spring.mvn.socket.entity.dao.UserDao;


public class SpringMybatisTest extends TestBase{
	public static void main(String[] args) {
		UserDao userDao = getApplicationContext().getBean("userDao", UserDao.class);
		User user = userDao.getUser("1001");
		System.out.println("-----------");
		if (user == null) {
			System.out.println("用户名不正确");
		} else {
			System.out.println("1."+user.getUsername());
			System.out.println("2."+user.getPassword());
			System.out.println("3."+user.getEmail());
			System.out.println("4."+user.getPhone());
		}
	}
}
