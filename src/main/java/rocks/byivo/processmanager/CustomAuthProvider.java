package rocks.byivo.processmanager;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import rocks.byivo.processmanager.model.User;
import rocks.byivo.processmanager.repositories.UserRepository;

@Component
public class CustomAuthProvider implements AuthenticationProvider{

    @Autowired UserRepository userRepository;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        Optional<User> foundUser = userRepository.findUserByUsername(name);
        foundUser.isPresent();
        return new UsernamePasswordAuthenticationToken(
                name, password, Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
	return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
