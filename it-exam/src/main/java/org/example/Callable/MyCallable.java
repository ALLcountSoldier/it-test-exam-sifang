package org.example.Callable;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Path path = Paths.get("D:\\Desk\\testIO\\a.data");
        Path path2 = Paths.get("D:\\Desk\\testIO\\b.data");
        Path path3 = Paths.get("D:\\Desk\\testIO\\c.data");
        Scanner scanner = null;
        Scanner scanner2 = null;
        Scanner scanner3 = null;
        try {
            scanner = new Scanner(path);
            scanner2 = new Scanner(path2);
            scanner3 = new Scanner(path3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scanner.useDelimiter("\\s+");
        String line = scanner.nextLine();
        String line2 = scanner2.nextLine();
        String line3 = scanner3.nextLine();
        String[] arr = line.split("\\s+");//分割一个或者多个空格
        String[] arr2 = line2.split("\\s+");
        String[] arr3 = line3.split("\\s+");
        List<String> list = new ArrayList<String>();
        for(String s : arr) {
            list.add(s);
        }
        for(String s : arr2) {
            list.add(s);
        }
        for(String s : arr3) {
            list.add(s);
        }
        Collections.sort(list);
        String se = StringEscapeUtils.unescapeJava(list.toString());
        // 除了字母数字下划线之外的字符为非法字符
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        // 指定设置非法字符
        Matcher matcher = pattern.matcher(se);
        StringBuffer buffer = new StringBuffer();
        // 如果找到非法字符
        while (matcher.find()) {
            // 如果里面包含非法字符，那么就把他们消去，并把非法字符前面的字符放到缓冲区
            matcher.appendReplacement(buffer," ");
        }
        // 将剩余的合法部分添加到缓冲区
        matcher.appendTail(buffer);
        String[] split = buffer.toString().trim().split("\\s+");
        HashMap<String,Integer> map = new HashMap<>();
        //遍历集合,获取每一个字符串
        for(String s :split){
            //使用获取到的字符,去Map集合判断key是否存在
            if(map.containsKey(s)){
                //key存在
                Integer value = map.get(s);
                value++;
                map.put(s,value);
            }else{
                //key不存在
                map.put(s,1);
            }
        }
        //获取map的entrySet集合
        Set<Map.Entry<String, Integer>> es = map.entrySet();
        //将entry集合存入list
        List<Map.Entry<String, Integer>> l1 = new ArrayList<>(es);
        Collections.sort(l1, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //通过获取entry的value（Student对象），再获取分数进行比较
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        //输出前10名
        for (int i = 0; i < 10; i++) {
            //String s = StringUtils.substringBefore(String.valueOf(l1.get(i)), "=");
            System.out.println(l1.get(i));
        }
        scanner.close();
        return l1.toString();
    }
}
