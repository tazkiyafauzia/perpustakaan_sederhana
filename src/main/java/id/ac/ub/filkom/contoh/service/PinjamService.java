/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.ub.filkom.contoh.service;

/**
 *
 * @author ASUS
 */

import id.ac.ub.filkom.contoh.controller.PinjamGagalException;
import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.Bukti;
import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.TransaksiPinjam;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import id.ac.ub.filkom.contoh.repository.BukuRepository;
import id.ac.ub.filkom.contoh.repository.TransaksiPinjamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.List;

@Service
public class PinjamService {

    private static final int LAMA_PINJAM_HARI = 7;

    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;
    private final TransaksiPinjamRepository transaksiRepository;

    public PinjamService(AnggotaRepository anggotaRepository,
                         BukuRepository bukuRepository,
                         TransaksiPinjamRepository transaksiRepository) {
        this.anggotaRepository = anggotaRepository;
        this.bukuRepository = bukuRepository;
        this.transaksiRepository = transaksiRepository;
    }

    public List<Anggota> daftarAnggota() {
        return anggotaRepository.findAll();
    }

    public List<Buku> daftarBuku() {
        return bukuRepository.findAll();
    }

    @Transactional
    public Bukti pinjam(String idAnggota, String idBuku) {
        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() -> new PinjamGagalException("Anggota tidak ditemukan."));
        Buku buku = bukuRepository.findById(idBuku)
                .orElseThrow(() -> new PinjamGagalException("Buku tidak ditemukan."));

        if (buku.getStok() <= 0) {
            throw new PinjamGagalException("Stok buku \"" + buku.getJudul() + "\" habis.");
        }
        if (transaksiRepository.countByAnggotaAndTanggalKembaliIsNull(anggota) >= anggota.getBatasPinjam()) {
            throw new PinjamGagalException(anggota.getNama()
                    + " sudah mencapai batas pinjam (" + anggota.getBatasPinjam() + " buku).");
        }

        buku.kurangiStok();
        bukuRepository.save(buku);

        LocalDate pinjam = LocalDate.now();
        TransaksiPinjam transaksi = transaksiRepository.save(
                new TransaksiPinjam(anggota, buku, pinjam, pinjam.plusDays(LAMA_PINJAM_HARI)));

        return new Bukti(transaksi.getId(), anggota.getNama(), buku.getJudul(),
                transaksi.getTanggalPinjam(), transaksi.getTanggalJatuhTempo());
    }
    
    public List<TransaksiPinjam> daftarPinjamAktif() {return transaksiRepository.findByTanggalKembaliIsNull();}

    @Transactional
    public String kembalikan(Long idTransaksi) {
        TransaksiPinjam transaksi = transaksiRepository.findById(idTransaksi)
                .orElseThrow(() -> new PinjamGagalException("Transaksi tidak ditemukan."));

        if (!transaksi.isAktif()) {
            throw new PinjamGagalException("Buku ini sudah dikembalikan.");
        }

        LocalDate hariIni = LocalDate.now();
        transaksi.kembalikan(hariIni);
        transaksiRepository.save(transaksi);

        Buku buku = transaksi.getBuku();
        buku.tambahStok();
        bukuRepository.save(buku);

        String pesan = "\"" + buku.getJudul() + "\" dari " + transaksi.getAnggota().getNama()
                + " berhasil dikembalikan.";
        if (hariIni.isAfter(transaksi.getTanggalJatuhTempo())) {
            long hari = ChronoUnit.DAYS.between(transaksi.getTanggalJatuhTempo(), hariIni);
            pesan += " Terlambat " + hari + " hari.";
        }
        return pesan;
    }
}
