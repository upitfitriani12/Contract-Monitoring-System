# Employment Contract Monitoring API

---

### Base URL
http://localhost:8080/api

# Endpoints 

## 1. Cabang

### Mengambil semua data cabang

- GET /api/cabang

**Response 200 (OK)**
```json
[
  {
    "id": "number",
    "namaCabang": "string"
  },
  {
    "id": "number",
    "namaCabang": "string"
  }
] 
```

### Mengambil data Cabang berdasarkan id

- GET /api/cabang/{id}

**Response 200 (OK)**

```json
{
  "id": "number",
  "namaCabang": "string"
}
```
**Response 404 (Not Found)**

```json
{
  "error": "Cabang not found"
}
```

### Menambahkan data Cabang baru

- POST /api/cabang

**Request Body**

```json
{
  "namaCabang": "string"
}
```
**Response 201 (Created)**

```json
{
  "id": "number",
  "namaCabang": "string"
}
```

### Update data Cabang berdasarkan id

- PUT /api/cabang/{id}

**Request Body**

```json
{
  "namaCabang": "string"
}
```

**Response 200 (OK)**

```json
{
  "id": "number",
  "namaCabang": "string"
}
```

**Response 404 (Not Found)**

```json
{
  "error": "Cabang not found"
}
```

### Menghapus data Cabang berdasarkan id

- DELETE /api/cabang/{id}

## 2. Jabatan

### Mengambil semua data jabatan

- GET /api/jabatan

**Response 200 (OK)**
```json
[
  {
    "id": "number",
    "namaJabatan": "string"
  },
  {
    "id": "number",
    "namaJabatan": "string"
  }
]
```

### Mengambil data Jabatan berdasarkan id

- GET /api/jabatan/{id}

**Response 200 (OK)**

```json
{
  "id": "number",
  "namaJabatan": "string"
}
```
**Response 404 (Not Found)**

```json
{
  "error": "Jabatan not found"
}
```

### Menambahkan data Jabatan baru

- POST /api/jabatan

**Request Body**

```json
{
  "namaJabatan": "string"
}
```
**Response 201 (Created)**

```json
{
  "id": "number",
  "namaJabatan": "string"
}
```

### Update data Jabatan berdasarkan id

- PUT /api/jabatan/{id}

**Request Body**

```json
{
  "namaJabatan": "string"
}
```

**Response 200 (OK)**

```json
{
  "id": "number",
  "namaJabatan": "string"
}
```

**Response 404 (Not Found)**

```json
{
  "error": "Jabatan not found"
}
```

### Menghapus data Jabatan berdasarkan id

- DELETE /api/jabatan/{id}

## 2. Pegawai

### Mengambil semua data pegawai

- GET /api/pegawai

**Response 200 (OK)**
```json
[
  {
    "kodePegawai": "string",
    "namaPegawai": "string",
    "namaJabatan": "string",
    "namaCabang": "string",
    "tanggalMulaiKontrak": "string",
    "tanggalHabisKontrak": "string"
  },
  {
    "kodePegawai": "string",
    "namaPegawai": "string",
    "namaJabatan": "string",
    "namaCabang": "string",
    "tanggalMulaiKontrak": "string",
    "tanggalHabisKontrak": "string"
  }
]
```

### Mengambil data Pegawai berdasarkan id

- GET /api/pegawai/{id}

**Response 200 (OK)**

```json
{
  "kodePegawai": "string",
  "namaPegawai": "string",
  "namaJabatan": "string",
  "namaCabang": "string",
  "tanggalMulaiKontrak": "string",
  "tanggalHabisKontrak": "string"
}
```
**Response 404 (Not Found)**

```json
{
  "error": "Pegawai not found"
}
```

### Menambahkan data Pegawai baru

- POST /api/pegawai

**Request Body**

```json
{
  "kodePegawai": "string",
  "namaPegawai": "string",
  "jabatan": { "id": "number" },
  "cabang": { "id": "number" },
  "tanggalMulaiKontrak": "string",
  "tanggalHabisKontrak": "string"
}
```
**Response 201 (Created)**

```json
{
  "kodePegawai": "string",
  "namaPegawai": "string",
  "jabatan": { "id": "number" },
  "cabang": { "id": "number" },
  "tanggalMulaiKontrak": "string",
  "tanggalHabisKontrak": "string"
}
```

### Update data Pegawai berdasarkan id

- PUT /api/pegawai/{id}

**Request Body**

```json
{
  "kodePegawai": "string",
  "namaPegawai": "string",
  "jabatan": { "id": "number" },
  "cabang": { "id": "number" },
  "tanggalMulaiKontrak": "string",
  "tanggalHabisKontrak": "string"
}
```

**Response 200 (OK)**

```json
{
  "kodePegawai": "string",
  "namaPegawai": "string",
  "jabatan": { "id": "number" },
  "cabang": { "id": "number" },
  "tanggalMulaiKontrak": "string",
  "tanggalHabisKontrak": "string"
}
```

**Response 404 (Not Found)**

```json
{
  "error": "Pegawai not found"
}
```

### Menghapus data Pegawai berdasarkan id

- DELETE /api/pegawai/{id}

## 4. Upload File

### Import File Excel

- POST /api/excel/upload

**Request**
  - Form-data dengan key file dan value berupa file Excel (.xlsx atau .xls)

**Response 200 (OK)**
"Upload berhasil"

**Response 400 (Bad Request)**
"Upload gagal <pesan error>"














