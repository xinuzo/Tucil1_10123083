# Tucil1_10123083
# IQ Puzzler Pro

**IQ Puzzler Pro** adalah permainan papan yang bertujuan untuk mengisi seluruh papan dengan piece (blok puzzle) yang telah tersedia.  
Setiap blok memiliki bentuk unik dan harus digunakan seluruhnya. Papan awal selalu kosong, dan setiap blok dapat **dirotasi** maupun **dicerminkan** untuk memaksimalkan kemungkinan penempatan.

## 📥 Input Format
Input program diberikan melalui berkas **.txt** dengan format berikut:

1. **Baris pertama**:  
   - Dua angka (**N** dan **M**) menentukan dimensi papan (**NxM**).  
   - Satu angka (**P**) menunjukkan banyaknya blok puzzle.  
   
2. **Baris kedua**:  
   - Sebuah **string** yang mengidentifikasi jenis kasus konfigurasi, misalnya:  
     - `DEFAULT`  
     - `CUSTOM`  
     - `PYRAMID`  

3. **Baris selanjutnya**:  
   - Representasi bentuk masing-masing blok puzzle menggunakan karakter **huruf kapital (A–Z)** untuk membedakan setiap blok.

## 📤 Output Format
Output yang dihasilkan oleh program mencakup:

- **Konfigurasi papan** yang terisi penuh oleh blok puzzle, dengan setiap blok ditandai menggunakan warna berbeda (**output berwarna**).  
- **Waktu eksekusi** pencarian solusi (dalam **milidetik**), hanya untuk proses **algoritma brute force**.  
- **Jumlah iterasi** atau kasus yang ditinjau oleh algoritma.  
- **Opsi penyimpanan** hasil output ke dalam berkas **.txt**.  

---

📌 **Catatan:** Pastikan format input sesuai agar program dapat berjalan dengan baik.


