/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.ub.filkom.contoh.controller;

/**
 *
 * @author ASUS
 */

import id.ac.ub.filkom.contoh.service.AnggotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Controller: halaman lihat anggota. */
@Controller
public class AnggotaController {

    private final AnggotaService anggotaService;

    public AnggotaController(AnggotaService anggotaService) {
        this.anggotaService = anggotaService;
    }

    // GET http://localhost:8080/anggota
    @GetMapping("/anggota")
    public String lihatAnggota(Model model) {
        model.addAttribute("daftarAnggota", anggotaService.lihatSemuaAnggota());
        return "anggota";            // -> templates/anggota.html
    }
    
    // GET /anggota/tambah -> tampilkan form
    @GetMapping("/anggota/tambah")
    public String formTambahAnggota() {
        return "anggota-tambah"; // -> templates/anggota-tambah.html
    }

    // POST /anggota/tambah -> simpan, lalu balik ke daftar
    @PostMapping("/anggota/tambah")
    public String simpanAnggota(@RequestParam String idAnggota,
                                @RequestParam String nama,
                                @RequestParam int batasPinjam) {
        anggotaService.tambahAnggota(idAnggota, nama, batasPinjam);
        return "redirect:/anggota";
    }
}
