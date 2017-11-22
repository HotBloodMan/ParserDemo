package com.ljt.parserdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {
public static String TAG= MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Gson gson = new Gson();
        Log.d(TAG,TAG+" ----第一部分------ ");
        String s1 = gson.toJson(100);
         Log.d(TAG,TAG+" ----->>>s1= "+s1);
        String s2 = gson.toJson(false);
        Log.d(TAG,TAG+" ----->>>s2= "+s2);
        String s3 = gson.toJson("String");
        Log.d(TAG,TAG+" ----->>>s3= "+s3);

        User laBi = new User("LaBi", 24);
        String s4 = gson.toJson(laBi);
        Log.d(TAG,TAG+" ---生成JSON-->>>s4= "+s4);
        String jsonString = "{\"name\":\"怪盗kidou\",\"age\":24}";
        User user = gson.fromJson(jsonString, User.class);
        Log.d(TAG,TAG+" --解析JSON--->>> user= "+user);

        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        String[] s5= gson.fromJson(jsonArray, String[].class);
        Log.d(TAG,TAG+" --解析JSON字符串数组--->>> s5= "+s5[0]+" "+s5[1]+" "+s5[2]);

        //TypeToken作用是获得泛型的类
        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>(){}.getType());
        Log.d(TAG,TAG+" --解析JSON数组为集合list--->>> stringList= "+stringList.size());

        /*
        * Gson提供了fromJson()和toJson() 两个直接用于解析和生成的方法，前者实现反序列化，
        * 后者实现了序列化。同时每个方法都提供了重载方法
        *
        * */

         Log.d(TAG,TAG+" ---手动的方式就是使用stream包下的JsonReader类来手动实现反序列化-->>> ");
        String json = "{\"name\":\"怪盗kidou\",\"age\":\"24\"}";
        User user2 = new User();
        StringReader stringReader = new StringReader(json);
         Log.d(TAG,TAG+" ----->>>stringReader= "+stringReader);
        JsonReader reader = new JsonReader(stringReader);
        try {
            reader.beginObject();
            while(reader.hasNext()){
                String s = reader.nextName();
                switch (s){
                    case "name":
                        user2.name = reader.nextString();
                        Log.d(TAG,TAG+" ----->>>user.name= "+user2.name);
                        break;
                    case "age":
                        user2.age = reader.nextInt(); //自动转换
                        Log.d(TAG,TAG+" ----->>>user.age= "+user2.age);
                        break;
                    case "email":
                        user2.emailAddress = reader.nextString();
                        Log.d(TAG,TAG+" ----->>>user.emailAddress= "+user2.emailAddress);
                        break;
                }
            }
            reader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
