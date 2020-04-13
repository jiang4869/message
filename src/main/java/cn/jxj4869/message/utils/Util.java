package cn.jxj4869.message.utils;

import java.io.File;
import java.util.*;

public class Util {


    public static int getRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min =" + min + ">" + "max=" + max);
        }
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(max - min + 1);
        return min + randomNumber;
    }

    public static String getRandomName(int min, int max) {
        StringBuffer stringBuffer = new StringBuffer();
        int len = getRandomNumber(min, max);
        for (int i = 0; i < len; i++) {
            stringBuffer.append((char)getRandomNumber('a','z'));
        }
        return stringBuffer.toString();
    }

    public static int getRandomAge(int min, int max) {
        return getRandomNumber(min, max);
    }


    public static List generateNameList(int n) {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nameList.add(getRandomName(2, 10));
        }
        return nameList;
    }


    public static String getAvatar(){
        String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/img/avatars";
        projectPath.replace("\\","\\\\");
        File file=new File(projectPath);

        String[] list = file.list();
        int idx = Util.getRandomNumber(0, list.length-1);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(file.isDirectory());
        System.out.println(file.exists());
        String avatars="/img/avatars/"+list[idx];
        System.out.println(avatars);
        System.out.println(projectPath);
        return avatars;
    }


}
