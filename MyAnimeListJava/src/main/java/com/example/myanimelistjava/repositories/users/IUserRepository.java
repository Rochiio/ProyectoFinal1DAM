package com.example.myanimelistjava.repositories.users;

import com.example.myanimelistjava.models.User;
import com.example.myanimelistjava.repositories.ICRUDRepository;

import java.util.UUID;

public interface IUserRepository extends ICRUDRepository<UUID, User> {
}
