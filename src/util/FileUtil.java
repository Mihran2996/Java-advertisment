package util;

import model.Item;
import model.User;

import java.io.*;
import java.util.List;
import java.util.Map;

public class FileUtil {
    public static final String USER_PATH = "C:\\Users\\MIHRAN\\Homework\\src\\tnayinner\\advertisment\\util\\file.txt";
    public static final String ITEM_PATH = "C:\\Users\\MIHRAN\\Homework\\src\\tnayinner\\advertisment\\util\\file2.txt";


    public void serializeUserMap(Map<String, User> userMap) throws IOException {
//ստեղ մեր եկած մապ-ը կսերիալիզացնենք ինչ որ ֆայլի մեջ։
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(USER_PATH));
        obj.writeObject(userMap);
        obj.close();

    }

    public Map<String, User> deserializeUserMap() throws IOException, ClassNotFoundException {
        ObjectInputStream obj = new ObjectInputStream(new FileInputStream(USER_PATH));
        Object obj1 = obj.readObject();
        obj.close();
        return (Map<String, User>) obj1;
//ստեղ էլ նույն ֆայլից կվերցնենք օբյեկտը, ու կվերադարնենք։
    }

    public void serializeItemList(List<Item> items) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(ITEM_PATH));
        obj.writeObject(items);
        obj.close();
    }

    public List<Item> deserializeItemList() throws IOException, ClassNotFoundException {
        ObjectInputStream obj = new ObjectInputStream(new FileInputStream(ITEM_PATH));
        Object obj1 = obj.readObject();
        obj.close();
        return (List<Item>) obj1;
    }
}