package cn.spring.mvn.socket.entity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.spring.mvn.socket.entity.User;
import cn.spring.mvn.socket.entity.dao.UserDao;
import cn.spring.mvn.socket.entity.service.UserService;


@Service("UserService")
public class UserServiceImpl implements UserService{
    
   @Autowired 
   private UserDao userDao;
    
   public int addUser(User user) {
       int result=userDao.addUser(user);
       return result;
   } 

   public int deleteUser(String userid) {
       int result=userDao.deleteUser(userid);
       return result;
   } 

   public int updateUser(User user) {
       int result=userDao.updateUser(user);
       return result;
   } 

   public User getUser(String userid) {
       User user=userDao.getUser(userid); 
       return user;
   } 

   public List<User> getUserList() {
       List<User> list=userDao.getUserList(); 
       return list;
   } 

}
