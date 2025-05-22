const fs = require('fs');
const path = require('path');

const firstNames = ['Nguyen', 'Tran', 'Le', 'Pham', 'Hoang', 'Vu', 'Dang'];
const lastNames = ['Hung', 'Linh', 'Nam', 'Mai', 'Duong', 'Phuc', 'Tam'];

function randomString(length) {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    return Array.from({ length }, () => chars[Math.floor(Math.random() * chars.length)]).join('');
}

function generateUsername(isManager) {
    const firstName = firstNames[Math.floor(Math.random() * firstNames.length)];
    const lastName = lastNames[Math.floor(Math.random() * lastNames.length)];
    const randomNum = Math.floor(Math.random() * 1000);
    const username = `${firstName.toLowerCase()}${lastName.toLowerCase()}${randomNum}`;
    return isManager ? `${username}_MGR` : username;
}

function generatePassword() {
    const length = Math.floor(Math.random() * 5) + 8;
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    return Array.from({ length }, () => chars[Math.floor(Math.random() * chars.length)]).join('');
}

function generateAccount() {
    const isManager = Math.random() < 0.2;
    return {
        role: isManager ? 'Manager' : 'Seller',
        username: generateUsername(isManager),
        password: generatePassword()
    };
}

function toolRandomAccount(count = 20) {
    const accounts = Array.from({ length: count }, () => generateAccount());
    const filePath = path.join(__dirname, '..', '..', 'main', 'data', 'listaccounts.json');
    fs.writeFileSync(filePath, JSON.stringify(accounts, null, 2), 'utf-8');
    console.log('Đã ghi danh sách tài khoản vào file accounts.json');
}

toolRandomAccount();