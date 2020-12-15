package com.chippy.example.controller;

import com.chippy.example.common.response.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author: chippy
 * @datetime 2020-12-15 11:18
 */
@RestController
@RequestMapping("/price")
public class PriceController {

    @GetMapping("/byOrderNo")
    public ResponseResult<BigDecimal> byOrderNo(@RequestParam("orderNo") String orderNo) {
        final double price = Math.random() * 100;
        return ResponseResult.success(BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_DOWN));
    }

}
