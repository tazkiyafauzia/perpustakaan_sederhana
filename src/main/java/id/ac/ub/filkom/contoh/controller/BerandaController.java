/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.ub.filkom.contoh.controller;

/**
 *
 * @author ASUS
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BerandaController {

    // GET http://localhost:8080/
    @GetMapping("/")
    public String beranda() {
        return "beranda"; // -> templates/beranda.html
    }
}
