package com.example.myanimelistjava.repositories.admin;

import com.example.myanimelistjava.models.Admin;
import com.example.myanimelistjava.models.User;
import com.example.myanimelistjava.repositories.ICRUDRepository;

import java.util.UUID;

public interface IAdminRepository extends ICRUDRepository<UUID, Admin> {
}
