package rocks.byivo.processmanager;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import rocks.byivo.processmanager.model.Role;
import rocks.byivo.processmanager.model.User;
import rocks.byivo.processmanager.repositories.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner{

    @Autowired private UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
	User admin = new User();
	admin.setUsername("admin");
	admin.setPassword("admin");
	admin.setRoles(new HashSet<>(Arrays.asList(Role.ADMIN, Role.HANDLER)));
	admin.setCreatedAt(new Date());
	userRepository.save(admin);
    }

}
