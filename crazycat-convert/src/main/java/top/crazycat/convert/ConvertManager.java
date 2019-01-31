package top.crazycat.convert;

import top.crazycat.convert.processor.ConvertProcessor;
/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/18
 * description:
 */
public interface ConvertManager {

    ConvertProcessor[] processors();

    void setProcessor(ConvertProcessor... processor);

    void register(Class source, Class target,String datePattern);

    void register(Class self);

    <S, T> T convert(S source, Class<T> clazz);

    <S, T> void convert(S source,T target);

    String[] scanPackages();

    void setScanPackages(String... param);

    void init();

    void destroy();

}
