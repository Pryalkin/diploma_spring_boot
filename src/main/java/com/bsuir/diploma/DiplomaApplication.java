package com.bsuir.diploma;

import com.bsuir.diploma.model.employee.Employee;
import com.bsuir.diploma.model.employee.User;
import com.bsuir.diploma.service.EmployeeService;
import com.bsuir.diploma.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class DiplomaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiplomaApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService, EmployeeService employeeService) {
		return args -> {
			User user = new User(0L, "Admin", "Adminov", "Adminovich", new Date(),
								 "291234567", "admin@gmail.com", "admin", new BCryptPasswordEncoder().encode("admin"),
								 new Date(), new Date(),
								 true,
								 true, true);
			user = userService.register(user);
			Employee employee = new Employee(0L, user, "Administrator", "Super_Administrator", new String[]{"Грязный Луи"}, 0.0);
			employeeService.appoint(employee);
		};
	}

//	@Bean
//	public void createAdmin() throws UserNotFoundException, EmailIsInvalidException, EmailExistException, UsernameIsInvalidException, UsernameExistException {
//		User user = new User(0L, "Admin", "Adminov", "Adminovich", new Date(), "291234567", "admin@gmail.com", "admin", "admin",
//							 true, true, new Date(), new Date(), true);
//		userService.register(user);
//	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
