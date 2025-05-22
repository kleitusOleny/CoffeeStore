const fs = require('fs');
const path = require('path');

// Danh sách dữ liệu mẫu
const firstNames = ['Nguyễn', 'Trần', 'Lê', 'Phạm', 'Hoàng', 'Huỳnh', 'Vũ', 'Đặng'];
const middleNames = ['Văn', 'Thị', 'Hồng', 'Minh', 'Quốc', 'Anh', 'Ngọc', ''];
const lastNames = ['Hùng', 'Linh', 'Nam', 'Mai', 'Dương', 'Phúc', 'Tâm', 'Bình'];

// Hàm tạo số ngẫu nhiên
function randomNumber(length) {
    const chars = '0123456789';
    return Array.from({ length }, () => chars[Math.floor(Math.random() * chars.length)]).join('');
}

function getRandomAmount() {
    const amount = Math.floor(Math.random() * 90000) + 10000; // từ 10.000đ đến 100.000đ
    return amount.toLocaleString("vi-VN") + "đ";
}

function getRandomMethod() {
    const methods = ["Tiền mặt", "Thẻ tín dụng", "Chuyển khoản"];
    return methods[Math.floor(Math.random() * methods.length)];
}

function getRandomDate() {
    const today = new Date();
    const randomOffset = Math.floor(Math.random() * 10); // trong vòng 10 ngày
    const randomDate = new Date(today);
    randomDate.setDate(today.getDate() + randomOffset);
    return randomDate.toLocaleDateString("vi-VN");
}

function generateTransaction() {
    const fullName = `${firstNames[Math.floor(Math.random() * firstNames.length)]} ${
        middleNames[Math.floor(Math.random() * middleNames.length)]} ${
        lastNames[Math.floor(Math.random() * lastNames.length)]}`.replace(/\s+/g, ' ').trim();
    const phoneNumber = `0${randomNumber(9)}`;
    const randomAmount = getRandomAmount();
    const randomMethod = getRandomMethod();
    const randomDate = getRandomDate();
    return {
        tenKhachHang: fullName,
        soDienThoai: phoneNumber,
        soTien: randomAmount,
        phuongThucThanhToann: randomMethod,
        ngay: randomDate
    }
}

function toolRandomTransaction(count = 10) {
    const employees = Array.from({ length: count }, () => generateTransaction());
    const filePath = path.join(__dirname, '..', '..', 'main', 'data', 'transactionhistory.json')
    fs.writeFileSync(filePath, JSON.stringify(employees, null, 2), 'utf-8')
    console.log("Đã tạo thông tin ngẫu nhiên thành công");
}

toolRandomTransaction();

