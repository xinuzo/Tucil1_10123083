# Image Compression menggunakan Quadtree

## Deskripsi
Proyek Image Compression ini menggunakan struktur data Quadtree untuk kompresi gambar dengan pendekatan divide and conquer; gambar awal dibagi secara rekursif menjadi empat kuadran dan setiap blok dihitung nilai error berdasarkan analisis sistem warna RGB (misalnya, variansi, MAD, dsb.). Jika error pada blok melebihi threshold, blok akan dipecah lebih lanjut hingga mencapai tingkat keseragaman yang diinginkan atau ukuran minimum, setelah itu blok-blok yang tidak terpecah direpresentasikan dengan warna rata-rata dan disusun kembali membentuk gambar terkompresi yang lebih efisien, dengan output berupa gambar terkompresi serta animasi GIF yang menunjukkan progresi kompresi.


## Instalasi & Penggunaan

### Persyaratan
- Java Development Kit (JDK) minimal versi 8.
- Pastikan variabel lingkungan (`JAVA_HOME`) telah diatur dan perintah `javac` serta `java` tersedia pada `PATH`.

### Langkah-langkah
1. **Download atau Clone Repository:**  
   Tempatkan seluruh file `.java` dalam satu direktori.

2. **Kompilasi:**  
   Buka terminal atau command prompt, arahkan ke direktori proyek, dan jalankan:
   ```sh
   javac *.java
