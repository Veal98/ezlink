package cn.itmtx.ddd.ezlink.application.job;

import cn.itmtx.ddd.ezlink.component.dl.lock.DistributedLockFactory;
import cn.itmtx.ddd.ezlink.domain.domainservice.VisitStatisticsDomain;
import cn.itmtx.ddd.ezlink.domain.enums.LockKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

/**
 * 定时统计转换记录
 */
@Component
@Slf4j
public class VisitStatisticsJob {

    @Autowired
    private VisitStatisticsDomain visitStatisticsDomain;

    @Autowired
    private DistributedLockFactory distributedLockFactory;

    /**
     * 每天 8 点执行一次，统计昨天的转换记录
     * {秒数} {分钟} {小时} {日期} {月份} {星期} {年份(可为空)}
     */
    @Scheduled(cron = "0 0 8 * * ?")
    public void processVisitStatistics() {
        RLock lock = distributedLockFactory.getLock(LockKeyEnum.VISITOR_STATS_TASK.getCode());
        try {
            boolean tryLock = lock.tryLock(LockKeyEnum.VISITOR_STATS_TASK.getWaitTime(), LockKeyEnum.VISITOR_STATS_TASK.getReleaseTime(), TimeUnit.SECONDS);
            if (tryLock) {
                OffsetDateTime now = OffsetDateTime.now();
                OffsetDateTime start = now.minusDays(1L).withNano(0).withSecond(0).withMinute(0).withHour(0);
                OffsetDateTime end = start.withNano(0).withSecond(59).withMinute(59).withHour(23);
                visitStatisticsDomain.processVisitStatisticsDuration(start, end);
            }
        } catch (InterruptedException e) {
            log.error("processVisitStatistics error.", e);
        } finally {
            lock.unlock();
        }

    }

}
