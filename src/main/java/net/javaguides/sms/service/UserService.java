package net.javaguides.sms.service;

import net.javaguides.sms.entity.User;
import net.javaguides.sms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Ваш код для сохранения нового пользователя в источнике данных
        // Например, в базе данных или другом хранилище

        User savedUser = userRepository.save(user); // Пример использования репозитория

        return savedUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void deleteUserById(Long id) {
        // Ваша реализация метода удаления пользователя по идентификатору
    }

    public List<User> findAllUsers() {
        // Ваш код для извлечения списка пользователей из источника данных
        // Например, из базы данных или другого хранилища

        List<User> userList = userRepository.findAll(); // Пример использования репозитория

        return userList;
    }
}