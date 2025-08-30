using System;

namespace PegawaiFE.Models
{
    public class PegawaiDTO
    {
        public string KodePegawai { get; set; } = string.Empty;
        public string NamaPegawai { get; set; } = string.Empty;
        public string NamaJabatan { get; set; } = string.Empty;
        public string NamaCabang { get; set; } = string.Empty;

        public DateTime TanggalMulaiKontrak { get; set; } = DateTime.MinValue;
        public DateTime TanggalHabisKontrak { get; set; } = DateTime.MinValue;

        public PegawaiDTO() { }

        public PegawaiDTO(string kodePegawai, string namaPegawai, string namaJabatan, string namaCabang, DateTime tanggalMulai, DateTime tanggalHabis)
        {
            KodePegawai = kodePegawai;
            NamaPegawai = namaPegawai;
            NamaJabatan = namaJabatan;
            NamaCabang = namaCabang;
            TanggalMulaiKontrak = tanggalMulai;
            TanggalHabisKontrak = tanggalHabis;
        }
    }
}
