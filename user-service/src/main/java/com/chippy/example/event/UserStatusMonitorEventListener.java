package com.chippy.example.event;

import cn.hutool.json.JSONUtil;
import com.ejoy.tkmapper.support.domain.MonitorOperationLogInfo;
import com.ejoy.tkmapper.support.event.AbstractMonitorExecutorListener;
import com.ejoy.tkmapper.support.event.MonitorEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

/**
 * 用户状态字段监听事件监听
 *
 * @author: chippy
 * @datetime 2021/2/21 0:16
 */
@Component
@Slf4j
public class UserStatusMonitorEventListener extends AbstractMonitorExecutorListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
    public void process(MonitorEvent monitorEvent) {
        final Map<String, MonitorOperationLogInfo> sourceInfo = super.getSourceInfo(monitorEvent);
        log.debug("接受事件数据-" + JSONUtil.toJsonStr(sourceInfo));
    }

}
