package top.crazycat.proxy.logstore;

import top.crazycat.proxy.entity.InvokeEntity;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
public interface LogStore {
    void record(InvokeEntity entity);
}
