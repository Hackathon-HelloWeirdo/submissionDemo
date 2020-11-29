package com.jiayou.demo.service;

import com.jiayou.demo.entity.User;
import com.jiayou.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("数据不存在"));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUser_name(String user_name) {
        return null;
    }
}
