package onairm.com.devtool.net;



import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

/**
 * Created by apple on 17/7/28.
 */

public class CheckGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    CheckGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        //预处理请求错误
       /* BaseData baseData = gson.fromJson(response, BaseData.class);
        Log.d("TAG","responseCode>>>>>>>>>>>>>>>>"+baseData.getStatusCode());
//        if (baseData.isCodeInvalid()){
//            Log.d("TAG","error>>>>>>>>>>>>>>");
//            ApiException apiException = new ApiException(baseData.getStatusCode(), baseData.getMessage());
//            apiException.isTokenExpried();
//            value.close();
//            throw apiException;
//        }
*/
        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }


}
