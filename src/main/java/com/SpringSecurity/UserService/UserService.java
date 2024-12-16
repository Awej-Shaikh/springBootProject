package com.SpringSecurity.UserService;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.SpringSecurity.UserEntity.UserEntity;
import com.SpringSecurity.UserRepository.UserRepo;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the repository
        Optional<UserEntity>userentity = userRepo.findByUsername(username);

        if (userentity.isPresent())
        {
        	UserEntity user = userentity.get();

        	 return User.builder()
                     .username(user.getUsername())
                     .password(user.getPassword())
                     .roles(user.getRole())
                     .build();
         }
        else
        	{
        	throw new UsernameNotFoundException("User not found with username: " + username);
        	}
        }       
		/*
		 * public UserEntity saveData(UserEntity userEntity) { return
		 * userRepo.save(userEntity); }
		 */
}
