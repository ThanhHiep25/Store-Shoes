package com.example.shoestore.service;

import com.example.shoestore.config.PasswordUtil;
import com.example.shoestore.model.User;
import com.example.shoestore.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User getUserByUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (users.size() == 1) {
            return users.get(0);
        } else {
            // Handle the case where there are 0 or more than 1 result
            return null;
        }
    }

//    public void saveUser(User user) {
//        String hashedPassword = null;
//		try {
//			hashedPassword = PasswordUtil.hashPassword(user.getPassword());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        user.setPassword(hashedPassword);
//        userRepository.save(user);
//    }
    public void saveUser(User user) {
        // Không băm mật khẩu, lưu trữ như văn bản thuần túy
        String plainPassword = user.getPassword();
        user.setPassword(plainPassword);
        userRepository.save(user);
    }

    public void updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setAddress(updatedUser.getAddress());
            userRepository.save(existingUser);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

