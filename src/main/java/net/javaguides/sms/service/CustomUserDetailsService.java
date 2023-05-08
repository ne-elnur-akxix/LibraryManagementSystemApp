package net.javaguides.sms.service;

import net.javaguides.sms.config.CustomUserDetails;
import net.javaguides.sms.entity.Role;
import net.javaguides.sms.entity.User;
import net.javaguides.sms.repository.RoleRepository;
import net.javaguides.sms.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService, UserService{

        private final UserRepository userRepository;
        private final RoleRepository roleRepository;
        private final PasswordEncoder encoder;

        public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
            this.encoder = encoder;
        }



        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return new CustomUserDetails(
                    userRepository.findByUsername(username).orElse(new User()));
        }

        @Override
        public User createUser(User newUser) {
            return createUserWithRole(newUser, "USER");
        }

        private User createUserWithRole(User newUser, String roleName) {
            //TODO: check for already existence username, if the user with username already exists throw Exception
            newUser.setId(null);
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            newUser.getRoles().add(getOrCreateRole(roleName));
            return userRepository.save(newUser);
        }
        @Override
        public User createAdmin(User newAdmin) {
            return createUserWithRole(newAdmin, "ADMIN");
        }

        @Override
        public List<User> findAllUsers() {
            return userRepository.findAll();
        }
        protected Role getOrCreateRole(String roleName) {
            Optional<Role> optionalRole = roleRepository.findById(roleName);
            if (optionalRole.isPresent()) {
                return optionalRole.get();
            }
            Role role = new Role(roleName);
            return roleRepository.save(role);
        }
}
