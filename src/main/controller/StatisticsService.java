package controller;

import model.payments.Transaction;
import model.payments.TransactionLog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

public class StatisticsService {
    
    
    public enum TimeUnit {
        WEEK, MONTH, YEAR
    }
    
    public Map<String, Double> getRevenueGroupedBy(TimeUnit unit) {
        Map<String, Double> result = new TreeMap<>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter outputFormatter = switch (unit) {
            case WEEK -> DateTimeFormatter.ofPattern("YYYY-'W'ww");
            case MONTH -> DateTimeFormatter.ofPattern("yyyy-MM");
            case YEAR -> DateTimeFormatter.ofPattern("yyyy");
        };
        
        for (Transaction t : TransactionLog.getTransactions()) {
            LocalDate date = LocalDate.from(t.getDateTime()); // đã là LocalDate
            String key = date.format(outputFormatter); // tạo key theo tuần / tháng / năm
            
            result.put(key, result.getOrDefault(key, 0.0) + t.getAmount());
        }
        
        return result;
    }
    
    //        public Map<String, Double> calculateRevenueBy(String timeUnit) {
//        Map<String, Double> revenueMap = new LinkedHashMap<>();
//        List<Transaction> transactions = TransactionLog.getTransactions();
//
//        for (Transaction t : transactions) {
//            LocalDate date = LocalDate.from(t.getDateTime());  // Đã là LocalDate theo bạn nói
//
//            String key;
//            switch (timeUnit.toLowerCase()) {
//                case "week":
//                    WeekFields weekFields = WeekFields.of(Locale.getDefault());
//                    int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
//                    int year = date.getYear();
//                    key = "Tuần " + weekNumber + " - " + year;
//                    break;
//                case "month":
//                    key = String.format("%02d/%d", date.getMonthValue(), date.getYear());
//                    break;
//                case "year":
//                    key = String.valueOf(date.getYear());
//                    break;
//                default:
//                    throw new IllegalArgumentException("Loại thời gian không hợp lệ: " + timeUnit);
//            }
//
//            revenueMap.put(key, revenueMap.getOrDefault(key, 0.0) + t.getAmount());
//        }
//
//        return revenueMap;
//    }
    public Map<String, Double> calculateRevenueBy(String timeUnit) {
        Map<String, Double> revenueMap = new LinkedHashMap<>();
        List<Transaction> transactions = TransactionLog.getTransactions();
        
        for (Transaction t : transactions) {
            LocalDate date = LocalDate.from(t.getDateTime());
            String key;
            
            switch (timeUnit.toUpperCase()) {
                case "WEEK":
                    WeekFields weekFields = WeekFields.of(Locale.getDefault());
                    int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
                    int year = date.get(weekFields.weekBasedYear()); // dùng week-based year để tránh lỗi đầu/năm
                    key = String.format("Tuần %02d - %d", weekNumber, year);
                    break;
                
                case "MONTH":
                    key = String.format("Tháng %02d - %d", date.getMonthValue(), date.getYear());
                    break;
                
                case "YEAR":
                    key = String.format("Năm %d", date.getYear());
                    break;
                
                default:
                    throw new IllegalArgumentException("Loại thời gian không hợp lệ: " + timeUnit);
            }
            
            revenueMap.put(key, revenueMap.getOrDefault(key, 0.0) + t.getAmount());
        }
        
        return revenueMap;
    }
    
}