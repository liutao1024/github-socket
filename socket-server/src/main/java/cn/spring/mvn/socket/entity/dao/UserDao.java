package cn.spring.mvn.socket.entity.dao;

import java.util.List;

import cn.spring.mvn.socket.entity.User;

public interface UserDao {

    public int addUser(User user);

    public int deleteUser(String userid);

    public int updateUser(User user);

    public User getUser(String userid);

    public List<User> getUserList();
}
