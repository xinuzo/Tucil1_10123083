# Tucil1_10123083
# IQ Puzzler Pro

**IQ Puzzler Pro** adalah permainan papan yang bertujuan untuk mengisi seluruh papan dengan piece (blok puzzle) yang telah tersedia.  
Setiap blok memiliki bentuk unik dan harus digunakan seluruhnya. Papan awal selalu kosong, dan setiap blok dapat **dirotasi** maupun **dicerminkan** untuk memaksimalkan kemungkinan penempatan.

---

## 🔧 Instalasi & Penggunaan (Windows & Linux)
Program ini dapat dijalankan di **Windows** maupun **Linux** dengan langkah-langkah berikut:
### **🖥️ Windows**
1. **Pastikan Java terinstal** (minimal Java 8).  
   - Cek dengan perintah:  
     ```sh
     java -version
     ```
   - Jika belum terinstal, unduh Java dari: [https://www.oracle.com/java/technologies/javase-jdk11-downloads.html](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. **Download repository ini** atau clone dengan Git:  
   ```sh
   git clone https://github.com/xinuzo/Tucil1_10123083.git
3. **Ubah directory ke src**
   cd src
4. **Compile dan Run**
```
javac -d ../bin Main.java
java -cp ../bin Main
```
---
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
```
N M P
BoardType
<Piece 1>
<Piece 2>
...
<Piece P>
```

### Keterangan:
- **N** dan **M**: Dimensi papan (**NxM**).  
- **P**: Banyaknya blok puzzle.  
- **BoardType**: Jenis konfigurasi papan (contoh: `DEFAULT`, `CUSTOM`, `PYRAMID`).  
- **<Piece 1> ... <Piece P>**: Representasi masing-masing blok puzzle menggunakan huruf kapital **A–Z**.  

## 📤 Output Format
Output yang dihasilkan oleh program mencakup:

- **Konfigurasi papan** yang terisi penuh oleh blok puzzle, dengan setiap blok ditandai menggunakan warna berbeda (**output berwarna**).  
- **Waktu eksekusi** pencarian solusi (dalam **milidetik**), hanya untuk proses **algoritma brute force**.  
- **Jumlah iterasi** atau kasus yang ditinjau oleh algoritma.  
- **Opsi penyimpanan** hasil output ke dalam berkas **.txt**.

![Screenshot 2025-02-24 084759](https://github.com/user-attachments/assets/93a2c928-35cf-4b73-962b-5adb5bd79afb)
---

## ✒️ Author
**Rendi Adinata**  
📌 **NIM**: 10123083  
📌 **Kelas**: K1  


