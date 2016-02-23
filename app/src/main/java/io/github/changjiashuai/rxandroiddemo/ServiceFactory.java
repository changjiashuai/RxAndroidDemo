package io.github.changjiashuai.rxandroiddemo;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 16/2/23 21:44.
 */
public class ServiceFactory {
    public static <T> T createServiceFrom(Class<T> serviceClass, String endpoint){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(serviceClass);
    }
}
