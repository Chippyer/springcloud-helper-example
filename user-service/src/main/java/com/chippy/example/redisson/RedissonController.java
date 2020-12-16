package com.chippy.example.redisson;

import com.chippy.example.common.respnse.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redisson测试接口
 *
 * @author: chippy
 * @datetime 2020/12/16 23:11
 */
@RestController
@RequestMapping("/redisson")
@Slf4j
public class RedissonController {

    private static Map<String, Integer> stockMap = new HashMap<>();

    static {
        stockMap.put("1", 100);
    }

    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/lock1")
    public ResponseResult<String> lock1(@RequestParam("skuId") String skuId,
        @RequestParam("releaseStock") Integer releaseStock) throws InterruptedException {
        Integer stock = stockMap.get(skuId);
        if (stock < releaseStock) {
            return ResponseResult.fail(1, "库存值小于释放库存值");
        }
        Thread.sleep(100);
        stock -= releaseStock;
        stockMap.put(skuId, stock);
        return ResponseResult.success("剩余库存值[" + stock + "]");
    }

    @GetMapping("/lock2")
    public ResponseResult<String> lock2(@RequestParam("skuId") String skuId,
        @RequestParam("releaseStock") Integer releaseStock) {
        final RLock lock = redissonClient.getLock(skuId);
        try {
            final boolean isLockHolder = lock.tryLock(5, 1, TimeUnit.SECONDS);
            if (!isLockHolder) {
                return ResponseResult.fail(3, "操作频繁请稍后再试");
            }
            Integer stock = stockMap.get(skuId);
            if (stock < releaseStock) {
                return ResponseResult.fail(1, "库存值小于释放库存值");
            }
            Thread.sleep(100);
            stock -= releaseStock;
            stockMap.put(skuId, stock);
            return ResponseResult.success(skuId + "剩余库存值[" + stock + "]");
        } catch (InterruptedException e) {
            log.error("线程中断异常-" + e.getMessage(), e);
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
            return ResponseResult.fail(2, "释放库存发生异常");
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    @GetMapping("/remain")
    public ResponseResult<Integer> remain(@RequestParam("skuId") String skuId) {
        return ResponseResult.success(stockMap.get(skuId));
    }

}
