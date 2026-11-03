/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package id.ac.ub.filkom.contoh.repository;

/**
 *
 * @author ASUS
 */

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.TransaksiPinjam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaksiPinjamRepository extends JpaRepository<TransaksiPinjam, Long> {

    // hanya pinjaman yang belum dikembalikan (untuk cek batas pinjam)
    long countByAnggotaAndTanggalKembaliIsNull(Anggota anggota);

    // daftar pinjaman aktif (untuk halaman kembali)
    List<TransaksiPinjam> findByTanggalKembaliIsNull();
}

