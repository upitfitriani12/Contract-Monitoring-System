using System.Text.Json.Serialization;

namespace PegawaiFE.Models
{
    public class Cabang
{
    [JsonPropertyName("idCabang")]
    public long IdCabang { get; set; }

    [JsonPropertyName("namaCabang")]
    public string NamaCabang { get; set; } = string.Empty;
}

}
