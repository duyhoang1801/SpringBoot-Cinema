drop database if exists VTI_Cinema;
create database VTI_Cinema;
use VTI_Cinema;
-- Tạo bảng quốc gia
CREATE TABLE quoc_gia (
    id_quoc_gia INT PRIMARY KEY auto_increment,
    ten_quoc_gia VARCHAR(255)
);

-- Tạo bảng thể loại
CREATE TABLE the_loai (
    id_the_loai INT PRIMARY KEY auto_increment,
    ten_the_loai VARCHAR(255)
);

-- Tạo bảng phim
CREATE TABLE film (
    id_film INT PRIMARY KEY auto_increment,
    id_quoc_gia INT,
    id_the_loai INT,
    ten_phim VARCHAR(255),
    noi_dung TEXT,
    dien_vien TEXT,
    dao_dien VARCHAR(255),
    khoi_chieu DATETIME,
    thoi_luong INT,
    gioi_han_do_tuoi INT,
    anh_minh_hoa VARCHAR(255),
    FOREIGN KEY (id_quoc_gia) REFERENCES quoc_gia(id_quoc_gia),
    FOREIGN KEY (id_the_loai) REFERENCES the_loai(id_the_loai)
);
-- Tạo bảng account
CREATE TABLE account (
    id_account INT PRIMARY KEY auto_increment,
    role enum ('ADMIN', 'USER'),
    ho_ten VARCHAR(255),
    so_dien_thoai VARCHAR(20),
    email VARCHAR(255),
    ngay_sinh DATE,
    gioi_tinh enum ('MALE', 'FEMALE'),
    avatar VARCHAR(255),
    mat_khau VARCHAR(255)
);

-- Tạo bảng đánh giá
CREATE TABLE danh_gia (
    id_danh_gia INT PRIMARY KEY auto_increment,
    id_user INT,
    id_film INT,
    noi_dung TEXT,
    FOREIGN KEY (id_user) REFERENCES account(id_account),
    FOREIGN KEY (id_film) REFERENCES film(id_film)
);

-- Tạo bảng khuyến mãi
CREATE TABLE khuyen_mai (
    id_khuyen_mai INT PRIMARY KEY auto_increment,
    code VARCHAR(20),
    giam_gia DECIMAL(5, 2),
    ngay_het_han DATE,
    anh_minh_hoa VARCHAR(255),
    noi_dung VARCHAR(255)
);

-- Tạo bảng vé
CREATE TABLE ve (
    id_ve INT PRIMARY KEY auto_increment,
    id_khuyen_mai INT,
    id_account INT,
    gia DECIMAL(10, 2),
    FOREIGN KEY (id_khuyen_mai) REFERENCES khuyen_mai(id_khuyen_mai),
    FOREIGN KEY (id_account) REFERENCES account(id_account)
);

-- Tạo bảng rạp
CREATE TABLE rap (
    id_rap INT PRIMARY KEY auto_increment,
    ten_rap VARCHAR(255)
);

-- Tạo bảng phòng chiếu
CREATE TABLE phong_chieu (
    id_phong_chieu INT PRIMARY KEY auto_increment,
    id_rap INT,
    so_phong INT,
    trang_thai BOOLEAN,
    FOREIGN KEY (id_rap) REFERENCES rap(id_rap)
);

-- Tạo bảng ghế
CREATE TABLE ghe (
    id_ghe INT PRIMARY KEY auto_increment,
    id_phong_chieu INT,
    hang CHAR(1),
    cot INT,
    trang_thai BOOLEAN,
    FOREIGN KEY (id_phong_chieu) REFERENCES phong_chieu(id_phong_chieu)
);

-- Tạo bảng suất chiếu
CREATE TABLE suat_chieu (
    id_suat_chieu INT PRIMARY KEY auto_increment,
    id_film INT,
    id_phong_chieu INT,
    ngay_chieu DATE,
    gio_chieu TIME,
    FOREIGN KEY (id_film) REFERENCES film(id_film),
    FOREIGN KEY (id_phong_chieu) REFERENCES phong_chieu(id_phong_chieu)
);

