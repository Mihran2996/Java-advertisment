import model.Category;
import model.Gender;
import model.Item;
import model.User;
import storage.DataStorage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


public class AdverstimentMAin implements Comands {
    private static Scanner scanner = new Scanner(System.in);
    private static DataStorage dataStorage = new DataStorage();
    private static User currentUser = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean isRun = true;
        while (isRun) {
            createdFiles();
            dataStorage.initData();
            Comands.printMainComands();
            int comand;
            try {
                comand = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                comand = -1;
            }
            switch (comand) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    loginUsre();
                    break;
                case REGISTER:
                    registerUser();
                    break;
                default:
                    System.out.println("Wrong comand!");
            }
        }

    }

    private static void registerUser() {
        System.out.println("Please input user data `name,surname,gender(MALE,FEMALE),age,phoneNumber,passvord");
        try {
            String userDAtaStr = scanner.nextLine();
            String[] userDataArr = userDAtaStr.split(",");
            User userFromStorage = dataStorage.getUser(userDataArr[4]);
            if (userFromStorage == null) {
                User user = new User();
                user.setName(userDataArr[0]);
                user.setSurname(userDataArr[1]);
                user.setGender(Gender.valueOf(userDataArr[2]));
                user.setAge(Integer.parseInt(userDataArr[3]));
                user.setPhoneNumber(userDataArr[4]);
                user.setPassword(userDataArr[5]);
                dataStorage.add(user);
                System.out.println("User was successfully added ");
            } else {
                System.out.println("User already exists!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong Data! " + e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loginUsre() {
        System.out.println("Please input`  phoneNumber,password");
        try {
            String loginStr = scanner.nextLine();
            String[] loginArr = loginStr.split(",");
            User user = dataStorage.getUser(loginArr[0]);
            if (user != null && user.getPassword().equals(loginArr[1])) {
                currentUser = user;
                loginSuccess();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong Data! " + e);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loginSuccess() throws IOException, ClassNotFoundException {
        System.out.println("Welcome " + currentUser.getName() + " (!)");
        boolean isRun = true;
        while (isRun) {
            Comands.printUserComands();
            int comand;
            try {
                comand = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                comand = -1;
            }
            switch (comand) {
                case LOGOUT:
                    isRun = false;
                    break;
                case ADD_NEW_AD:
                    addNewAd();
                    break;
                case PRINT_MY_ADS:
                    dataStorage.printItemBysByUser(currentUser);
                    break;
                case PRINT_ALL_ADS:
                    dataStorage.printItems();
                    break;
                case PRINT_AD_BY_CATEGORY:
                    printByCategory();
                    break;
                case PRINT_ALL_ADS_SORT_BY_TITLE:
                    dataStorage.printItemsOrderByTitle();
                    break;
                case PRINT_ALL_ADS_SORT_BY_DATE:
                    dataStorage.printItemsOrderByDate();
                    break;
                case DELETE_MY_ALL_ADS:
                    dataStorage.deleteItemByUser(currentUser);
                    break;
                case DELETE_AD_BY_ID:
                    deleteById();
                    break;
                default:
                    System.out.println("Wrong comand!");
            }
        }
    }

    private static void addNewAd() throws IOException {
        System.out.println("Please input item data ` title,text,price,category");
        System.out.println("Please choose category from name from list " + Arrays.toString(Category.values()));
        Item itemD = new Item();
        try {
            String itemDataStr = scanner.nextLine();
            String[] itemDataArr = itemDataStr.split(",");
            itemD.setTitle(itemDataArr[0]);
            itemD.setText(itemDataArr[1]);
            itemD.setPrice(Double.parseDouble(itemDataArr[2]));
            itemD.setCategory(Category.valueOf(itemDataArr[3]));
            itemD.setUser(currentUser);
            itemD.setCreateDate(new Date());
            System.out.println("Item ws successfully added");
        } catch (Exception e) {
            System.out.println("Wrong Data!");
        }
        dataStorage.add(itemD);

    }

    private static void printByCategory() {
        System.out.println("Please choose category from name from list " + Arrays.toString(Category.values()));
        try {
            String categoryStr = scanner.nextLine();
            Category category = Category.valueOf(categoryStr);
            dataStorage.printItemByCategory(category);
        } catch (Exception e) {
            System.out.println("Wrong Category!");
        }
    }

    private static void deleteById() {
        System.out.println("Please choose id from list");
        dataStorage.printItemBysByUser(currentUser);
        try {
            long id = Long.parseLong(scanner.nextLine());
            Item itemById = dataStorage.getItemById(id);
            if (itemById != null && itemById.getUser().equals(currentUser)) {
                dataStorage.deleteItemById(id);
            } else {
                System.out.println("Wrong id!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong id!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createdFiles() throws IOException {
        File file = new File("C:\\Users\\MIHRAN\\Homework\\src\\tnayinner\\advertisment\\util\\file.txt");
        File file2 = new File("C:\\Users\\MIHRAN\\Homework\\src\\tnayinner\\advertisment\\util\\file2.txt");

        if (!file.exists()) {
            boolean newFile = file.createNewFile();
        }
        if (!file2.exists()) {
            boolean newFile = file2.createNewFile();
        }

    }


}
