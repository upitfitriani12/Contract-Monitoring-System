# 📌 Employment Contract Monitoring System

Aplikasi ini dibuat untuk memenuhi kebutuhan **monitoring kontrak kerja pegawai** di sebuah perusahaan.  
Sistem ini terdiri dari:
- **Backend** → Java Spring Boot
- **Frontend** → .NET Core MVC
- **Database** → MySQL

---

## 📂 Struktur Project

### Backend (Java Spring Boot)
- `contract-application/`

---

### Frontend (.NET Core MVC)
- `PegawaiFE/`

## 1️⃣ Database (Scripting & Query)

- **Database**: MySQL
- **Tabel**:
  - `jabatan`
  - `cabang`
  - `pegawai`
- **Kolom Data Pegawai**:
  - `kode_pegawai`
  - `nama_pegawai`
  - `nama_cabang`
  - `nama_jabatan`
  - `tanggal_mulai_kontrak`
  - `tanggal_habis_kontrak`

---

## 2️⃣ Backend (Java Spring Boot)

- **Framework**: Spring Boot
- **Fitur Utama**:
  - CRUD Pegawai
  - CRUD Cabang
  - CRUD Jabatan
  - Upload & Import Excel

- **REST API Endpoint**:
  - **Pegawai API** (`/api/pegawai`)
    - CRUD Pegawai (Get All, Get by ID, Post, Put, Delete)

  - **Cabang API** (`/api/cabang`)
    - CRUD Cabang (Get All, Get by ID, Post, Put, Delete)

  - **Jabatan API** (`/api/jabatan`)
    - CRUD Jabatan (Get All, Get by ID, Post, Put, Delete)

  - **Excel API** (`/api/excel`)
    - `POST /api/excel/upload` → Upload & import data dari Excel

---

## 3️⃣ Frontend (.NET Core MVC)

- **Framework**: ASP.NET Core MVC
- **Fitur Utama**:
  - Tabel daftar **Pegawai**
  - Tabel daftar **Cabang**
  - Tabel daftar **Jabatan**
  - Form **Upload Excel**
  - Menampilkan data hasil import Excel

- **Integrasi Backend**:
  - Menggunakan `HttpClient` untuk akses REST API.
  - Endpoint yang dipanggil:
    - `GET /api/pegawai`
    - `GET /api/cabang`
    - `GET /api/jabatan`
    - `POST /api/excel/upload`

- **Deployment**:
  **IIS Windows** 

---