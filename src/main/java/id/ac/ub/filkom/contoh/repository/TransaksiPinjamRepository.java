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

public interface TransaksiPinjamRepository extends JpaRepository<TransaksiPinjam, Long> {

    long countByAnggota(Anggota anggota);
}

