using System.Text.Json.Serialization;
namespace PegawaiFE.Models

{
    public class Jabatan
    {
        [JsonPropertyName("idJabatan")]
public long IdJabatan { get; set; }

[JsonPropertyName("namaJabatan")]
public string NamaJabatan { get; set; } = string.Empty;

    }
}