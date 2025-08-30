using System.Text.Json.Serialization;

namespace PegawaiFE.Models
{
    public class Pegawai
    {
        [JsonPropertyName("idPegawai")]
        public long IdPegawai { get; set; }

        [JsonPropertyName("kodePegawai")]
        public string KodePegawai { get; set; } = string.Empty;

        [JsonPropertyName("namaPegawai")]
        public string NamaPegawai { get; set; } = string.Empty;

        [JsonPropertyName("jabatan")]
        public Jabatan? Jabatan { get; set; }

        [JsonPropertyName("cabang")]
        public Cabang? Cabang { get; set; }

        [JsonPropertyName("tanggalMulaiKontrak")]
        public DateTime TanggalMulaiKontrak { get; set; }

        [JsonPropertyName("tanggalHabisKontrak")]
        public DateTime TanggalHabisKontrak { get; set; }
    }
}
