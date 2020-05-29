package top.crazycat.web.remote;

/**
 * @ClassName Executor
 * @Deacription TODO
 * @Author 20030015
 * @Date 2020/5/29 11:56
 * @Version 1.0
 **/
public interface Executor<P,R> {

    R execute(P param);

}
