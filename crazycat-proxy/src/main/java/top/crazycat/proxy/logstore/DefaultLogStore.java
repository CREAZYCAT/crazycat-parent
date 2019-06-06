package top.crazycat.proxy.logstore;

import lombok.extern.slf4j.Slf4j;
import top.crazycat.proxy.entity.InvokeEntity;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
@Slf4j
public class DefaultLogStore implements LogStore {
    private static final LogStore instance = new DefaultLogStore();
    private DefaultLogStore() {
    }

    public static LogStore getInstance() {
        return instance;
    }

    @Override
    public void record(InvokeEntity entity) {
        log.info("invoke remote : [{}]", entity.toString());
    }
}
