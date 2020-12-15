package com.chippy.example.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单信息
 *
 * @author: chippy
 * @datetime 2020-12-15 11:01
 */
@Data
public class OrderInfoResult implements Serializable {

    private String orderNo;
    private String userId;
    private String address;

    // 我们希望请求结束后能够拥有此字段信息， 默认订单服务是不会返回这个值
    private String name;

}
