package cn.itmtx.ddd.ezlink.adapter.job;

import cn.itmtx.ddd.ezlink.component.dl.lock.DistributedLockFactory;
import cn.itmtx.ddd.ezlink.domain.domainservice.VisitStatisticsDomainService;
import cn.itmtx.ddd.ezlink.domain.domainservice.enums.LockKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 定时统计转换记录
 */
@Component
@Slf4j
public class VisitStatisticsJob {

    @Autowired
    private VisitStatisticsDomainService visitStatisticsDomainService;

    @Autowired
    private DistributedLockFactory distributedLockFactory;

    /**
     * 每分钟执行一次
     * {秒数} {分钟} {小时} {日期} {月份} {星期} {年份(可为空)}
     */
    @Scheduled(cron = "0 1 0 * * ?")
    public void processVisitStatistics() {
        RLock lock = distributedLockFactory.getLock(LockKeyEnum.VISITOR_STATS_TASK.getCode());
        try {
            boolean tryLock = lock.tryLock(LockKeyEnum.VISITOR_STATS_TASK.getWaitTime(), LockKeyEnum.VISITOR_STATS_TASK.getReleaseTime(), TimeUnit.SECONDS);
            if (tryLock) {
                OffsetDateTime now = OffsetDateTime.now();
                OffsetDateTime start = now.minusDays(1L).withNano(0).withSecond(0).withMinute(0).withHour(0);
                OffsetDateTime end = start.withNano(0).withSecond(59).withMinute(59).withHour(23);
                visitStatisticsDomainService.processVisitStatisticsDuration(start, end);
            }
        } catch (InterruptedException e) {
            log.error("processVisitStatistics error.", e);
        } finally {
            lock.unlock();
        }

    }

}
