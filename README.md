# LocalAI Studio (Max Architecture Draft)

LocalAI Studio adalah aplikasi Android 10+ untuk menjalankan AI **100% lokal/offline setelah model terunduh** dengan 4 domain utama:
1. Chat AI lokal.
2. Editor script dengan AI assistant.
3. Model manager GGUF/LiteRT.
4. Build APK lokal via Gradle.

## Stack
- Android layer: Java + Material Design 3 + BottomNavigation.
- Inference engine: JNI C++ (`LlamaCppEngine`) dengan fallback CPU.
- Optional backend: `LiteRTEngine` interface.
- Storage: app-specific storage + Room.
- Worker: pondasi untuk background download/build.

## Modul yang tersedia saat ini
- `ui.dashboard`: status RAM, backend aktif, ringkasan fitur.
- `ui.chat`: chat bubble list + mock token streaming per karakter.
- `ui.editor`: editor basic + Explain/Generate Patch preview.
- `ui.model`: katalog model penuh RAM 8GB + warning model berat.
- `ui.build`: trigger Gradle task dan stream log.
- `engine`: JNI bridge (`llama_jni.cpp`) dan Java wrapper.
- `data`: Room entity/DAO/database.

## Arsitektur target (yang sudah dipetakan)
### Phase 1 (implemented as strong scaffold)
- Chat UI modern + stream mock.
- Model catalog JSON lengkap sesuai spesifikasi.
- Llama.cpp JNI bridge foundation.
- Basic code editor.

### Phase 2 (foundation in place)
- AI editor integration commands (Explain/Patch preview).
- Diff-first workflow (no direct overwrite).
- Context indexer hooks siap ditambah.

### Phase 3 (foundation in place)
- Gradle build runner + build log area.
- Parser error file:line untuk “Ask AI to fix”.

### Phase 4
- LiteRT backend runtime.
- Embedding/RAG lokal.
- Git panel (status/diff/commit).

## Model catalog
Katalog bawaan lengkap berada di `app/src/main/assets/models/catalog.json`.

Aturan download GGUF:
- Base URL: `https://huggingface.co/{repo}`.
- Prioritas quant: `Q4_K_M` -> `Q5_K_M` -> `Q3_K_M/IQ4`.
- Hindari `F16` untuk RAM 8 GB kecuali user memilih eksplisit.

## Build
Project root:
```bash
gradle wrapper
./gradlew assembleDebug
```

## Catatan keamanan
- Tidak ada cloud inference wajib.
- Command build dibatasi ke folder workspace user.
- Model download harus explicit by user selection.

## Sample target
`sample-project/` disertakan untuk pengujian deteksi Android Gradle project.


## Upgrade kualitas terbaru
- `ModelDownloadWorker` sekarang mendukung resume download (`Range`) untuk file model besar GGUF.
- `GradleBuildRunner` sekarang membatasi task build ke daftar aman (`tasks`, `assembleDebug`, `assembleRelease`, `clean`) dan selalu menampilkan command sebelum eksekusi.
- `BuildFragment` kini diarahkan ke sample Android project agar alur build lebih realistis untuk pengujian.
