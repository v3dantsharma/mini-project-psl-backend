package com.exam;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.model.User;
import com.exam.model.Role;
import com.exam.model.UserRole;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;
import com.exam.service.impl.UserServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class DBConnectivityTest {
	@Autowired
	private UserRepository userrepository;
	
    @Autowired
    private UserService userService;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Test
	void connectivityTest() {
//		User user = this.userrepository.findByUsername("abc");	
//		System.out.println(user.getUsername());
		
		assert (this.userrepository.findByUsername("abc")) != null;
	}
	
	@Test
	void createUserTest() throws Exception {
        User user = new User();

        user.setFirstName("testing");
        user.setLastName("testing");
        user.setUsername("testing");
        user.setPassword(this.bCryptPasswordEncoder.encode("testing"));
        user.setEmail("testing@gmail.com");
        user.setProfile("default.png");

        Role role1 = new Role();
        role1.setRoleId(45L);
        role1.setRoleName("NORMAL");

        Set<UserRole> userRoleSet = new HashSet<>();
        UserRole userRole = new UserRole();

        userRole.setRole(role1);

        userRole.setUser(user);

        userRoleSet.add(userRole);

        User user1 = this.userService.createUser(user, userRoleSet);
        
        assertThat(this.userrepository.findByUsername("testing").getFirstName()).isEqualTo("testing");
	}
	
	@Test
	void deleteUserTest() {
		User user = this.userrepository.findByUsername("testing");
		this.userService.deleteUser(user.getId());
		user = this.userrepository.findByUsername("testing");
		assertThat(user).isNull();
	}
	
}
