package com.chippy.example.redisson.liveobject;

import lombok.Data;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;

/**
 * @author: chippy
 * @datetime 2020-12-17 16:09
 */
@REntity
@Data
public class Address {

    @RId
    private Integer id;

    private String addressDescribes;

}
