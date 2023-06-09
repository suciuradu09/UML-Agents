package org.example.interfaces;

import org.example.Admin;
import org.example.Agent;
import org.example.Repository;

import java.util.List;

public interface AdminRepository extends Repository<Long, Admin> {
    Admin findByUsername(String username);
}
