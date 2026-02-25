# 📱 Panduan Lengkap — Aplikasi Note Kotlin

Panduan ini memandu Anda dari **clone repository** hingga aplikasi **berjalan di device/emulator**.

---

## ✅ Prasyarat

Pastikan software berikut sudah terinstall sebelum memulai:

| Software | Versi Minimum | Link Download |
|---|---|---|
| **Android Studio** | Arctic Fox (2020.3.1) atau lebih baru | [developer.android.com/studio](https://developer.android.com/studio) |
| **JDK** | 17 atau lebih tinggi | Sudah termasuk di Android Studio |
| **Git** | Versi terbaru | [git-scm.com](https://git-scm.com) |
| **Android SDK** | API Level 24 (Android 7.0+) | Diinstall lewat Android Studio |

---

## 🚀 Langkah 1 — Clone Repository

Buka **Terminal** (Mac/Linux) atau **Command Prompt/Git Bash** (Windows), lalu jalankan:

```bash
git clone https://github.com/Doninugraha2304/aplikasi_note_kotlin.git
```

Masuk ke folder project:

```bash
cd aplikasi_note_kotlin
```

---

## 🗂️ Langkah 2 — Buka Project di Android Studio

1. Buka **Android Studio**
2. Klik **"Open"** (atau `File → Open`)
3. Navigasi ke folder `aplikasi_note_kotlin` yang baru di-clone
4. Klik **"OK"**

> ⚠️ **Penting:** Pilih folder root project (yang berisi `build.gradle.kts`), bukan folder `app` di dalamnya.

---

## ⚙️ Langkah 3 — Sync Gradle

Setelah project terbuka, Android Studio akan mendeteksi file Gradle secara otomatis.

- Jika muncul notifikasi **"Gradle files have changed"** → klik **"Sync Now"**
- Jika tidak muncul → klik menu **`File → Sync Project with Gradle Files`**
- Tunggu hingga proses selesai (bisa 2–5 menit tergantung kecepatan internet)

Pastikan di bagian bawah layar statusnya berubah menjadi:
```
BUILD SUCCESSFUL
```

---

## 📱 Langkah 4 — Siapkan Device / Emulator

Pilih salah satu cara di bawah:

### 🖥️ Opsi A — Emulator (Virtual Device)

1. Klik menu **`Tools → Device Manager`**
2. Klik **"Create Device"**
3. Pilih tipe device (contoh: **Pixel 5**)
4. Pilih **System Image** — disarankan **API 34 (Android 14)**
5. Klik **"Finish"**
6. Jalankan emulator dengan klik tombol ▶ di samping nama device

### 📲 Opsi B — Device Fisik (HP Android)

1. Di HP Anda, buka **Pengaturan → Tentang Ponsel**
2. Ketuk **"Nomor Build"** sebanyak **7 kali** hingga muncul notifikasi "Developer options enabled"
3. Kembali ke **Pengaturan → Opsi Pengembang**
4. Aktifkan **"USB Debugging"**
5. Sambungkan HP ke komputer via kabel USB
6. Pilih **"Allow"** / **"Izinkan"** pada dialog yang muncul di HP

---

## ▶️ Langkah 5 — Jalankan Aplikasi

1. Pastikan device/emulator sudah terdeteksi di **toolbar dropdown** Android Studio
2. Klik tombol **Run** (**ikon Play hijau** ▶) atau tekan `Shift + F10`
3. Tunggu proses build selesai
4. Aplikasi akan otomatis terbuka di device/emulator Anda

---

## 🐛 Troubleshooting

### ❌ Gradle Sync Gagal
```
File → Invalidate Caches → Invalidate and Restart
```
Atau coba:
```
Build → Clean Project → Rebuild Project
```

### ❌ Device Tidak Terdeteksi
- Pastikan **USB Debugging** aktif di HP
- Coba ganti kabel USB
- Install **USB Driver** dari produsen HP (khusus Windows)
- Restart Android Studio

### ❌ Build Error "SDK not found"
1. Buka `File → Project Structure → SDK Location`
2. Set path Android SDK yang benar
3. Klik OK → Sync ulang

### ❌ Emulator Lambat / Tidak Muncul
- Aktifkan **Hardware Acceleration (HAXM)** di BIOS
- Cold Boot emulator: `Device Manager → ⋮ → Cold Boot Now`
- Kurangi RAM alokasi emulator jika device kurang RAM

---

## 📁 Struktur Project

```
aplikasi_note_kotlin/
├── app/
│   └── src/main/
│       ├── java/com/noteapp/elegant/
│       │   ├── data/                  # Room Database & Model
│       │   ├── repository/            # Data Repository
│       │   ├── viewmodel/             # ViewModel (MVVM)
│       │   ├── adapter/               # RecyclerView Adapter
│       │   ├── MainActivity.kt        # Layar utama (daftar catatan)
│       │   └── AddEditNoteActivity.kt # Layar tambah/edit catatan
│       └── res/                       # Layout, warna, strings
├── build.gradle.kts                   # Konfigurasi build root
└── settings.gradle.kts
```

---

## 🎨 Fitur Utama

| Fitur | Cara Pakai |
|---|---|
| ➕ Buat catatan | Tap tombol **+** di pojok kanan bawah |
| 📌 Pin catatan | Long press catatan → pilih **Pin** |
| 🔍 Cari catatan | Tap ikon **Search** di toolbar |
| 🎨 Ganti warna | Buka catatan → pilih warna dari palette |
| ✏️ Edit catatan | Tap pada catatan |
| 🗑️ Hapus catatan | Long press catatan → pilih **Delete** |

---

## 🆘 Butuh Bantuan?

Jika masih mengalami masalah setelah mencoba langkah troubleshooting:
1. Cek **Logcat** di Android Studio untuk melihat error detail
2. Buka **Issues** di repository GitHub ini
3. Sertakan screenshot error dan versi Android Studio yang digunakan
