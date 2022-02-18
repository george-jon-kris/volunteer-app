package com.draco.volunteerapp.repositories;

import com.draco.volunteerapp.models.User;

public interface UserRepository extends JpaR<User, Long> {
    User findByUsername(String username);
}

