//package com.chippy.example.redisson.liveobject;
//
//import cn.hutool.json.JSONUtil;
//import com.chippy.example.common.respnse.ResponseResult;
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RLiveObject;
//import org.redisson.api.RLiveObjectService;
//import org.redisson.api.RedissonClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * 测试LiveLobject
// *
// * @author: chippy
// * @datetime 2020-12-17 15:03
// */
//@RestController
//@RequestMapping("/testLiveObject")
//@Slf4j
//public class LiveObjectController {
//
//    @Resource
//    private RedissonClient redissonClient;
//
//    @GetMapping("/get")
//    public ResponseResult<User> get(@RequestParam("id") Integer id) {
//        final RLiveObjectService liveObjectService = redissonClient.getLiveObjectService();
//        // 通过ID获取分布式实时对象
//        User u1 = liveObjectService.get(User.class, id);
//        if (id == 15) {
//            u1.setName("我是attach创建的对象。我变更了我的属性");
//        }
//        return ResponseResult.success(u1);
//    }
//
//    @GetMapping("/modify")
//    public ResponseResult<User> changeUser(@RequestParam("id") Integer id, String name) {
//        final RLiveObjectService liveObjectService = redissonClient.getLiveObjectService();
//        User user = liveObjectService.get(User.class, id);
//        user.setName(name);
//        return ResponseResult.success(user);
//    }
//
//    @GetMapping("/setForPersist")
//    public ResponseResult<Boolean> setForPersist(User user) {
//        final RLiveObjectService liveObjectService = redissonClient.getLiveObjectService();
//        liveObjectService.persist(user);
//        return ResponseResult.success(Boolean.TRUE);
//    }
//
//    @GetMapping("/setForMerge")
//    public ResponseResult<User> setForMerge(User user) {
//        final RLiveObjectService liveObjectService = redissonClient.getLiveObjectService();
//        liveObjectService.merge(user);
//        return this.get(user.getId());
//    }
//
//    @GetMapping("/isClassRegistered")
//    public ResponseResult<Boolean> isClassRegistered() {
//        final RLiveObjectService liveObjectService = redissonClient.getLiveObjectService();
//        return ResponseResult.success(liveObjectService.isClassRegistered(Address.class));
//    }
//
//    @GetMapping("/isClassRegistered2")
//    public ResponseResult<Boolean> isClassRegistered(Address address) {
//        final RLiveObjectService liveObjectService = redissonClient.getLiveObjectService();
//        final Address merge = liveObjectService.merge(address);
//        return ResponseResult.success(liveObjectService.isClassRegistered(Address.class));
//    }
//
//    @GetMapping("/setForAttach")
//    public ResponseResult<User> setForAttach(User user) {
//        final RLiveObjectService liveObjectService = redissonClient.getLiveObjectService();
//        final User attach = liveObjectService.attach(user);
//        log.debug("attach: " + JSONUtil.toJsonStr(attach));
//        return this.get(user.getId());
//    }
//
//    @GetMapping("/deletion")
//    public ResponseResult<Boolean> deletion(User user) {
//        final RLiveObjectService liveObjectService = redissonClient.getLiveObjectService();
//        final User rUser = liveObjectService.get(User.class, user.getId());
//        final RLiveObject rLiveObject = liveObjectService.asLiveObject(rUser);
//        log.debug("rliveObject: " + JSONUtil.toJsonStr(rLiveObject));
//        liveObjectService.delete(rLiveObject);
//        return ResponseResult.success(Boolean.TRUE);
//    }
//
//}
