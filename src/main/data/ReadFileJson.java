package data;
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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

    // Xử lý phần account login page
    public static List<FormatAccounts> readFileJSON() {
        try {
            String path = Paths.get("src", "main", "data", "listaccounts.json").toString();
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(path);
            Type listType = new TypeToken<List<FormatAccounts>>(){}.getType();
            formatAccountsList = gson.fromJson(fileReader, listType);
            for (FormatAccounts accounts : formatAccountsList){
                System.out.println("Role: " + accounts.getRole());
                System.out.println("Username: " + accounts.getUsername());
                System.out.println("Password: " + accounts.getPassword());
                System.out.println("-----------------");
            }
            fileReader.close();
        } catch (Exception exception){
            exception.printStackTrace();
            System.out.println("Đã có lỗi phát sinh trong quá trình đọc file");
        }
        return formatAccountsList;
    }

    // Xử lý phần DiscountPanel ở dòng 82
    public static List<FormatClient> readFileJSONForClient() {
        try {
            String path = Paths.get("src", "main", "data", "client.json").toString();
            FileReader fileReader = new FileReader(path);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<FormatClient>>() {}.getType();
            formatClientList = gson.fromJson(fileReader, listType);
            khachData = new Object[formatClientList.size()][5];
            for (int i = 0; i < formatClientList.size(); i++) {
                FormatClient formatClient = formatClientList.get(i);
                khachData[i][0] = formatClient.getHoTen();
                khachData[i][1] = formatClient.getSoDienThoai();
                khachData[i][2] = formatClient.getDiemTichLuy();
                khachData[i][3] = formatClient.getTrangThai();
                khachData[i][4] = formatClient.isChon();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return formatClientList;
    }

    // Xử lý phần DiscountPanel ở dòng 145
    public static List<FormatDiscount> readFileJSONForDiscount(){
        try {
            String path = Paths.get("src", "main", "data", "listdiscount.json").toString();
            FileReader fileReader = new FileReader(path);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<FormatDiscount>>() {}.getType();
            formatDiscountsList = gson.fromJson(fileReader, listType);
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return formatDiscountsList;
    }

    // Xử lý phần DiscountPanel ở dòng 228
    public static void readWithOverwriteJSONForClient(String ten, String sdt){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "client.json").toString();
            FileReader fileReader = new FileReader(path);
            Type listType = new TypeToken<List<FormatClient>>() {}.getType();
            formatClientList = gson.fromJson(fileReader, listType);
            fileReader.close();
            FormatClient formatClient = new FormatClient(ten, sdt, "0", "Bình Thường", false);
            formatClientList.add(formatClient);
            FileWriter fileWriter = new FileWriter(path);
            gson.toJson(formatClientList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
