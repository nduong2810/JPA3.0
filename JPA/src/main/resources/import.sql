-- Users mẫu (1=Admin, 2=Manager, 3=User)
INSERT INTO dbo.[User](email,userName,fullName,passWord,roleId,phone,createdDate,avatar) VALUES
('admin@example.com','admin',N'Quản trị','123456',1,'0900000000',SYSUTCDATETIME(),NULL),
('manager@example.com','manager',N'Quản lý','123456',2,'0900000001',SYSUTCDATETIME(),NULL),
('user@example.com','user',N'Người dùng','123456',3,'0900000002',SYSUTCDATETIME(),NULL);

-- Categories mẫu
INSERT INTO dbo.Category(cate_name,icons) VALUES
(N'Đồ uống',NULL),
(N'Bánh',NULL),
(N'Khác',NULL);