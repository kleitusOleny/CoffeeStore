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
    public Map<String, Double> calculateRevenueBy(String timeUnit) {
        Map<String, Double> revenueMap = new LinkedHashMap<>();
        List<Transaction> transactions = TransactionLog.getTransactions();

        for (Transaction t : transactions) {
            LocalDate date = LocalDate.from(t.getDateTime());  // Đã là LocalDate theo bạn nói

            String key;
            switch (timeUnit.toLowerCase()) {
                case "week":
                    WeekFields weekFields = WeekFields.of(Locale.getDefault());
                    int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
                    int year = date.getYear();
                    key = "Tuần " + weekNumber + " - " + year;
                    break;
                case "month":
                    key = String.format("%02d/%d", date.getMonthValue(), date.getYear());
                    break;
                case "year":
                    key = String.valueOf(date.getYear());
                    break;
                default:
                    throw new IllegalArgumentException("Loại thời gian không hợp lệ: " + timeUnit);
            }

            revenueMap.put(key, revenueMap.getOrDefault(key, 0.0) + t.getAmount());
        }

        return revenueMap;
    }
//public Map<String, Double> calculateRevenueBy(String timeUnit) {
//    Map<String, Double> data = new LinkedHashMap<>();
//
//    switch (timeUnit.toUpperCase()) {
//        case "WEEK":
//            data.put("Tuần 1", 15000000.0);
//            data.put("Tuần 2", 18000000.0);
//            data.put("Tuần 3", 12000000.0);
//            data.put("Tuần 4", 20000000.0);
//            break;
//
//        case "MONTH":
//            data.put("Tháng 1", 60000000.0);
//            data.put("Tháng 2", 72000000.0);
//            data.put("Tháng 3", 54000000.0);
//            data.put("Tháng 4", 80000000.0);
//            break;
//
//        case "YEAR":
//            data.put("Năm 2021", 720000000.0);
//            data.put("Năm 2022", 850000000.0);
//            data.put("Năm 2023", 910000000.0);
//            data.put("Năm 2024", 1000000000.0);
//            break;
//
//        default:
//            // Nếu không đúng, trả về dữ liệu rỗng
//            break;
//    }
//
//    return data;
//}
}
