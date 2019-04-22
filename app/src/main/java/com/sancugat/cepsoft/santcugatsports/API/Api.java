package com.sancugat.cepsoft.santcugatsports.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static final String BASE_URL="http://localhost:56410/";
     //private static final String BASE_URL="http://192.168.8.100:53009";
    //private static final String BASE_URL="http://192.168.8.100:53009";
    //private static final String BASE_URL="http://192.168.43.246:53009";
     // private static final String BASE_URL="http://192.168.43.246:53009/";
     //private static final String BASE_URL="http://localhost:53009";
     //private static final String BASE_URL="http://192.168.8.104:45455/";
    //private static final String BASE_URL="http://192.168.32.2:53009";
    //private static final String BASE_URL="http://10.0.2.2:53009/";
    //207.80.197.185

    private static Retrofit retrofit = null;

    public static Retrofit getApi()
    {
        if(retrofit==null)
        {
            /*HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient httpClient = new OkHttpClient();
            // add your other interceptors …

            // add logging as last interceptor
            httpClient.interceptors().add(logging);  // <-- this is the important line!*/

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
             /*retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(clientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();*/



        }

        return retrofit;
    }
}
