/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dosen;

/**
 *
 * @author shahd
 */

public class dosen {

    protected String nama;
    protected String nik;
    protected String jurusan;

    dosen(String namaX, String nikX, String jurX) {
        nama = namaX;
        nik = nikX;
        jurusan = jurX;
    }

    public void view() {
        System.out.println("Nama    : " + nama);
        System.out.println("NIK     : " + nik);
        System.out.println("Jurusan : " + jurusan);
    }

}