-- Tạo bảng ghế đã đặt
CREATE TABLE ghe_da_dat (
    id_suat_chieu INT,
    id_ve INT,
    id_ghe INT,
    primary key (id_suat_chieu, id_ve, id_ghe),
    FOREIGN KEY (id_suat_chieu) REFERENCES suat_chieu(id_suat_chieu),
    FOREIGN KEY (id_ghe) REFERENCES ghe(id_ghe),
    FOREIGN KEY (id_ve) REFERENCES ve(id_ve)
);
-- Tạo bảng thể loại đồ ăn
CREATE TABLE the_loai_do_an (
    id_the_loai_do_an INT PRIMARY KEY auto_increment,
    ten_the_loai VARCHAR(255)
);

-- Tạo bảng đồ ăn
CREATE TABLE do_an (
    id_do_an INT PRIMARY KEY auto_increment,
    id_the_loai_do_an INT,
    ten_do_an VARCHAR(255),
    gia decimal (10,2),
    FOREIGN KEY (id_the_loai_do_an) REFERENCES the_loai_do_an(id_the_loai_do_an)
);

-- Tạo bảng vé đồ ăn
CREATE TABLE ve_do_an (
    id_ve INT,
    id_do_an INT,
    PRIMARY KEY (id_ve, id_do_an),
    FOREIGN KEY (id_ve) REFERENCES ve(id_ve),
    FOREIGN KEY (id_do_an) REFERENCES do_an(id_do_an)
);
-- Chèn dữ liệu vào bảng thể loại đồ ăn
INSERT INTO the_loai_do_an (ten_the_loai) VALUES 
('Mì'),
('Cơm'),
('Pizza'),
('Hamburger'),
('Salad'),
('Sushi'),
('Kem'),
('Bánh mì'),
('Nước ngọt'),
('Trái cây');

-- Chèn dữ liệu vào bảng đồ ăn
INSERT INTO do_an (id_the_loai_do_an, ten_do_an, gia) VALUES 
(1, 'Mì xào', 20000),
(2, 'Cơm chiên', 30000),
(3, 'Pizza hải sản', 50000),
(4, 'Hamburger gà', 25000),
(5, 'Salad cà chua', 30000),
(6, 'Sushi cá hồi', 25000),
(7, 'Kem vani', 10000),
(8, 'Bánh mì thịt nướng', 20000),
(9, 'Nước ngọt Coca Cola', 30000),
(10, 'Trái cây tươi', 20000);


-- Chèn dữ liệu vào bảng quốc gia
INSERT INTO quoc_gia (ten_quoc_gia) VALUES
('Mỹ'),
('Anh'),
('Pháp'),
('Trung Quốc'),
('Nhật Bản'),
('Hàn Quốc'),
('Ấn Độ'),
('Canada'),
('Úc'),
('Đức');

-- Chèn dữ liệu vào bảng the_loai
INSERT INTO the_loai (ten_the_loai) VALUES
('Hành động'),
('Hài'),
('Kinh dị'),
('Tình cảm'),
('Viễn tưởng'),
('Hoạt hình'),
('Phiêu lưu'),
('Tâm lý'),
('Tội phạm'),
('Gia đình');

