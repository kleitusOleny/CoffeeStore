package model.data;
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ReadFileJson {

    public static List<FormatAccounts> readFileJSON() {
        List<FormatAccounts> formatAccountsList = new ArrayList<>();
        try {
            String path = Paths.get("src", "main", "model", "data", "listaccounts.json").toString();
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
}
