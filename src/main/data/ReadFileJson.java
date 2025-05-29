package data;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import data.dto.*;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ReadFileJson {
    private static List<AccountsDTO> accountsDTOList;
    private static List<ClientDTO> clientDTOList;
    private static List<DiscountDTO> discountsListDTO;
    private static List<EmployeeDTO> employeeDTOList;
    private static List<PayDTO> payDTOList;
    private static List<MenuDTO> menuDTOList;
    private static List<TransactionHistoryDTO> transactionHistoryDTOList;
    
    private static Object[][] kmData;
    private static Object[][] khachData;
    private static Object[][] employeeData;
    private static Object[][] payData;
    private static Object[][] drinkData;
    private static Object[][] transactionData;
    
    public static Object[][] getKhachData() {
        return khachData;
    }
    
    public static Object[][] getKmData() {
        return kmData;
    }
    
    public static Object[][] getEmployeeData() {
        return employeeData;
    }
    
    public static Object[][] getPayData() {
        return payData;
    }
    
    public static Object[][] getDrinkData() {
        return drinkData;
    }
    
    public static Object[][] getTransactionData() {
        return transactionData;
    }
    
    // Template for all methods using gson
    private static <T> List<T> initializeGson(String path, Type typeOfT, Gson gson) {
        try {
            FileReader fileReader = new FileReader(path);
            List<T> listT = gson.fromJson(fileReader, typeOfT);
            fileReader.close();
            return listT;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Template plug for all methods using gson fileWriter
    private static <T> void initializeOverrideData(String path, List<T> listFormat, Gson gsonWithPrettyPrint) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        gsonWithPrettyPrint.toJson(listFormat, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }
    
    public static List<TransactionHistoryDTO> readFileJSONForTransactionHistory() {
        Gson gson = new Gson();
        String path = Paths.get("src", "main", "data", "transactionhistory.json").toString();
        Type listType = new TypeToken<List<TransactionHistoryDTO>>() {
        }.getType();
        transactionHistoryDTOList = initializeGson(path, listType, gson);
        transactionData = new Object[transactionHistoryDTOList.size()][6];
        for (int i = 0; i < transactionHistoryDTOList.size(); i++) {
            TransactionHistoryDTO transactionHistoryDTO = transactionHistoryDTOList.get(i);
            transactionData[i][0] = i+1;
            transactionData[i][1] = transactionHistoryDTO.getName();
            transactionData[i][2] = transactionHistoryDTO.getPhoneNumber();
            transactionData[i][3] = transactionHistoryDTO.getMoney();
            transactionData[i][4] = transactionHistoryDTO.getMethod();
            transactionData[i][5] = transactionHistoryDTO.getPaymentDate();
        }
        return transactionHistoryDTOList;
    }
    
    public static List<PayDTO> readFileJSONForPay() {
        Gson gson = new Gson();
        String path = Paths.get("src", "main", "data", "listpay.json").toString();
        Type listType = new TypeToken<List<PayDTO>>() {
        }.getType();
        payDTOList = initializeGson(path, listType, gson);
        payData = new Object[payDTOList.size()][5];
        for (int i = 0; i < payDTOList.size(); i++) {
            PayDTO payDTO = payDTOList.get(i);
            payData[i][0] = payDTO.getTenMon();
            payData[i][1] = payDTO.getSoLuong();
            payData[i][2] = payDTO.getGia();
            payData[i][3] = payDTO.getTopping();
        }
        return payDTOList;
    }
    
    // [LoginPage]
    public static List<AccountsDTO> readFileJSONForAccount() {
        try {
            Gson gson = new Gson();
            String path = Paths.get("src", "main", "data", "listaccounts.json").toString();
            Type listType = new TypeToken<List<AccountsDTO>>() {
            }.getType();
            accountsDTOList = initializeGson(path, listType, gson);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return accountsDTOList;
    }
    
    // [DiscountPanel]
    public static List<ClientDTO> readFileJSONForClient() {
        Gson gson = new Gson();
        String path = Paths.get("src", "main", "data", "client.json").toString();
        Type listType = new TypeToken<List<ClientDTO>>() {
        }.getType();
        clientDTOList = initializeGson(path, listType, gson);
        khachData = new Object[clientDTOList.size()][5];
        for (int i = 0; i < clientDTOList.size(); i++) {
            ClientDTO clientDTO = clientDTOList.get(i);
            khachData[i][0] = clientDTO.getHoTen();
            khachData[i][1] = clientDTO.getSoDienThoai();
            khachData[i][2] = clientDTO.getDiemTichLuy();
            khachData[i][3] = clientDTO.getTrangThai();
            khachData[i][4] = clientDTO.isChon();
        }
        return clientDTOList;
    }
    
    public static List<MenuDTO> readFileJSONForMenu() {
        Gson gson = new Gson();
        String path = Paths.get("src", "main", "data", "listmenu.json").toString();
        Type listType = new TypeToken<List<MenuDTO>>() {
        }.getType();
        menuDTOList = initializeGson(path, listType, gson);
        return menuDTOList;
    }
    
    // [EmployeeManagement]
    public static List<EmployeeDTO> readFileJSONForEmployee() {
        Gson gson = new Gson();
        String path = Paths.get("src", "main", "data", "listemployee.json").toString();
        Type listType = new TypeToken<List<EmployeeDTO>>() {
        }.getType();
        employeeDTOList = initializeGson(path, listType, gson);
        employeeData = new Object[employeeDTOList.size()][5];
        for (int i = 0; i < employeeDTOList.size(); i++) {
            EmployeeDTO employeeDTO = employeeDTOList.get(i);
            employeeData[i][0] = employeeDTO.getName();
            employeeData[i][1] = employeeDTO.getId();
            employeeData[i][2] = employeeDTO.getPhoneNumber();
//            employeeData[n][n] = formatEmployee.getIdentifyNumber();
//            employeeData[n][n] = formatEmployee.getAddress();
            employeeData[i][3] = employeeDTO.getBirth();
//            employeeData[n][n] = formatEmployee.getStartingDate();
//            employeeData[n][n] = formatEmployee.getShift();
            employeeData[i][4] = employeeDTO.getSalary();
        }
        return employeeDTOList;
    }
    
    public static void updateFormatDiscountsFromTable(DefaultTableModel kmModel) {
        for (int i = 0; i < discountsListDTO.size(); i++) {
            // Lấy trạng thái đã tick (true/false) từ cột 5
            Object value = kmModel.getValueAt(i, 5);
            boolean isSelected = Boolean.TRUE.equals(value);
            discountsListDTO.get(i).setChon(isSelected);
        }
        Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
        String path = Paths.get("src", "main", "data", "listdiscount.json").toString();
        try {
            initializeOverrideData(path, discountsListDTO, gsonWithPrettyPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // [DiscountPanel] && [PromotionManagement]
    public static List<DiscountDTO> readFileJSONForDiscount() {
        Gson gson = new Gson();
        String path = Paths.get("src", "main", "data", "listdiscount.json").toString();
        Type listType = new TypeToken<List<DiscountDTO>>() {
        }.getType();
        discountsListDTO = initializeGson(path, listType, gson);
        kmData = new Object[discountsListDTO.size()][6];
        for (int i = 0; i < discountsListDTO.size(); i++) {
            DiscountDTO discountDTO = discountsListDTO.get(i);
            kmData[i][0] = discountDTO.getMaKM();
            kmData[i][1] = discountDTO.getTenKM();
            kmData[i][2] = discountDTO.getNoiDung();
            kmData[i][3] = discountDTO.getNgayBatDau();
            kmData[i][4] = discountDTO.getNgayKetThuc();
            kmData[i][5] = discountDTO.isChon();
        }
        return discountsListDTO;
    }
    
    // [PromotionManagement]
    public static void addDiscount(String maKM, String tenKM, String loaiKM, String ngayBatDau, String ngayHetHan) {
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "listdiscount.json").toString();
            Type listType = new TypeToken<List<DiscountDTO>>() {
            }.getType();
            discountsListDTO = initializeGson(path, listType, gsonWithPrettyPrint);
            
            DiscountDTO discountDTO = new DiscountDTO(maKM, tenKM, loaiKM, ngayBatDau, ngayHetHan, false);
            discountsListDTO.add(discountDTO);
            initializeOverrideData(path, discountsListDTO, gsonWithPrettyPrint);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
    
    // [AddEmployeeDialog]
    public static void addEmployee(String name, String id, String phoneNumber, String identifyNumber, String address, String birth, String startingDate, String shift, String salary) {
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "listemployee.json").toString();
            Type listType = new TypeToken<List<EmployeeDTO>>() {
            }.getType();
            employeeDTOList = initializeGson(path, listType, gsonWithPrettyPrint);
            
            EmployeeDTO employeeDTO = new EmployeeDTO(name, id, phoneNumber, identifyNumber, address, birth, startingDate, shift, salary);
            employeeDTOList.add(employeeDTO);
            initializeOverrideData(path, employeeDTOList, gsonWithPrettyPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void deleteEmployee(String verifyId) {
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "listemployee.json").toString();
            Type listType = new TypeToken<List<EmployeeDTO>>() {
            }.getType();
            employeeDTOList = initializeGson(path, listType, gsonWithPrettyPrint);
            
            for (EmployeeDTO employeeDTO : employeeDTOList) {
                if (employeeDTO.getId().equals(verifyId)) {
                    employeeDTOList.remove(employeeDTO);
                    break;
                }
            }
            initializeOverrideData(path, employeeDTOList, gsonWithPrettyPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void addDrink(String name, String price, String type, String sourcePicture) {
        Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
        String path = Paths.get("src", "main", "data", "listmenu.json").toString();
        Type listType = new TypeToken<List<MenuDTO>>() {
        }.getType();
        menuDTOList = initializeGson(path, listType, gsonWithPrettyPrint);
        
        MenuDTO menuDTO = new MenuDTO(type, name, price, sourcePicture);
        menuDTOList.add(menuDTO);
        try {
            initializeOverrideData(path, menuDTOList, gsonWithPrettyPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void editDrink(String nameVerify, String priceVerify, String nameChange, String priceChange, String sourcePictureChange) {
        Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
        String path = Paths.get("src", "main", "data", "listmenu.json").toString();
        Type listType = new TypeToken<List<MenuDTO>>() {
        }.getType();
        menuDTOList = initializeGson(path, listType, gsonWithPrettyPrint);
        
        for (MenuDTO menuDTO : menuDTOList) {
            if (menuDTO.getName().equals(nameVerify) && menuDTO.getPrice().equals(priceVerify)) {
                menuDTO.setName(nameChange);
                menuDTO.setPrice(priceChange);
                menuDTO.setSourcePicture(sourcePictureChange);
            }
        }
        try {
            initializeOverrideData(path, menuDTOList, gsonWithPrettyPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void updateFormatClientFromTable(DefaultTableModel khachModel) {
        for (int i = 0; i < clientDTOList.size(); i++) {
            Object value = khachModel.getValueAt(i, 4);
            boolean isSelected = Boolean.TRUE.equals(value);
            clientDTOList.get(i).setChon(isSelected);
        }
        Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
        String path = Paths.get("src", "main", "data", "client.json").toString();
        try {
            initializeOverrideData(path, clientDTOList, gsonWithPrettyPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Phần thêm thông tin từ OrderBill sang phần thanh toán
    public static void addOrderToPay(String tenMon, int soLuong, String gia, String topping) {
        Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
        String path = Paths.get("src", "main", "data", "listpay.json").toString();
        Type listType = new TypeToken<List<PayDTO>>() {
        }.getType();
        payDTOList = initializeGson(path, listType, gsonWithPrettyPrint);
        
        PayDTO payDTO = new PayDTO(tenMon, soLuong, gia, topping);
        payDTOList.add(payDTO);
        try {
            initializeOverrideData(path, payDTOList, gsonWithPrettyPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // [DiscountPanel]
    public static void addClient(String ten, String sdt) {
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "client.json").toString();
            Type listType = new TypeToken<List<ClientDTO>>() {
            }.getType();
            clientDTOList = initializeGson(path, listType, gsonWithPrettyPrint);
            
            ClientDTO clientDTO = new ClientDTO(ten, sdt, "0", "Normal", false);
            clientDTOList.add(clientDTO);
            initializeOverrideData(path, clientDTOList, gsonWithPrettyPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // [ChangeInforCustomerDialog], [DiscountPanel]
    public static void saveChangedClientInformation(String verifyName, String verifyPhoneNumber, String nameChange, String phoneChange, String scoreChange) {
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "client.json").toString();
            Type listType = new TypeToken<List<ClientDTO>>() {
            }.getType();
            clientDTOList = initializeGson(path, listType, gsonWithPrettyPrint);
            
            for (ClientDTO clientDTO : clientDTOList) {
                if (clientDTO.getHoTen().equals(verifyName) && clientDTO.getSoDienThoai().equals(verifyPhoneNumber)) {
                    clientDTO.setHoTen(nameChange);
                    clientDTO.setSoDienThoai(phoneChange);
                    clientDTO.setDiemTichLuy(scoreChange);
                    break;
                }
            }
            initializeOverrideData(path, clientDTOList, gsonWithPrettyPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // [ChangeInforCustomerDialog]
    public static void deteleClientInformation(String verifyName, String verifyPhoneNumber) {
        try {
            Gson gsonWithPrettyPrint = new GsonBuilder().setPrettyPrinting().create();
            String path = Paths.get("src", "main", "data", "client.json").toString();
            Type listType = new TypeToken<List<ClientDTO>>() {
            }.getType();
            clientDTOList = initializeGson(path, listType, gsonWithPrettyPrint);
            
            for (ClientDTO clientDTO : clientDTOList) {
                if (clientDTO.getHoTen().equals(verifyName) && clientDTO.getSoDienThoai().equals(verifyPhoneNumber)) {
                    clientDTOList.remove(clientDTO);
                    break;
                }
            }
            initializeOverrideData(path, clientDTOList, gsonWithPrettyPrint);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
