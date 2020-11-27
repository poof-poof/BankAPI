package org.bataykin.security.service;

import lombok.RequiredArgsConstructor;
import org.bataykin.repository.UserRepository;
import org.bataykin.security.model.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) {
        return userRepository.findByUsername(username)
                .map(UserDetailsImpl::build)
                .orElseThrow(() -> new UsernameNotFoundException(format("User Not Found with username: %s", username)));
    }
}
