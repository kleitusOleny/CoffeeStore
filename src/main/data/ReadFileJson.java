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

import java.util.List;

public class ReadFileJson {
    private static List<FormatAccounts> formatAccountsList;
    private static List<FormatClient> formatClientList;
    private static List<FormatDiscount> formatDiscountsList;
    private static Object[][] kmData;
    private static Object[][] khachData ;

    public static Object[][] getKhachData() {
        return khachData;
    }

    public static Object[][] getKmData() {
        return kmData;
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

    // Xử lý phần account login page
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
            exception.printStackTrace();
            System.out.println("Đã có lỗi phát sinh trong quá trình đọc file");
        }
        return formatAccountsList;
    }

    // Xử lý phần DiscountPanel ở dòng 82
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

    // Xử lý phần DiscountPanel ở dòng 145
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

    // Xử lý phần DiscountPanel ở dòng 228
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

    // Đang xử lý dòng thứ 83 của ChangeInforCustomerDialog, và nghi ngờ dòng từ 120 của DiscountPanel
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
