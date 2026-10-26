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
import org.springframework.data.jpa.repository.JpaRepository;

/** Akses tabel anggota. findAll(), findById(), save() sudah tersedia otomatis. */
public interface AnggotaRepository extends JpaRepository<Anggota, String> {
}
