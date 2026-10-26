/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.ub.filkom.contoh.service;

/**
 *
 * @author ASUS
 */

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Service: logika untuk fitur anggota. */
@Service
public class AnggotaService {

    private final AnggotaRepository anggotaRepository;

    public AnggotaService(AnggotaRepository anggotaRepository) {
        this.anggotaRepository = anggotaRepository;
    }

    public List<Anggota> lihatSemuaAnggota() {
        return anggotaRepository.findAll();
    }
    
    public void tambahAnggota(String idAnggota, String nama, int batasPinjam) {
    anggotaRepository.save(new Anggota(idAnggota, nama, batasPinjam));
    }
}
