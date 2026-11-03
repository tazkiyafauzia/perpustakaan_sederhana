/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.ub.filkom.contoh.entity;

/**
 *
 * @author ASUS
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "transaksi_pinjam")
public class TransaksiPinjam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_anggota")
    private Anggota anggota;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_buku")
    private Buku buku;

    private LocalDate tanggalPinjam;
    private LocalDate tanggalJatuhTempo;
    private LocalDate tanggalKembali;

    protected TransaksiPinjam() { }

    public TransaksiPinjam(Anggota anggota, Buku buku,
                           LocalDate tanggalPinjam, LocalDate tanggalJatuhTempo) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public Long getId() { return id; }
    public Anggota getAnggota() { return anggota; }
    public Buku getBuku() { return buku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalJatuhTempo() { return tanggalJatuhTempo; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public boolean isAktif() { return tanggalKembali == null; }
    public void kembalikan(LocalDate tanggal) {this.tanggalKembali = tanggal;}
}
