/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
/**
 *
 * @author 62813
 */




public class CRUD {

    static final String DB_URL = "jdbc:mysql://localhost:3306/pegawai";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    public static void main(String[] args) {

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void showMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Insert Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertData();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updateData();
                    break;
                case 4:
                    deleteData();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showData() {
        String sql = "SELECT * FROM pegawai";

        try {
            rs = stmt.executeQuery(sql);
            System.out.println("\n========= LIST DATA =========");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String alamat = rs.getString("alamat");
                String telepon = rs.getString("telepon");   
                String update_at = rs.getString("update_at");
                String delete_at = rs.getString("delete_at");
                System.out.println(String.format("%d. %s - %s - %s- %s - %s", id, nama, alamat, telepon,update_at,delete_at));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void insertData() {
        try {
            // ambil inputan
            System.out.print("Nama: ");
            String nama = input.readLine().trim();
            System.out.print("Alamat: ");
            String alamat = input.readLine().trim();
            System.out.print("Telepon: ");
            String telepon = input.readLine().trim();

            // query simpan
            String sql = "INSERT INTO pegawai (nama, alamat, telepon) VALUE('%s', '%s', '%s')";
            sql = String.format(sql, nama, alamat, telepon);

            // eksekusi simpan 
            stmt.execute(sql);
            System.out.println("Berhasil Simpan");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void updateData() {
        try {
            
            // ambil inputan
            System.out.print("Masukkan id yang ingin di edit: ");
            int id = Integer.parseInt(input.readLine());
            System.out.print("Nama: ");
            String nama = input.readLine().trim();
            System.out.print("Alamat: ");
            String alamat = input.readLine().trim();
            System.out.print("Telepon: ");
            String telepon = input.readLine().trim();
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            System.out.println(date);

            // query update
            String sql = "UPDATE pegawai SET nama='%s', alamat='%s', telepon='%s',update_at='%s' WHERE id=%d";
            sql = String.format(sql, nama, alamat, telepon,date, id);

            // eksekusi update data 
            stmt.execute(sql);
            System.out.println("Berhasil Update");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteData() {
        try {
            
            // ambil inputan
            System.out.print("Masukkan id yang ingin di hapus: ");
            int id = Integer.parseInt(input.readLine());
            
            // query hapus
            String sql = String.format("DELETE FROM pegawai WHERE id=%d", id);

            // eksekusi hapus data
            stmt.execute(sql);            
            System.out.println("Berhasil Hapus");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}