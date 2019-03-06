package onairm.com.devtool.net;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by apple on 17/8/29.
 */

public class LongDefaultGson implements JsonSerializer<Long>, JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            if (json.getAsString().equals("") || judgeContainsStr(json.getAsString()) ||json.getAsString().equals("null")) {//定义为long类型,如果后台返回""或者null,则返回0
                return 0l;
            }
        } catch (Exception ignore) {
        }
        try {
            return json.getAsLong();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }

    /**
     * 字符串是否全不为数字
     * @param cardNum
     * @return
     */
    public boolean judgeContainsStr(String cardNum) {
        String regex=".*[^0-9]+.*";
        Matcher m= Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }

}
