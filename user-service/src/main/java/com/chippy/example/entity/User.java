package com.chippy.example.entity;

import com.chippy.example.mapper.UserMapper;
import com.ejoy.tkmapper.Monitor;
import com.ejoy.tkmapper.MonitorExecutor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MonitorExecutor(executor = UserMapper.class)
@Data
@Table(name = "tbl_test_user_info")
public class User implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`name`")
    private String name;

    @Monitor
    @Column(name = "status")
    private Boolean status;

}
