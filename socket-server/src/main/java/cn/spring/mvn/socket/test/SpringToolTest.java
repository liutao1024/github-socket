package cn.spring.mvn.socket.test;

import org.junit.Test;

import cn.spring.mvn.socket.base.SocketBase;
import cn.spring.mvn.socket.base.SpringTool;
import cn.spring.mvn.socket.entity.User;
import cn.spring.mvn.socket.entity.service.UserService;

public class SpringToolTest extends SocketBase{
	@Test
	public void print(){
		UserService userServiceImpl = (UserService) SpringTool.getBean("UserService");
		User user = userServiceImpl.getUser("1001");
		System.out.println(user);
	}
	public static void main(String[] args) {
		UserService userServiceImpl = (UserService) SpringTool.getBean("UserService");
		User user = userServiceImpl.getUser("1001");
		System.out.println(user);
	}
}
