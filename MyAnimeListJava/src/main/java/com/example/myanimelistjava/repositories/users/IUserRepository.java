package com.example.myanimelistjava.repositories.users;

import com.example.myanimelistjava.models.User;

import java.util.UUID;

public interface IUserRepository extends ICRUDUsers<User, UUID> {
}
