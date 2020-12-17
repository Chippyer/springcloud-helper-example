package com.chippy.example.redisson.liveobject;

import lombok.Data;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;

import java.io.Serializable;

/**
 * 测试Redisson LiveObject
 *
 * @author: chippy
 * @datetime 2020-12-17 12:44
 */
@REntity
@Data
public class User implements Serializable {

    @RId
    private Integer id;

    private String name;
}
