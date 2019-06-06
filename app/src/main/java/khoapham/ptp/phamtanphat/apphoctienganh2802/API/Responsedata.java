package khoapham.ptp.phamtanphat.apphoctienganh2802.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Responsedata {

    private static final String base_url = "https://server2301.herokuapp.com/";
    public static final APICallback getData(){
        return RetrofitInit.initRetro(base_url).create(APICallback.class);
    }
}
