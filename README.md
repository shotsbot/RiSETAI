# LocalAI Studio

Aplikasi Android offline-first untuk chat AI lokal, editor script, manajer model GGUF/LiteRT, dan build APK lokal.

## Arsitektur
- **Android native app (Java + Material 3)**
- **JNI engine (C++)**: wrapper `llama.cpp`-style melalui `LlamaCppEngine`
- **Optional LiteRT interface**: kontrak backend Android-native
- **Room DB**: metadata model + chat history
- **Worker**: download/build berjalan background

## Fitur yang disediakan (scaffold)
### Phase 1
- Dashboard utama (`Chat AI`, `Editor Script`, `Model Manager`, `Build APK`)
- Model catalog lokal JSON + repository loader
- JNI inference engine wrapper + native stubs
- Basic build runner untuk `./gradlew assembleDebug|assembleRelease`

### Phase 2
- Parser log build untuk error utama
- Pondasi AI patch workflow (siap dihubungkan diff viewer)

### Phase 3
- Gradle runner streaming log (foreground service disarankan untuk produksi)

### Phase 4 (opsional)
- LiteRT engine plug-in

## Model catalog
Catalog awal disimpan di:
- `app/src/main/assets/models/catalog.json`

Aturan:
- URL dasar HF: `https://huggingface.co/{repo}`
- Prioritas quant: `Q4_K_M` -> `Q5_K_M` -> `Q3_K_M/IQ4`
- Hindari `F16` untuk RAM 8GB kecuali user pilih eksplisit.

## Build
```bash
./gradlew assembleDebug
```

## Menambah model baru
1. Tambahkan entry JSON baru di `catalog.json`.
2. Isi: `name, repo, format, recommended_quant, category, use_case`.
3. Simpan metadata install/download ke tabel Room `models`.

## Keamanan
- Inference utama berjalan lokal.
- Build command dibatasi ke workspace project.
- Download hanya dari repo model yang dipilih user.

## Sample project build test
Folder `sample-project/` disediakan sebagai target uji deteksi project Android Gradle.
