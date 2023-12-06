package peaksoft.appplaza_springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import peaksoft.appplaza_springboot.model.User;
import peaksoft.appplaza_springboot.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(" SECURITY UserDetails loadUser");
        User user = userRepository.findByEmail(username).get();
        System.out.println(username);
        if (user == null) {
            throw new UsernameNotFoundException(" User not found by email " + username);
        }
        System.out.println(" END SECURITY UserDetails loadUser");
        return user;
    }
}
