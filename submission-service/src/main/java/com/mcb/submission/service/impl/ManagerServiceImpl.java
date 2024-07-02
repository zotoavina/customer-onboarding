package com.mcb.submission.service.impl;

import com.mcb.submission.persistence.repository.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ManagerServiceImpl implements UserDetailsService {

    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository repository) {
        this.managerRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load manager using username {}", username);
        return managerRepository.findManagerByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("No manager with username " + username)
                );
    }
}
