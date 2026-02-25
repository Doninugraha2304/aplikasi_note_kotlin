# 🚀 CARA INSTALL APLIKASI KE EMULATOR

## ✅ Emulator Sudah Running!
Status: **emulator-5554** - Ready! ✓

---

## 📱 CARA 1: Install dari Android Studio (TERMUDAH & DISARANKAN)

### Step-by-step:

**1. Di Android Studio yang sudah terbuka:**
   ```
   ✅ Pastikan Gradle sync sudah selesai (cek progress bar di bawah)
   ```

**2. Pilih Device:**
   ```
   ✅ Lihat di toolbar atas, ada dropdown device
   ✅ Pilih "emulator-5554" atau nama emulator Anda
   ```

**3. Klik Run:**
   ```
   ✅ Klik tombol ▶️ (Run) di toolbar
   ✅ Atau tekan: Shift + F10 (Mac)
   ```

**4. Tunggu Build:**
   ```
   ✅ Pertama kali: 2-3 menit
   ✅ Lihat progress di bagian bawah: "Building..."
   ✅ Setelah selesai, aplikasi otomatis install & buka!
   ```

---

## 🎯 CARA 2: Dari Terminal (Jika Android Studio Build Berhasil)

Jika sudah pernah build dari Android Studio dan APK sudah ada:

```bash
cd /Users/josjaya/kotlin_mobile

# Install APK yang sudah di-build
~/Library/Android/sdk/platform-tools/adb install -r app/build/outputs/apk/debug/app-debug.apk

# Buka aplikasinya
~/Library/Android/sdk/platform-tools/adb shell am start -n com.noteapp.elegant/.MainActivity
```

---

## 🔧 CARA 3: Build & Install Manual (Advanced)

Dari Android Studio, bisa juga:

**Build APK:**
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

**Setelah build selesai:**
```
Klik "locate" pada notifikasi
Drag APK ke emulator window
```

---

## ⚡ QUICK START (PALING MUDAH):

```
1. ✅ Emulator sudah running? YA!
2. ✅ Android Studio sudah buka? YA!
3. ⏳ Gradle sync selesai? Tunggu jika belum...
4. ▶️  Klik tombol RUN atau tekan Shift + F10
5. 🎉 Aplikasi otomatis install & buka!
```

---

## 🐛 Troubleshooting:

### Jika Tombol Run Disabled:
- Tunggu Gradle sync selesai dulu
- Pastikan tidak ada error di Build panel

### Jika Build Error di Android Studio:
```
Build → Clean Project
Build → Rebuild Project
```

### Jika Emulator Tidak Muncul di Dropdown:
```
File → Sync Project with Gradle Files
Restart Android Studio
```

### Cek Device Terhubung:
```bash
~/Library/Android/sdk/platform-tools/adb devices
# Should show: emulator-5554   device
```

---

## 🎨 SETELAH APLIKASI RUNNING:

Anda akan melihat:
- ✅ **Home Screen** dengan search bar
- ✅ **FAB Button (+)** warna ungu di kanan bawah
- ✅ **Empty State** "No notes yet"

**Coba Fitur:**
1. Tap **+** untuk buat note baru
2. Pilih **warna pastel** favorit (7 pilihan)
3. Tulis **Title** dan **Content**
4. Tap **Save** (icon di toolbar)
5. Note muncul di home screen! 🎉

**Fitur Lain:**
- Long press note → Pin/Delete
- Tap note untuk edit
- Search bar untuk cari note
- Warna berbeda untuk kategorisasi

---

## 🚀 RECOMMENDED: Pakai Android Studio!

Cara paling smooth dan mudah:
```
Android Studio → Pastikan sync selesai → Pilih emulator → Klik Run (▶️)
```

Build pertama memang agak lama (2-3 menit), tapi setelah itu
rebuild cuma 30-60 detik!

**Selamat mencoba aplikasi Elegant Notes! 📝✨**
