package top.crazycat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.crazycat.api.DemoService;
import top.crazycat.api.entity.DemoEntity;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/16
 * description:
 */
@Slf4j
@Component("demoService")
public class DemoServiceImpl implements DemoService {
    @Override
    public void request(DemoEntity entity) {
        log.info("request[{}]",entity);
    }
}
