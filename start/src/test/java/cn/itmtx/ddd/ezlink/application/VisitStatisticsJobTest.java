package cn.itmtx.ddd.ezlink.application;

import cn.itmtx.ddd.ezlink.adapter.job.VisitStatisticsJob;
import cn.itmtx.ddd.ezlink.start.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 集成测试，需要确保环境都正常启动
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class VisitStatisticsJobTest {

    @Autowired
    private VisitStatisticsJob visitStatisticsJob;

    @Test
    public void test() {
        visitStatisticsJob.processVisitStatistics();
    }
}
