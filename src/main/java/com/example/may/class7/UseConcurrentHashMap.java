package com.example.may.class7;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: Bruce_T
 * @date: 2022/05/27   21:22
 * @version: 1.0
 * @modified:
 */
public class UseConcurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap<KeyVo,String> map = new ConcurrentHashMap<>();
        KeyVo keyVo = new KeyVo(1,"A");
        System.out.println("put不存在的值------");
        System.out.println(map.put(keyVo,"AA"));
        System.out.println(map.get(keyVo));

        System.out.println("put已存在的key-------------");
        System.out.println(map.put(keyVo,"BB"));
        System.out.println(map.get(keyVo));

        System.out.println("putIfAbsent已存在的key-------------");
        System.out.println(map.putIfAbsent(keyVo,"CC"));
        System.out.println(map.get(keyVo));

        System.out.println("putIfAbsent不存在的key-------------");
        KeyVo keyVo2 = new KeyVo(2,"B");
        System.out.println(map.putIfAbsent( keyVo2,"CC"));
        System.out.println(map.get(keyVo2));

        ConcurrentHashMap<KeyVo,String> map2
                = new ConcurrentHashMap<KeyVo,String>(2,
                0.75f,16);

//putIfAbsent类似的业务流程:
//        synchronized (map){
//            if(map.get(key)==null){
//                map.put(key.value)
//            }else{
//                return map.get(key);
//            }
//        }

    }
}
