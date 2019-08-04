package com.feng.rpc.register;

import com.feng.rpc.common.URL;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RegistMap implements Serializable {
    public static Map<String, Map<URL, Class>> registMap = new HashMap<String, Map<URL, Class>>();

    public static final String fpath = "D:\\2015\\temp";

    public static void register(String interfaceName, URL host, Class implementClass) {
        Map<URL, Class> map = new HashMap<URL, Class>();
        map.put(host, implementClass);
        registMap.put(interfaceName, map);
        try {
            saveFile(fpath, registMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Object getRadomURLOrClass(String interfaceName, String getType) {
        try {
            registMap = (Map<String, Map<URL, Class>>) readFile(fpath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("美貌与智慧并存的我就是这么自信，每次都有小哥哥，小姐姐来找我!");
        Map<URL, Class> urlMap = registMap.get(interfaceName);
        URL url = null;
        Class implementClass = null;
        Random random = new Random();
        int i = random.nextInt(urlMap.keySet().size());
        int j = 0;
        for (Map.Entry<URL, Class> entry : urlMap.entrySet()
        ) {
            if (i == j) {
                implementClass = entry.getValue();
                url = entry.getKey();
            }
            j++;
        }
        if (getType.equals("class")) {
            return implementClass;
        } else
            return url;

    }

    /**
     * 写入文件
     *
     * @param path
     * @param object
     * @throws IOException
     */
    private static void saveFile(String path, Object object) throws IOException {
        //FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
        FileOutputStream fileOutputStream = new FileOutputStream("/data1/temp.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
    }

    /**
     * 从文件中读取
     *
     * @param path
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static Object readFile(String path) throws IOException, ClassNotFoundException {
        //FileInputStream fileInputStream = new FileInputStream(new File(path));
        FileInputStream fileInputStream = new FileInputStream("/data1/temp.txt");
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        return inputStream.readObject();
    }

}
