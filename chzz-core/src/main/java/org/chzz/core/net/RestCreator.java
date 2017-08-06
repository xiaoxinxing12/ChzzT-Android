package org.chzz.core.net;

import org.chzz.core.app.Chzz;
import org.chzz.core.app.ConfigType;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午1:50
 */
public class RestCreator {

    public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    public static RestService getRestService() {
        return RestServiceHoloder.REST_SERVICE;
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Chzz.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkhttpHolader.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        private static final class OkhttpHolader {
            private static final int TIME_OUT = 60;
            private static  final  OkHttpClient.Builder BUILDER =new OkHttpClient.Builder();
            private static final ArrayList<Interceptor> INTERCEPTORS = Chzz.getConfiguration(ConfigType.INTERCEPTORS);

            private static OkHttpClient.Builder addInterceptor(){
                if(INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
                    for(Interceptor interceptor:INTERCEPTORS){
                        BUILDER.addInterceptor(interceptor);
                    }
                }
                return BUILDER;
            }

            private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build();
//            private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
//                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
//                    .build();
        }

    }

    private static final class RestServiceHoloder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
