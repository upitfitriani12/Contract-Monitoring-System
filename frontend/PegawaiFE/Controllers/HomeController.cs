using Microsoft.AspNetCore.Mvc;
using PegawaiFE.Models;
using System.Net.Http.Json;

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
                using var content = new MultipartFormDataContent();
                using var fileStream = file.OpenReadStream();
                content.Add(new StreamContent(fileStream), "file", file.FileName);

                var response = await _httpClient.PostAsync("api/excel/upload", content);
                if (response.IsSuccessStatusCode)
                    ViewBag.Message = "Upload berhasil!";
                else
                    ViewBag.Message = "Upload gagal!";
            }
            return View();
        }
    }
}
