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

    ConvertManager setProcessor(ConvertProcessor... processor);

    ConvertManager register(Class source, Class target,String datePattern);

    ConvertManager register(Class self);

    <S, T> T convert(S source, Class<T> clazz);

    <S, T> void convert(S source,T target);

    String[] scanPackages();

    ConvertManager setScanPackages(String... param);

    ConvertManager init();

    void destroy();

}