-- Chèn dữ liệu vào bảng film
INSERT INTO film (id_film, id_quoc_gia, id_the_loai, ten_phim, noi_dung, dien_vien, dao_dien, khoi_chieu, thoi_luong, gioi_han_do_tuoi, anh_minh_hoa) VALUES
(1, 1, 1, 'Avengers: Endgame', 'Sau sự kiện của Infinity War, các siêu anh hùng còn sống tìm cách đối mặt với Thanos một lần nữa trong một trận chiến cuối cùng để cứu thế giới.', 'Robert Downey Jr., Chris Evans, Mark Ruffalo', 'Anthony Russo, Joe Russo', '2019-04-26', 181, 13, 'https://ss-images.saostar.vn/wp700/2019/04/02/4887547/avengers-endgame-dolby-1165441.jpeg'),
(2, 2, 2, 'Bridget Jones''s Diary', 'Bridget Jones, một phụ nữ độc thân 30 tuổi, ghi chép cuộc sống hàng ngày của mình trong một nhật ký khi cô tìm kiếm tình yêu.', 'Renée Zellweger, Colin Firth, Hugh Grant', 'Sharon Maguire', '2001-04-13', 97, 16, 'https://www.miramax.com/assets/bridget_jones_diary_scrubbed_150413.jpg'),
(3, 3, 3, 'Insidious', 'Một gia đình phát hiện ra những sự kiện siêu nhiên kỳ quái xảy ra trong nhà mới của họ, và đang bị ám ảnh bởi một thế giới đen tối.', 'Patrick Wilson, Rose Byrne, Ty Simpkins', 'James Wan', '2011-04-01', 103, 16, 'https://musicart.xboxlive.com/7/4eac5000-0000-0000-0000-000000000002/504/image.jpg?w=1920&h=1080'),
(4, 4, 4, 'Titanic', 'Câu chuyện tình yêu của Jack và Rose, hai người từ các tầng lớp xã hội khác nhau, trên chuyến hải trình định mệnh của con tàu Titanic.', 'Leonardo DiCaprio, Kate Winslet, Billy Zane', 'James Cameron', '1997-12-19', 195, 12, 'https://upload.wikimedia.org/wikipedia/vi/a/ab/Titanic_3D_poster_Vietnam.jpg'),
(5, 5, 5, 'Your Name', 'Mitsuha và Taki, hai người trẻ tuổi sống ở hai nơi khác nhau, trao đổi cơ thể của họ thông qua một hiện tượng siêu nhiên.', 'Ryûnosuke Kamiki, Mone Kamishiraishi', 'Makoto Shinkai', '2016-08-26', 106, 13, 'https://m.media-amazon.com/images/M/MV5BNGYyNmI3M2YtNzYzZS00OTViLTkxYjAtZDIyZmE1Y2U1ZmQ2XkEyXkFqcGdeQXVyMTA4NjE0NjEy._V1_.jpg'),
(6, 6, 6, 'Spirited Away', 'Chihiro, một cô bé 10 tuổi, phải điều tra một thế giới siêu nhiên sau khi bố mẹ của cô đã bị biến thành heo trong một kỳ nghỉ.', 'Rumi Hiiragi, Miyu Irino, Mari Natsuki', 'Hayao Miyazaki', '2001-07-20', 125, 8, 'https://m.media-amazon.com/images/M/MV5BMjlmZmI5MDctNDE2YS00YWE0LWE5ZWItZDBhYWQ0NTcxNWRhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg'),
(7, 7, 7, 'PK', 'PK, một người ngoài hành tinh, đến Trái Đất và học cách sống như một con người khi gặp phải nhiều tình huống hài hước và bi thảm.', 'Aamir Khan, Anushka Sharma, Sanjay Dutt', 'Rajkumar Hirani', '2014-12-19', 153, 10, 'https://m.media-amazon.com/images/M/MV5BMTg5NzIzMzEyOF5BMl5BanBnXkFtZTgwMjgzMTg0MzE@._V1_FMjpg_UX1000_.jpg'),
(8, 8, 8, 'The Revenant', 'Trong một cuộc hành trình qua vùng hoang dã của Mỹ, một người sống sót phải chiến đấu với tự nhiên và lòng thù trong cuộc sống hoang dã.', 'Leonardo DiCaprio, Tom Hardy, Domhnall Gleeson', 'Alejandro González Iñárritu', '2015-12-25', 156, 16, 'https://m.media-amazon.com/images/M/MV5BMDE5OWMzM2QtOTU2ZS00NzAyLWI2MDEtOTRlYjIxZGM0OWRjXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_.jpg'),
(9, 9, 9, 'Mad Max: Fury Road', 'Trong một tương lai hậu tận thế, Max và Furiosa hợp tác để trốn thoát khỏi một tên chiến binh và giải cứu một nhóm phụ nữ.', 'Tom Hardy, Charlize Theron, Nicholas Hoult', 'George Miller', '2015-05-15', 120, 15, 'https://m.media-amazon.com/images/M/MV5BN2EwM2I5OWMtMGQyMi00Zjg1LWJkNTctZTdjYTA4OGUwZjMyXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg.jpg'),
(10, 10, 10, 'Finding Nemo', 'Marlin, một chú cá clown, đi tìm con trai mất tích của mình, Nemo, trong một cuộc hành trình qua đại dương rộng lớn.', 'Albert Brooks, Ellen DeGeneres, Alexander Gould', 'Andrew Stanton', '2003-05-30', 100, 6, 'https://m.media-amazon.com/images/M/MV5BZmYxZjg3OWEtNzg5Yi00M2YzLWI1YzYtYTQ0NTgwNzhjN2E1XkEyXkFqcGdeQXVyNDUyOTg3Njg@._V1_FMjpg_UX1000_.jpg');

