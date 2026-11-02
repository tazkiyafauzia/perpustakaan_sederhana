/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.ub.filkom.contoh.entity;

/**
 *
 * @author ASUS
 */

import java.time.LocalDate;

/** Data yang ditampilkan di halaman bukti peminjaman. */
public class Bukti {

    private final Long idTransaksi;
    private final String namaAnggota;
    private final String judulBuku;
    private final LocalDate tanggalPinjam;
    private final LocalDate tanggalJatuhTempo;

    public Bukti(Long idTransaksi, String namaAnggota, String judulBuku,
                 LocalDate tanggalPinjam, LocalDate tanggalJatuhTempo) {
        this.idTransaksi = idTransaksi;
        this.namaAnggota = namaAnggota;
        this.judulBuku = judulBuku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public Long getIdTransaksi() { return idTransaksi; }
    public String getNamaAnggota() { return namaAnggota; }
    public String getJudulBuku() { return judulBuku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalJatuhTempo() { return tanggalJatuhTempo; }
}
