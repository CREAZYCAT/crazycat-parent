package top.crazycat.common.util.http;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/4/15
 * description:远程执行客户端
 */
public interface RemoteClient {

    void execute();

    boolean isSuccess();

    Object getResult();

    byte[] getBytes();

    Throwable getError();

    void fail();

    String getErrorMsg();

}
