package onairm.com.devtool.tool;

import android.text.TextUtils;

import java.util.List;

/**
 * 工具类
 */

public class CheckNull {
    public boolean isNotListNull(List list) {
        return null != list && 0 != list.size();
    }

    public boolean isNotListIndexOutOfBounds(int position, List list) {
        if (isNotListNull(list)) {
            return position >= 0 && position < list.size();
        }

        return false;
    }

    public String stringCheck(String raw) {
        if (!TextUtils.isEmpty(raw)) {
            return raw;
        }
        return "";
    }
}
