-- =============================================
-- Check and drop database PRJ301 if it already exists
-- =============================================
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'PRJ301')
BEGIN
    -- Close all other connections and set to single-user mode to allow dropping
    ALTER DATABASE [PRJ301] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE [PRJ301];
END
GO

-- Create new database
CREATE DATABASE [PRJ301];
GO

-- Use the newly created database
USE [PRJ301];
GO

-- =============================================
-- Create User table
-- =============================================
CREATE TABLE [user] (
    [userID]    VARCHAR(50)     NOT NULL,
    [fullName]  NVARCHAR(100)   NOT NULL,
    [password]  VARCHAR(255)    NOT NULL,
    [roleID]    VARCHAR(50)     NOT NULL,
    -- 1 = TRUE (Active), 0 = FALSE (Inactive)
    [status]    BIT             NOT NULL DEFAULT 1,
    CONSTRAINT PK_user PRIMARY KEY ([userID])
);
GO

-- Insert sample data for User
INSERT INTO [user] ([userID], [fullName], [password], [roleID], [status]) VALUES
('admin',  N'Administrator', '123456', 'ADMIN', 1),   -- TRUE / Active
('user01', N'Nguyen Van A',  '123456', 'USER',  1),   -- TRUE / Active
('user02', N'Tran Thi B',    '123456', 'USER',  0);   -- FALSE / Inactive
GO

-- =============================================
-- Create Product table (with Image column for Base64)
-- =============================================
CREATE TABLE [Product] (
    [productID]     VARCHAR(50)     NOT NULL,
    [productName]   NVARCHAR(255)   NOT NULL,
    [description]   NVARCHAR(MAX)   NULL,
    [price]         DECIMAL(18, 2)  NOT NULL,
    [quantity]      INT             NOT NULL DEFAULT 0,
    [image]         NVARCHAR(MAX)   NULL,  -- Stores Base64 image string
    -- 1 = TRUE / On Sale, 0 = FALSE / Discontinued
    [status]        BIT             NOT NULL DEFAULT 1,
    [createdAt]     DATETIME        NOT NULL DEFAULT GETDATE(),
    CONSTRAINT PK_Product PRIMARY KEY ([productID])
);
GO

-- Insert sample data for Product
INSERT INTO [Product] ([productID], [productName], [description], [price], [quantity], [image], [status]) VALUES
('P001', N'Laptop Dell XPS 15', N'High performance laptop for work and design', 35000000.00, 10, NULL, 1), -- TRUE / On Sale
('P002', N'iPhone 15 Pro Max', N'Apple smartphone Natural Titanium', 32000000.00, 15, NULL, 1), -- TRUE / On Sale
('P003', N'Old Tablet Model', N'Discontinued product', 5000000.00, 0, NULL, 0)  -- FALSE / Discontinued
GO