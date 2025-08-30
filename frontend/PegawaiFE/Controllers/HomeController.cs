using Microsoft.AspNetCore.Mvc;
using PegawaiFE.Models;
using System.Net.Http.Json;
using ClosedXML.Excel;
using System.Data;

namespace PegawaiFE.Controllers
{
    public class HomeController : Controller
    {
        private readonly HttpClient _httpClient;

        public HomeController(IHttpClientFactory httpClientFactory)
        {
            _httpClient = httpClientFactory.CreateClient();
            _httpClient.BaseAddress = new Uri("http://localhost:8080/");
        }

        public async Task<IActionResult> Index()
        {
            var pegawaiList = await _httpClient.GetFromJsonAsync<List<PegawaiDTO>>("api/pegawai");
            return View(pegawaiList);
        }

        public async Task<IActionResult> Cabang()
        {
            var cabangList = await _httpClient.GetFromJsonAsync<List<Cabang>>("api/cabang");
            return View(cabangList);
        }

        public async Task<IActionResult> Jabatan()
        {
            var jabatanList = await _httpClient.GetFromJsonAsync<List<Jabatan>>("api/jabatan");
            return View(jabatanList);
        }

        public IActionResult Upload() => View();

        [HttpPost]
        public async Task<IActionResult> Upload(IFormFile file)
        {
            if (file != null && file.Length > 0)
            {
                DataTable dt = new DataTable();

                using (var stream = file.OpenReadStream())
                {
                    using var workbook = new XLWorkbook(stream);
                    var worksheet = workbook.Worksheet(1); // Sheet pertama
                    bool firstRow = true;
                    foreach (var row in worksheet.RowsUsed())
                    {
                        if (firstRow)
                        {
                            foreach (var cell in row.Cells())
                                dt.Columns.Add(cell.Value.ToString());
                            firstRow = false;
                        }
                        else
                        {
                            dt.Rows.Add();
                            int i = 0;
                            foreach (var cell in row.Cells())
                                dt.Rows[dt.Rows.Count - 1][i++] = cell.Value.ToString();
                        }
                    }
                }

                ViewBag.DataTable = dt;

                // Kirim ke API
                using var content = new MultipartFormDataContent();
                using var fileStream = file.OpenReadStream();
                content.Add(new StreamContent(fileStream), "file", file.FileName);

                var response = await _httpClient.PostAsync("api/excel/upload", content);
                if (response.IsSuccessStatusCode)
                    ViewBag.Message = "Upload berhasil!";
                else
                    ViewBag.Message = "Upload gagal!";
            }
            else
            {
                ViewBag.Message = "File belum dipilih!";
            }

            return View();
        }
    }
}