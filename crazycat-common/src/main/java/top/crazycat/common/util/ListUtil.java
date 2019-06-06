package top.crazycat.common.util;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/18
 * description:
 */
public class ListUtil {

    public static boolean isEmpty(Collection c){
        return null == c || c.size() == 0;
    }

    public static boolean isNotEmpty(Collection c){
        return !isEmpty(c);
    }
}
