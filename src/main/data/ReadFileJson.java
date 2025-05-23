package data;
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import data.dto.FormatAccounts;
import data.dto.FormatClient;
import data.dto.FormatDiscount;
import data.dto.FormatEmployee;

import java.util.List;

public class ReadFileJson {
    private static List<FormatAccounts> formatAccountsList;
    private static List<FormatClient> formatClientList;
    private static List<FormatDiscount> formatDiscountsList;
    private static List<FormatEmployee> formatEmployeeList;

    private static Object[][] kmData;
    private static Object[][] khachData ;
    private static Object[][] employeeData;

    public static Object[][] getKhachData() {
        return khachData;
    }

    public static Object[][] getKmData() {
        return kmData;
    }

    public static Object[][] getEmployeeData() {
        return employeeData;
    }

    // Template for all methods using gson
    public static <T> List<T> initializeGson(String path, Type typeOfT, Gson gson){
        try {
            FileReader fileReader = new FileReader(path);
            List<T> listT = gson.fromJson(fileReader, typeOfT);
            fileReader.close();
            return listT;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // [LoginPage]
    public static List<FormatAccounts> readFileJSONForAccount() {
        try {
            Gson gson = new Gson();
            String path = Paths.get("src", "main", "data", "listaccounts.json").toString();
            Type listType = new TypeToken<List<FormatAccounts>>(){}.getType();
            formatAccountsList = initializeGson(path, listType, gson);
            for (FormatAccounts formatAccounts : formatAccountsList){
                System.out.println("Role: " + formatAccounts.getRole());
                System.out.println("Username: " + formatAccounts.getUsername());
                System.out.println("Password: " + formatAccounts.getPassword());
                System.out.println("-------------\n");
            }
        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return formatAccountsList;
    }

    // [DiscountPanel]
    public static List<FormatClient> readFileJSONForClient() {
        Gson gson = new Gson();
        String path = Paths.get("src", "main", "data", "client.json").toString();
        Type listType = new TypeToken<List<FormatClient>>() {}.getType();
        formatClientList = initializeGson(path, listType, gson);
        khachData = new Object[formatClientList.size()][5];
        for (int i = 0; i < formatClientList.size(); i++) {
            FormatClient formatClient = formatClientList.get(i);
            khachData[i][0] = formatClient.getHoTen();
            khachData[i][1] = formatClient.getSoDienThoai();
            khachData[i][2] = formatClient.getDiemTichLuy();
            khachData[i][3] = formatClient.getTrangThai();
            khachData[i][4] = formatClient.isChon();
        }
        return formatClientList;
    }

    // [EmployeeManagement]
    public static List<FormatEmployee> readFileJSONForEmployee() {
        Gson gson = new Gson();
        String path = Paths.get("src", "main", "data", "listemployee.json").toString();
        Type listType = new TypeToken<List<FormatEmployee>>() {}.getType();
        formatEmployeeList = initializeGson(path, listType, gson);
        employeeData = new Object[formatEmployeeList.size()][5];
        for (int i = 0; i < formatEmployeeList.size(); i++) {
            FormatEmployee formatEmployee = formatEmployeeList.get(i);
            employeeData[i][0] = formatEmployee.getName();
            employeeData[i][1] = formatEmployee.getId();
            employeeData[i][2] = formatEmployee.getPhoneNumber();
//            employeeData[n][n] = formatEmployee.getIdentifyNumber();
//            employeeData[n][n] = formatEmployee.getAddress();
            employeeData[i][3] = formatEmployee.getBirth();
//            employeeData[n][n] = formatEmployee.getStartingDate();
//            employeeData[n][n] = formatEmployee.getShift();
            employeeData[i][4] = formatEmployee.getSalary();
        }
        return formatEmployeeList;
    }

    // [DiscountPanel]
    public static List<FormatDiscount> readFileJSONForDiscount(){
        Gson gson = new Gson();
        String path = Paths.get("src", "main", "data", "listdiscount.json").toString();
        Type listType = new TypeToken<List<FormatDiscount>>() {}.getType();
        formatDiscountsList = initializeGson(path, listType, gson);
        kmData = new Object[formatDiscountsList.size()][6];
        for (int i = 0; i < formatDiscountsList.size(); i++) {
            FormatDiscount formatDiscount = formatDiscountsList.get(i);
            kmData[i][0] = formatDiscount.getMaKM();
            kmData[i][1] = formatDiscount.getTenKM();
            kmData[i][2] = formatDiscount.getNoiDung();
            kmData[i][3] = formatDiscount.getNgayBatDau();
            kmData[i][4] = formatDiscount.getNgayKetThuc();
            kmData[i][5] = formatDiscount.isChon();
        }
        return formatDiscountsList;
    }

    // [DiscountPanel]
    public static void addClient(String ten, String sdt){
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "client.json").toString();
            Type listType = new TypeToken<List<FormatClient>>() {}.getType();
            formatClientList = initializeGson(path, listType, gsonWithPrettyPrint);

            FormatClient formatClient = new FormatClient(ten, sdt, "0", "Bình Thường", false);
            formatClientList.add(formatClient);
            FileWriter fileWriter = new FileWriter(path);
            gsonWithPrettyPrint.toJson(formatClientList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // [AddEmployeeDialog]
    public static void addEmployee(String name, String id, String phoneNumber, String identifyNumber, String address, String birth, String startingDate, String shift, String salary){
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "listemployee.json").toString();
            Type listType = new TypeToken<List<FormatEmployee>>() {}.getType();
            formatEmployeeList = initializeGson(path, listType, gsonWithPrettyPrint);

            FormatEmployee formatEmployee = new FormatEmployee(name, id, phoneNumber, identifyNumber, address, birth, startingDate, shift, salary);
            formatEmployeeList.add(formatEmployee);
            FileWriter fileWriter = new FileWriter(path);
            gsonWithPrettyPrint.toJson(formatEmployeeList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteEmployee(String verifyId){
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "listemployee.json").toString();
            Type listType = new TypeToken<List<FormatEmployee>>() {}.getType();
            formatEmployeeList = initializeGson(path, listType, gsonWithPrettyPrint);

            for (FormatEmployee formatEmployee : formatEmployeeList){
                if (formatEmployee.getId().equals(verifyId)){
                    formatEmployeeList.remove(formatEmployee);
                    break;
                }
            }
            FileWriter fileWriter = new FileWriter(path);
            gsonWithPrettyPrint.toJson(formatEmployeeList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    // [ChangeInforCustomerDialog], [DiscountPanel]
    public static void saveChangedClientInformation(String verifyName, String verifyPhoneNumber, String nameChange, String phoneChange, String scoreChange){
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "client.json").toString();
            Type listType = new TypeToken<List<FormatClient>>() {}.getType();
            formatClientList = initializeGson(path, listType, gsonWithPrettyPrint);

            for (FormatClient formatClient : formatClientList){
                if (formatClient.getHoTen().equals(verifyName) && formatClient.getSoDienThoai().equals(verifyPhoneNumber)) {
                    formatClient.setHoTen(nameChange);
                    formatClient.setSoDienThoai(phoneChange);
                    formatClient.setDiemTichLuy(scoreChange);
                    break;
                }
            }
            FileWriter fileWriter = new FileWriter(path);
            gsonWithPrettyPrint.toJson(formatClientList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    // [ChangeInforCustomerDialog]
    public static void deteleClientInformation(String verifyName, String verifyPhoneNumber){
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "client.json").toString();
            Type listType = new TypeToken<List<FormatClient>>() {}.getType();
            formatClientList = initializeGson(path, listType, gsonWithPrettyPrint);

            for (FormatClient formatClient : formatClientList){
                if (formatClient.getHoTen().equals(verifyName) && formatClient.getSoDienThoai().equals(verifyPhoneNumber)){
                    formatClientList.remove(formatClient);
                    break;
                }
            }
            FileWriter fileWriter = new FileWriter(path);
            gsonWithPrettyPrint.toJson(formatClientList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
