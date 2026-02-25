# 🚀 Panduan Memulai Aplikasi Elegant Notes

## 📋 Prasyarat

Sebelum menjalankan aplikasi, pastikan Anda sudah menginstall:
- **Android Studio** (Arctic Fox atau lebih baru)
- **JDK 17** atau lebih tinggi
- **Android SDK** dengan minimum API Level 24 (Android 7.0)

## 🔧 Langkah-langkah Instalasi

### 1. Buka Project di Android Studio

```bash
- Buka Android Studio
- Pilih "Open an Existing Project"
- Navigasi ke folder "kotlin_mobile"
- Klik "OK"
```

### 2. Sync Gradle

Setelah project terbuka:
```
- Android Studio akan otomatis mendeteksi Gradle files
- Klik "Sync Now" jika muncul notifikasi
- Tunggu hingga proses sync selesai (bisa memakan waktu beberapa menit)
```

### 3. Setup Android Emulator atau Device

#### Menggunakan Emulator:
```
- Buka AVD Manager: Tools → AVD Manager
- Klik "Create Virtual Device"
- Pilih device (misal: Pixel 5)
- Pilih system image (minimal API 24, disarankan API 34)
- Klik "Finish"
```

#### Menggunakan Device Fisik:
```
- Enable Developer Options di Android device Anda
- Enable USB Debugging
- Sambungkan device ke komputer via USB
- Pilih "Always allow from this computer"
```

### 4. Run Aplikasi

```
- Pilih device/emulator dari dropdown di toolbar
- Klik tombol Run (ikon Play hijau) atau tekan Shift + F10
- Tunggu build selesai dan aplikasi akan otomatis terbuka
```

## 🎨 Fitur-Fitur yang Bisa Dicoba

### ✅ Membuat Catatan Pertama
1. Tap tombol **+** (FAB) di pojok kanan bawah
2. Pilih warna favorit Anda dari 7 pilihan warna pastel
3. Masukkan judul catatan (contoh: "Daftar Belanja")
4. Tulis isi catatan
5. Tap ikon **Save** di toolbar

### 📌 Pin Catatan Penting
1. Long press pada catatan yang ingin di-pin
2. Pilih **"Pin"**
3. Catatan akan berpindah ke bagian paling atas

### 🔍 Mencari Catatan
1. Tap ikon **Search** di toolbar
2. Ketik kata kunci
3. Hasil pencarian akan muncul secara real-time

### 🎨 Mengganti Warna Catatan
1. Tap catatan untuk mengedit
2. Pilih warna baru dari color palette
3. Tap **Save**

### 🗑️ Menghapus Catatan
1. Long press pada catatan
2. Pilih **"Delete"**
3. Konfirmasi penghapusan

## 🐛 Troubleshooting

### Build Error
Jika terjadi build error:
```
- File → Invalidate Caches → Invalidate and Restart
- Clean Project: Build → Clean Project
- Rebuild Project: Build → Rebuild Project
```

### Gradle Sync Failed
```
- Pastikan koneksi internet stabil
- Coba sync lagi: File → Sync Project with Gradle Files
- Update Gradle: File → Project Structure → Project → Gradle Version
```

### Emulator Tidak Muncul
```
- Restart Android Studio
- Cold Boot emulator: AVD Manager → Actions → Cold Boot Now
- Buat emulator baru jika masih bermasalah
```

## 📁 Struktur Project

```
kotlin_mobile/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/noteapp/elegant/
│   │       │   ├── data/           # Database & Models
│   │       │   ├── repository/     # Data Repository
│   │       │   ├── viewmodel/      # ViewModels
│   │       │   ├── adapter/        # RecyclerView Adapters
│   │       │   ├── MainActivity.kt
│   │       │   └── AddEditNoteActivity.kt
│   │       └── res/                # Resources (layouts, colors, etc)
│   └── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```

## 🎯 File-File Penting

| File | Deskripsi |
|------|-----------|
| `MainActivity.kt` | Layar utama menampilkan daftar catatan |
| `AddEditNoteActivity.kt` | Layar untuk menambah/edit catatan |
| `Note.kt` | Model data catatan |
| `NoteDatabase.kt` | Konfigurasi Room Database |
| `NoteViewModel.kt` | Business logic dan data handling |
| `NoteAdapter.kt` | Adapter untuk RecyclerView |

## 💡 Tips Penggunaan

1. **Multi-Color Notes**: Gunakan warna berbeda untuk kategori catatan
   - Merah: Urgent/Penting
   - Kuning: Reminder
   - Hijau: Ideas
   - Biru: To-Do Lists
   
2. **Pin Important Notes**: Pin catatan yang sering diakses

3. **Search Efficiently**: Gunakan search untuk menemukan catatan cepat

4. **Regular Backup**: Catatan tersimpan lokal, jadi backup device secara berkala

## 🆘 Butuh Bantuan?

Jika mengalami masalah:
1. Cek file README.md untuk informasi lengkap
2. Pastikan semua dependencies sudah ter-download
3. Periksa Android Studio Logcat untuk error messages

## ✨ Selamat Mencoba!

Aplikasi Elegant Notes siap digunakan! Buat catatan sebanyak yang Anda mau dengan desain yang cantik dan modern.

**Happy Coding! 🚀📝**
