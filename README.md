# 📝 Elegant Notes - Beautiful Kotlin Notes App

Aplikasi catatan yang elegan dan modern dibuat dengan Kotlin untuk Android dengan desain Material Design 3 yang menarik.

## ✨ Fitur-Fitur Utama

### 🎨 **Desain Modern & Menarik**
- Material Design 3 dengan tema terang dan gelap
- 7 pilihan warna pastel yang cantik untuk setiap catatan
- Animasi smooth dan responsif
- UI yang bersih dan minimalis

### 📌 **Fitur Fungsional**
- ✅ Tambah, edit, dan hapus catatan
- 📍 Pin catatan penting ke bagian atas
- 🔍 Pencarian catatan real-time
- 🎨 Pewarnaan catatan dengan 7 pilihan warna
- 🗂️ Tampilan grid dengan StaggeredLayoutManager
- 💾 Penyimpanan lokal dengan Room Database
- ⏱️ Timestamp otomatis pada setiap catatan

### 🏗️ **Arsitektur & Teknologi**
- **MVVM Architecture** - Clean dan maintainable
- **Room Database** - Penyimpanan data lokal yang robust
- **LiveData** - Reactive data updates
- **Coroutines** - Asynchronous operations
- **View Binding** - Type-safe view access
- **Material Design 3** - Latest design system

## 🎨 Palet Warna

Aplikasi ini menyediakan 7 warna pastel yang indah:
1. 🤍 Light Gray - Default
2. ❤️ Pastel Red - Merah lembut
3. 💛 Pastel Yellow - Kuning lembut
4. 💚 Pastel Mint - Hijau mint
5. 💙 Pastel Blue - Biru lembut
6. 💜 Pastel Purple - Ungu lembut
7. 🧡 Pastel Orange - Oranye lembut

## 📱 Struktur Aplikasi

```
app/
├── data/
│   ├── Note.kt              # Model data catatan
│   ├── NoteDao.kt           # Database Access Object
│   └── NoteDatabase.kt      # Room Database
├── repository/
│   └── NoteRepository.kt    # Repository pattern
├── viewmodel/
│   └── NoteViewModel.kt     # ViewModel untuk UI
├── adapter/
│   └── NoteAdapter.kt       # RecyclerView Adapter
├── MainActivity.kt          # Layar utama daftar catatan
└── AddEditNoteActivity.kt   # Layar tambah/edit catatan
```

## 🚀 Cara Menggunakan

### Instalasi
1. Buka project dengan Android Studio Arctic Fox atau yang lebih baru
2. Sync Gradle files
3. Jalankan aplikasi pada emulator atau device fisik (Min SDK: 24)

### Menggunakan Aplikasi

#### Menambah Catatan Baru
1. Tap tombol FAB (+) di pojok kanan bawah
2. Pilih warna untuk catatan Anda
3. Masukkan judul dan isi catatan
4. Tap ikon Save di toolbar

#### Mengedit Catatan
1. Tap pada catatan yang ingin diedit
2. Ubah judul, isi, atau warna
3. Tap ikon Save untuk menyimpan perubahan

#### Fitur Lainnya
- **Pin Catatan**: Long press pada catatan → Pilih "Pin"
- **Hapus Catatan**: Long press pada catatan → Pilih "Delete"
- **Cari Catatan**: Gunakan search bar di bagian atas
- **Hapus Semua**: Menu (⋮) → Delete All

## 🛠️ Teknologi yang Digunakan

- **Kotlin** - Bahasa pemrograman utama
- **Android SDK 34** - Target platform
- **Min SDK 24** (Android 7.0 Nougat)
- **Room 2.6.1** - Database persistence
- **Lifecycle 2.7.0** - ViewModel & LiveData
- **Coroutines 1.7.3** - Asynchronous programming
- **Material Components 1.11.0** - Material Design
- **RecyclerView 1.3.2** - Efficient list display
- **View Binding** - Safe view access

## 📐 Arsitektur MVVM

```
┌─────────────┐
│     View    │ (Activity/Fragment)
│  (UI Layer) │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  ViewModel  │ (Business Logic)
└──────┬──────┘
       │
       ▼
┌─────────────┐
│ Repository  │ (Data Source Abstraction)
└──────┬──────┘
       │
       ▼
┌─────────────┐
│    Room     │ (Local Database)
│  Database   │
└─────────────┘
```

## 🎯 Keunggulan Aplikasi

1. **Performa Tinggi** - Menggunakan coroutines untuk operasi database asynchronous
2. **UI Responsif** - StaggeredGridLayoutManager untuk tampilan yang menarik
3. **Data Aman** - Room Database dengan LiveData untuk reactive updates
4. **Arsitektur Clean** - MVVM pattern untuk maintainability
5. **Tema Adaptif** - Mendukung light dan dark mode
6. **User Friendly** - Interface yang intuitif dan mudah digunakan

## 📝 Fitur Mendatang (Roadmap)

- [ ] Export/Import catatan
- [ ] Backup ke cloud
- [ ] Rich text formatting
- [ ] Attach images
- [ ] Reminder/Alarm
- [ ] Categories/Tags
- [ ] Widget for home screen
- [ ] Voice-to-text

## 👨‍💻 Pengembang

Dibuat dengan ❤️ menggunakan Kotlin

## 📄 Lisensi

Project ini bersifat open source untuk pembelajaran.

---

**Selamat mencatat! 📝✨**
