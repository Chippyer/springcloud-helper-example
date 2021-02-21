package com.chippy.example.service;

import com.chippy.example.entity.User;

/**
 * @author: chippy
 * @datetime 2021-02-19 17:56
 */
public interface IUserService {

    User updateStatus(Long id, Boolean status);

    User updateName(Long id, String name);

}
