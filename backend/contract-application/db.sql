-- Database: employment_contract;
CREATE DATABASE employment_contract2;
USE employment_contract2;
select * from jabatan;
select * from pegawai;
select * from cabang;

-- Table: jabatan
CREATE TABLE IF NOT EXISTS jabatan (
    id_jabatan INT AUTO_INCREMENT PRIMARY KEY,
    nama_jabatan VARCHAR(100) NOT NULL
);

-- Table: cabang
CREATE TABLE IF NOT EXISTS cabang (
    id_cabang INT AUTO_INCREMENT PRIMARY KEY,
    nama_cabang VARCHAR(100) NOT NULL
);

-- Table: pegawai
CREATE TABLE IF NOT EXISTS pegawai (
    id_pegawai INT AUTO_INCREMENT PRIMARY KEY,
    kode_pegawai VARCHAR(50) UNIQUE NOT NULL,
    nama_pegawai VARCHAR(255) NOT NULL,
    id_jabatan INT,
    id_cabang INT,
    tanggal_mulai_kontrak DATE NOT NULL,
    tanggal_habis_kontrak DATE NOT NULL,
    FOREIGN KEY (id_jabatan) REFERENCES jabatan(id_jabatan),
    FOREIGN KEY (id_cabang) REFERENCES cabang(id_cabang)
);

-- Contoh Data
INSERT INTO jabatan (nama_jabatan) VALUES
('Manajer'),
('Staff IT'),
('Akuntan'),
('Sekretaris');

INSERT INTO cabang (nama_cabang) VALUES
('Jakarta Pusat'),
('Surabaya'),
('Bandung');

INSERT INTO pegawai (kode_pegawai, nama_pegawai, id_jabatan, id_cabang, tanggal_mulai_kontrak, tanggal_habis_kontrak) VALUES
('P001', 'Budi Santoso', 1, 1, '2022-01-15', '2024-01-15'),
('P002', 'Ani Wijaya', 2, 1, '2023-03-01', '2024-03-01'),
('P003', 'Citra Dewi', 3, 2, '2022-06-20', '2024-06-20'),
('P004', 'Dedi Kurniawan', 2, 3, '2023-01-10', '2024-07-10'),
('P005', 'Eko Prasetyo', 1, 1, '2023-09-01', '2025-09-01'),
('P006', 'Fina Lestari', 4, 2, '2023-11-11', '2024-05-11');


DELIMITER //
CREATE PROCEDURE GetPegawaiHabisKontrak (
    IN p_filter_nama_pegawai VARCHAR(255),
    IN p_filter_kode_pegawai VARCHAR(50),
    IN p_filter_nama_jabatan VARCHAR(100),
    IN p_filter_nama_cabang VARCHAR(100),
    IN p_days_until_expiration INT
)
BEGIN
    SET @sql = 'SELECT p.kode_pegawai, p.nama_pegawai, c.nama_cabang, j.nama_jabatan, p.tanggal_mulai_kontrak, p.tanggal_habis_kontrak
                FROM pegawai p
                JOIN jabatan j ON p.id_jabatan = j.id_jabatan
                JOIN cabang c ON p.id_cabang = c.id_cabang
                WHERE 1=1';

    IF p_filter_nama_pegawai IS NOT NULL AND p_filter_nama_pegawai != '' THEN
        SET @sql = CONCAT(@sql, ' AND p.nama_pegawai LIKE ''%', p_filter_nama_pegawai, '%''');
    END IF;

    IF p_filter_kode_pegawai IS NOT NULL AND p_filter_kode_pegawai != '' THEN
        SET @sql = CONCAT(@sql, ' AND p.kode_pegawai LIKE ''%', p_filter_kode_pegawai, '%''');
    END IF;

    IF p_filter_nama_jabatan IS NOT NULL AND p_filter_nama_jabatan != '' THEN
        SET @sql = CONCAT(@sql, ' AND j.nama_jabatan LIKE ''%', p_filter_nama_jabatan, '%''');
    END IF;

    IF p_filter_nama_cabang IS NOT NULL AND p_filter_nama_cabang != '' THEN
        SET @sql = CONCAT(@sql, ' AND c.nama_cabang LIKE ''%', p_filter_nama_cabang, '%''');
    END IF;

    IF p_days_until_expiration IS NOT NULL THEN
        SET @sql = CONCAT(@sql, ' AND p.tanggal_habis_kontrak BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL ', p_days_until_expiration, ' DAY)');
    END IF;

    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END //
DELIMITER ;
