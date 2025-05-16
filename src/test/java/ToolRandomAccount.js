
// Danh sách dữ liệu mẫu
const firstNames = ['Nguyen', 'Tran', 'Le', 'Pham', 'Hoang', 'Vu', 'Dang'];
const lastNames = ['Hung', 'Linh', 'Nam', 'Mai', 'Duong', 'Phuc', 'Tam'];

// Hàm tạo chuỗi ngẫu nhiên
function randomString(length) {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    return Array.from({ length }, () => chars[Math.floor(Math.random() * chars.length)]).join('');
}

// Hàm tạo username
function generateUsername(isManager) {
    const firstName = firstNames[Math.floor(Math.random() * firstNames.length)];
    const lastName = lastNames[Math.floor(Math.random() * lastNames.length)];
    const randomNum = Math.floor(Math.random() * 1000);
    const username = `${firstName.toLowerCase()}${lastName.toLowerCase()}${randomNum}`;
    return isManager ? `${username}_MGR` : username;
}

// Hàm tạo mật khẩu (8-12 ký tự)
function generatePassword() {
    const length = Math.floor(Math.random() * 5) + 8;
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    return Array.from({ length }, () => chars[Math.floor(Math.random() * chars.length)]).join('');
}

// Hàm tạo tài khoản ngẫu nhiên
function generateAccount() {
    const isManager = Math.random() < 0.2; // 20% xác suất là manager
    return {
        role: isManager ? 'Manager' : 'Seller',
        username: generateUsername(isManager),
        password: generatePassword()
    };
}

// Hàm tạo và in danh sách tài khoản
function toolRandomAccount(count = 20) {
    const accounts = Array.from({ length: count }, () => generateAccount());
    console.log('Danh sách tài khoản ngẫu nhiên:');
    accounts.forEach((account, index) => {
        console.log(`Tài khoản ${index + 1}:`);
        console.log(`  Vai trò: ${account.role}`);
        console.log(`  Username: ${account.username}`);
        console.log(`  Password: ${account.password}`);
        console.log('---');
    });
}

// Chạy chương trình với 5 tài khoản
toolRandomAccount();