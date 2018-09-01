package cn.spring.mvn.socket.entity.service;

import java.util.List;

import cn.spring.mvn.socket.entity.User;

public interface UserService {

    public int addUser(User user);

    public int deleteUser(String userid);

    public int updateUser(User user);

    public User getUser(String userid);

    public List<User> getUserList();
}
