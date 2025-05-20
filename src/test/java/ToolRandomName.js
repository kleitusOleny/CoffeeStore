const fs = require('fs');
const path = require('path');

// Danh sách dữ liệu mẫu
const firstNames = ['Nguyễn', 'Trần', 'Lê', 'Phạm', 'Hoàng', 'Huỳnh', 'Vũ', 'Đặng'];
const middleNames = ['Văn', 'Thị', 'Hồng', 'Minh', 'Quốc', 'Anh', 'Ngọc', ''];
const lastNames = ['Hùng', 'Linh', 'Nam', 'Mai', 'Dương', 'Phúc', 'Tâm', 'Bình'];
const cities = ['Hà Nội', 'TP.HCM', 'Đà Nẵng', 'Hải Phòng', 'Cần Thơ', 'Nha Trang', 'Huế'];

// Hàm tạo chuỗi ngẫu nhiên
function randomString(length) {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    return Array.from({ length }, () => chars[Math.floor(Math.random() * chars.length)]).join('');
}

// Hàm tạo số ngẫu nhiên
function randomNumber(length) {
    const chars = '0123456789';
    return Array.from({ length }, () => chars[Math.floor(Math.random() * chars.length)]).join('');
}

// Hàm tạo ngày ngẫu nhiên
function randomDate(startYear, endYear) {
    const year = Math.floor(Math.random() * (endYear - startYear + 1)) + startYear;
    const month = String(Math.floor(Math.random() * 12) + 1).padStart(2, '0');
    const day = String(Math.floor(Math.random() * 28) + 1).padStart(2, '0'); // Giới hạn 28 ngày để tránh lỗi
    return `${day}/${month}/${year}`;
}

// Hàm tạo thông tin nhân viên ngẫu nhiên
function generateEmployee() {
    const fullName = `${firstNames[Math.floor(Math.random() * firstNames.length)]} ${
        middleNames[Math.floor(Math.random() * middleNames.length)]} ${
        lastNames[Math.floor(Math.random() * lastNames.length)]}`.replace(/\s+/g, ' ').trim();
    const employeeId = `NV${randomString(6)}`;
    const phoneNumber = `0${randomNumber(9)}`;
    const citizenId = randomNumber(12);
    const address = `${Math.floor(Math.random() * 1000)} Đường ${randomString(5)}, ${
        cities[Math.floor(Math.random() * cities.length)]}`;
    const birthDate = randomDate(1970, 2005);
    const startDate = randomDate(2015, 2025);

    return {
        tenNhanVien: fullName,
        maNhanVien: employeeId,
        soDienThoai: phoneNumber,
        soCCCD: citizenId,
        diaChi: address,
        ngaySinh: birthDate,
        ngayVaoLam: startDate
    };
}

// Hàm chạy và in kết quả
function toolRandomName(count = 10) {
    const employees = Array.from({ length: count }, () => generateEmployee());
    const filePath = path.join(__dirname, '..', '..', 'main', 'model', 'data', 'listname.json')
    fs.writeFileSync(filePath, JSON.stringify(employees, null, 2), 'utf-8')
    console.log("Đã tạo thông tin ngẫu nhiên thành công");
}

// Chạy chương trình
toolRandomName();