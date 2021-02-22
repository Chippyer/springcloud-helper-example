package com.chippy.example.entity;

import com.chippy.example.mapper.UserMapper;
import com.ejoy.tkmapper.annotation.Monitor;
import com.ejoy.tkmapper.annotation.MonitorExecutor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MonitorExecutor(UserMapper.class)
@Monitor
@Data
@Table(name = "tbl_test_user_info")
public class User implements Serializable {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Monitor
    @Column(name = "`name`")
    private String name;

    //    @Monitor(value = true)
    @Column(name = "status")
    private Boolean status;

}
