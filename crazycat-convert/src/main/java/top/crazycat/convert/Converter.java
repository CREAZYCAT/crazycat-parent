package top.crazycat.convert;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/17
 * description:getset转换接口
 */
public interface Converter<S,T> {
    T convert(S source);
    void convert(S source,T target);
}
