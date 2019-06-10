package top.crazycat.proxy.logstore;

import top.crazycat.proxy.entity.InvokeEntity;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:日志记录执行者
 */
public interface LogStore {
    void record(InvokeEntity entity);
}
