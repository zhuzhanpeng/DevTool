package onairm.com.devtool.tool;

import java.util.List;

/**
 * 工具类
 */

public class CheckNull {
    public boolean List(List list){
        return null != list && 0!=list.size();
    }
}
