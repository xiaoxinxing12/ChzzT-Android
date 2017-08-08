package org.chzz.core.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午5:29
 */
public abstract class BaseInterceptor implements Interceptor {


    public LinkedHashMap<String,String> getUrlParameters(Chain chain){
        final HttpUrl url =chain.request().url();

        int size=url.querySize();
        final LinkedHashMap<String,String> params =new LinkedHashMap<>();
        for(int i=0;i<size;i++){
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParameters(Chain chain,String key){
        final Request request=chain.request();
        return request.url().queryParameter(key);
    }

    protected LinkedHashMap<String,String> getBodyparameters(Chain chain){
        final FormBody formBody= (FormBody) chain.request().body();
        final LinkedHashMap<String,String> parames =new LinkedHashMap<>();
        int size=formBody.size();
        for(int i=0;i<size;i++){
            parames.put(formBody.name(i),formBody.value(i));

        }
        return parames;
    }
    protected  String getBodyparameters(Chain chain,String key){
        return getBodyparameters(chain).get(key);
    }
}
