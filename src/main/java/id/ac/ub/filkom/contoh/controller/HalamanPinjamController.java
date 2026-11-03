/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.ub.filkom.contoh.controller;

/**
 *
 * @author ASUS
 */

import id.ac.ub.filkom.contoh.entity.Bukti;
import id.ac.ub.filkom.contoh.service.PinjamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** Boundary: halaman pinjam buku (controller + template pinjam.html / bukti.html). */
@Controller
public class HalamanPinjamController {

    private final PinjamService pinjamService;

    public HalamanPinjamController(PinjamService pinjamService) {
        this.pinjamService = pinjamService;
    }

    // GET /pinjam -> form pinjam
    @GetMapping("/pinjam")
    public String formPinjam(Model model) {
        isiPilihan(model);
        return "pinjam";
    }

    // POST /pinjam -> proses; sukses = bukti, gagal = kembali ke form + pesan
    @PostMapping("/pinjam")
    public String prosesPinjam(@RequestParam String idAnggota,
                               @RequestParam String idBuku,
                               Model model) {
        try {
            Bukti bukti = pinjamService.pinjam(idAnggota, idBuku);
            model.addAttribute("bukti", bukti);
            return "bukti";
        } catch (PinjamGagalException e) {
            model.addAttribute("pesanError", e.getMessage());
            isiPilihan(model);
            return "pinjam";
        }
    }

    private void isiPilihan(Model model) {
        model.addAttribute("daftarAnggota", pinjamService.daftarAnggota());
        model.addAttribute("daftarBuku", pinjamService.daftarBuku());
    }
    
    @GetMapping("/kembali")
    public String daftarKembali(Model model) {
        model.addAttribute("daftarPinjam", pinjamService.daftarPinjamAktif());
        return "kembali";
    }

    @PostMapping("/kembali")
    public String prosesKembali(@RequestParam Long idTransaksi,
                                RedirectAttributes redirect) {
        try {
            redirect.addFlashAttribute("pesan", pinjamService.kembalikan(idTransaksi));
        } catch (PinjamGagalException e) {
            redirect.addFlashAttribute("pesanError", e.getMessage());
        }
        return "redirect:/kembali";
    }
}
