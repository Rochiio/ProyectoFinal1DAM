package com.example.myanimelistjava.repositories.admin;

import com.example.myanimelistjava.models.Admin;
import com.example.myanimelistjava.models.User;

import java.util.UUID;

public interface IAdminRepository extends ICRUDAdmin<Admin, UUID> {
}