-- Chèn dữ liệu vào bảng rap
INSERT INTO rap (ten_rap) VALUES
('Rạp 1'),
('Rạp 2'),
('Rạp 3'),
('Rạp 4'),
('Rạp 5'),
('Rạp 6'),
('Rạp 7'),
('Rạp 8'),
('Rạp 9'),
('Rạp 10');

-- Chèn dữ liệu vào bảng phong_chieu
INSERT INTO phong_chieu (id_rap, so_phong, trang_thai) VALUES
(1, 1, TRUE),
(2, 2, TRUE),
(3, 1, TRUE),
(4, 2, TRUE),
(5, 1, TRUE),
(6, 2, TRUE),
(7, 1, TRUE),
(8, 2, TRUE),
(9, 1, TRUE),
(10, 2, TRUE);

-- Chèn dữ liệu vào bảng ghe
INSERT INTO ghe (id_phong_chieu, hang, cot, trang_thai) VALUES
(1, 'A', 1, TRUE),
(1, 'A', 2, TRUE),
(1, 'A', 3, FALSE),
(1, 'B', 1, TRUE),
(1, 'B', 2, TRUE),
(1, 'B', 3, TRUE),
(2, 'A', 1, TRUE),
(2, 'A', 2, FALSE),
(2, 'B', 1, TRUE),
(2, 'B', 2, TRUE);

