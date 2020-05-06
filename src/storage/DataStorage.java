package storage;

import model.Category;
import model.Item;
import model.User;
import util.FileUtil;

import java.io.IOException;
import java.util.*;

public class DataStorage {
    private static long itemId = 1;
    private Map<String, User> userMap = new HashMap<>();
    private List<Item> items = new ArrayList<>();
    private static FileUtil fileUtil = new FileUtil();

    public void add(User user) throws IOException, ClassNotFoundException {
        userMap.put(user.getPhoneNumber(), user);
        fileUtil.serializeUserMap(userMap);
    }
    public void initData() throws IOException, ClassNotFoundException {
        userMap=fileUtil.deserializeUserMap();
        items=fileUtil.deserializeItemList();
    }

    public void add(Item item) throws IOException {
        item.setId(itemId++);
        items.add(item);
        fileUtil.serializeItemList(items);
    }
    public User getUser(String phoneNUmber) throws IOException, ClassNotFoundException {
        userMap = fileUtil.deserializeUserMap();
        return userMap.get(phoneNUmber);

    }

    public Item getItemById(long id) throws IOException, ClassNotFoundException {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void printItemBysByUser(User user) {
        for (Item item : items) {
            if (item.getUser().equals(user)) {
                System.out.println(item);
            }
        }
    }

    public void printItems() {
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public void printItemByCategory(Category category) {
        for (Item item : items) {
            if (item.getCategory() == category) {
                System.out.println(item);
            }
        }
    }

    public void printItemsOrderByTitle(){
        List<Item> orderList = new ArrayList<>(items);
        Collections.sort(orderList);
//        orderList.sort(Item::compareTo);
        for (Item item : orderList) {
            System.out.println(item);
        }
    }

    public void printItemsOrderByDate() {
        List<Item> orderList = new ArrayList<>(items);
        orderList.sort(Comparator.comparing(Item::getCreateDate));
        for (Item item : orderList) {
            System.out.println(item);
        }
    }

    public void deleteItemById(long id) throws IOException, ClassNotFoundException {
        items.remove(getItemById(id));
        fileUtil.serializeItemList(items);
    }

    public void deleteItemByUser(User user) throws IOException {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item next = iterator.next();
            if (next.getUser().equals(user)) {
                iterator.remove();
                fileUtil.serializeItemList(items);
            }
        }
    }
}
