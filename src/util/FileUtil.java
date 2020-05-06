package util;

import model.Item;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {
    public static File file = new File("C:\\Users\\MIHRAN\\Homework\\src\\tnayinner\\advertisment\\util\\file.txt");
    public static File file2 = new File("C:\\Users\\MIHRAN\\Homework\\src\\tnayinner\\advertisment\\util\\file2.txt");

    public void serializeUserMap(Map<String, User> userMap) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));
        obj.writeObject(userMap);
        obj.close();

    }

    public Map<String, User> deserializeUserMap() throws IOException, ClassNotFoundException {
        if (file.length() != 0) {
            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(file));
            Object obj1 = obj.readObject();
            obj.close();
            return (Map<String, User>) obj1;
        }
        Map<String, User> userMap = new HashMap<>();
        return userMap;
    }

    public void serializeItemList(List<Item> items) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file2));
        obj.writeObject(items);
        obj.close();
    }

    public List<Item> deserializeItemList() throws IOException, ClassNotFoundException {
        if (file2.length() != 0) {

            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(file2));
            Object obj1 = obj.readObject();
            obj.close();
            return (List<Item>) obj1;
        }
        List<Item> iems = new ArrayList<>();
        return iems;
    }
}