-- Chèn dữ liệu vào bảng account
INSERT INTO account (id_account, role, ho_ten, so_dien_thoai, email, ngay_sinh, gioi_tinh, avatar, mat_khau) VALUES
(1, 'admin', 'Nguyễn Văn A', '0396781589', 'admin@example.com', '1990-01-01', 'MALE', 'avatar1.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(2, 'user', 'Trần Thị B', '0123456789', 'user@example.com', '1995-05-05', 'FEMALE', 'avatar2.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(3, 'user', 'Lê Văn C', '0369876543', 'le.van.c@example.com', '2000-10-10', 'MALE', 'avatar3.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(4, 'user', 'Phạm Thị D', '0999999999', 'pham.thi.d@example.com', '1998-12-25', 'FEMALE', 'avatar4.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(5, 'user', 'Hoàng Văn E', '0777777777', 'hoang.van.e@example.com', '1988-07-07', 'MALE', 'avatar5.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(6, 'user', 'Vũ Thị F', '0888888888', 'vu.thi.f@example.com', '1992-09-15', 'FEMALE', 'avatar6.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(7, 'user', 'Nguyễn Văn G', '0666666666', 'nguyen.van.g@example.com', '1993-03-20', 'MALE', 'avatar7.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(8, 'user', 'Trần Văn H', '0555555555', 'tran.van.h@example.com', '1997-08-18', 'MALE', 'avatar8.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(9, 'user', 'Lê Thị I', '0444444444', 'le.thi.i@example.com', '1994-06-30', 'FEMALE', 'avatar9.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(10, 'user', 'Phan Văn J', '0333333333', 'phan.van.j@example.com', '1985-04-12', 'MALE', 'avatar10.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(11, 'user', 'Trần Thị B', '012345678', 'uer@example.com', '1995-05-05', 'FEMALE', 'avatar2.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(12, 'user', 'Lê Văn C', '0369876543', 'le.vn.c@example.com', '2000-10-10', 'MALE', 'avatar3.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(13, 'user', 'Phạm Thị D', '099999999', 'pham.hi.d@example.com', '1998-12-25', 'FEMALE', 'avatar4.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(14, 'user', 'Hoàng Văn E', '077777777', 'hoangvan.e@example.com', '1988-07-07', 'MALE', 'avatar5.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(15, 'user', 'Vũ Thị F', '088888888', 'vu.hi.f@example.com', '1992-09-15', 'FEMALE', 'avatar6.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(16, 'user', 'Nguyễn Văn G', '066666666', 'nuyen.van.g@example.com', '1993-03-20', 'MALE', 'avatar7.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(17, 'user', 'Trần Văn H', '055555555', 'trn.van.h@example.com', '1997-08-18', 'MALE', 'avatar8.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(18, 'user', 'Lê Thị I', '044444444', 'le.i.i@example.com', '1994-06-30', 'FEMALE', 'avatar9.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm'),
(19, 'user', 'Phan Văn J', '033333333', 'pan.van.j@example.com', '1985-04-12', 'MALE', 'avatar10.jpg', '$2a$10$E2ocE0kKbLOdh6lftRqm1Oi82OAHSrWWkrc7I6I2Pi3rBcKdcqhvm');

-- Chèn dữ liệu vào bảng danh_gia
INSERT INTO danh_gia (id_danh_gia, id_user, id_film, noi_dung) VALUES
(1, 1, 1, 'Phim rất hay, đáng để xem.'),
(2, 2, 2, 'Phim thú vị, cười không ngớt.'),
(3, 3, 3, 'Phim kinh dị, gây ám ảnh.'),
(4, 4, 4, 'Phim lãng mạn, rất cảm động.'),
(5, 5, 5, 'Phim đầy lãng mạn và cảm động.'),
(6, 6, 6, 'Phim hoạt hình hấp dẫn.'),
(7, 7, 7, 'Phim hài hước, xem xong muốn xem lại.'),
(8, 8, 8, 'Phim kịch tính, diễn xuất tốt.'),
(9, 9, 9, 'Phim hành động đỉnh cao.'),
(10, 10, 10, 'Phim hoạt hình tuyệt vời.');

-- Chèn dữ liệu vào bảng ve
INSERT INTO ve (id_ve, id_khuyen_mai, id_account, gia) VALUES
(1, NULL, 1, 100000),
(2, NULL, 2, 150000),
(3, NULL, 3, 120000),
(4, NULL, 4, 90000),
(5, NULL, 5, 80000),
(6, NULL, 6, 110000),
(7, NULL, 7, 130000),
(8, NULL, 8, 140000),
(9, NULL, 9, 95000),
(10, NULL, 10, 100000);

-- Chèn dữ liệu vào bảng vé đồ ăn
-- Giả sử có vé với id từ 1 đến 10
-- Chọn ngẫu nhiên một số món ăn trong bảng đồ ăn
-- Giả sử mỗi vé có từ 1 đến 3 món ăn

-- Vé 1
INSERT INTO ve_do_an (id_ve, id_do_an) VALUES
(1, 1), (1, 3), (1, 5);

-- Vé 2
INSERT INTO ve_do_an (id_ve, id_do_an) VALUES
(2, 2), (2, 4);

-- Vé 3
INSERT INTO ve_do_an (id_ve, id_do_an) VALUES
(3, 6), (3, 7), (3, 9);

-- Vé 4
INSERT INTO ve_do_an (id_ve, id_do_an) VALUES
(4, 8), (4, 10);

-- Vé 5
INSERT INTO ve_do_an (id_ve, id_do_an) VALUES
(5, 1), (5, 2), (5, 3);

-- Vé 6
INSERT INTO ve_do_an (id_ve, id_do_an) VALUES
(6, 4), (6, 5), (6, 6);

-- Vé 7
INSERT INTO ve_do_an (id_ve, id_do_an) VALUES
(7, 7), (7, 8), (7, 9);

-- Vé 8
INSERT INTO ve_do_an (id_ve, id_do_an) VALUES
(8, 10), (8, 1);

-- Vé 9
INSERT INTO ve_do_an (id_ve, id_do_an) VALUES
(9, 2), (9, 3), (9, 4);

-- Vé 10
INSERT INTO ve_do_an (id_ve, id_do_an) VALUES
(10, 5), (10, 6), (10, 7);

-- Chèn dữ liệu vào bảng khuyen_mai
INSERT INTO khuyen_mai (id_khuyen_mai, code, giam_gia, ngay_het_han, anh_minh_hoa, noi_dung) VALUES
(1, 'SALE10', 10, '2024-04-30', 'anhminhhoa', 'Chương trình áp dụng từ ... đến.... Dùng nhanh không hết'),
(2, 'BIGSALE', 20, '2024-05-05', 'anhminhhoa', 'Chương trình áp dụng từ ... đến.... Dùng nhanh không hết'),
(3, 'HAPPYDAY', 15, '2024-04-25', 'anhminhhoa', 'Chương trình áp dụng từ ... đến.... Dùng nhanh không hết'),
(4, 'HOTDEAL', 25, '2024-04-28', 'anhminhhoa', 'Chương trình áp dụng từ ... đến.... Dùng nhanh không hết'),
(5, 'SUMMERSALE', 30, '2024-05-10', 'anhminhhoa', 'Chương trình áp dụng từ ... đến.... Dùng nhanh không hết'),
(6, 'EARLYBIRD', 10, '2024-05-15', 'anhminhhoa', 'Chương trình áp dụng từ ... đến.... Dùng nhanh không hết'),
(7, 'FAMILYTIME', 20, '2024-05-20', 'anhminhhoa', 'Chương trình áp dụng từ ... đến.... Dùng nhanh không hết'),
(8, 'FRIENDSHIP', 15, '2024-05-25', 'anhminhhoa', 'Chương trình áp dụng từ ... đến.... Dùng nhanh không hết'),
(9, 'LOVEMOVIE', 25, '2024-05-30', 'anhminhhoa', 'Chương trình áp dụng từ ... đến.... Dùng nhanh không hết'),
(10, 'WEEKENDFUN', 30, '2024-06-05', 'anhminhhoa', 'Chương trình áp dụng từ ... đến.... Dùng nhanh không hết');
-- Chèn dữ liệu vào bảng suat_chieu
INSERT INTO suat_chieu (id_suat_chieu, id_film, id_phong_chieu, ngay_chieu, gio_chieu) VALUES
(1, 1, 1, '2024-04-03', '13:00:00'),
(2, 2, 2, '2024-04-03', '15:30:00'),
(3, 3, 3, '2024-04-03', '18:00:00'),
(4, 4, 4, '2024-04-03', '20:30:00'),
(5, 5, 5, '2024-04-03', '10:00:00'),
(6, 6, 6, '2024-04-03', '12:30:00'),
(7, 7, 7, '2024-04-03', '14:00:00'),
(8, 8, 8, '2024-04-03', '16:30:00'),
(9, 9, 9, '2024-04-03', '19:00:00'),
(10, 10, 10, '2024-04-03', '21:30:00');

-- Chèn dữ liệu vào bảng ghe_da_dat
-- Giả sử có 10 ghế trong mỗi phòng chiếu và 10 suất chiếu
-- Chọn ngẫu nhiên 10 ghế đã đặt từ 1 đến 100 cho mỗi suất chiếu

-- Suất chiếu 1
INSERT INTO ghe_da_dat (id_suat_chieu, id_ve, id_ghe) VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 3),
(1, 4, 4),
(1, 5, 5),
(1, 6, 6),
(1, 7, 7),
(1, 8, 8),
(1, 9, 9),
(1, 10, 10);

-- Suất chiếu 2
INSERT INTO ghe_da_dat (id_suat_chieu, id_ve, id_ghe) VALUES
(2, 1, 1),
(2, 2, 2),
(2, 3, 3),
(2, 4, 4),
(2, 5, 5),
(2, 6, 6),
(2, 7, 7),
(2, 8, 8),
(2, 9, 9),
(2, 10, 10);


