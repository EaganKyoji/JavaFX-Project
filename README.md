# Indodax Tickers Viewer

Aplikasi JavaFX untuk menampilkan data ticker dari database Indodax dengan pagination.

## Requirements

- Java 21+
- JavaFX SDK 21 — download di https://gluonhq.com/products/javafx/
- MySQL Connector/J — download di https://dev.mysql.com/downloads/connector/j/
- MySQL Server berjalan di localhost:3306

## Setup

1. Clone repo ini
   git clone https://github.com/EaganKyoji/JavaFX-Project.git

2. Copy `.env.example` jadi `.env`
   cp .env.example .env

3. Isi `.env` dengan kredensial database kamu

4. Di VS Code, buka `Java Projects` → `Referenced Libraries` → tambahkan JAR:
   - Semua JAR dari folder instalasi JavaFX
   - mysql-connector-j.jar

5. Run `Main.java`

## Struktur Folder

src/Indodax/
├── Main.java                        ← entry point
├── IndodaxPaginationController.java ← logika pagination & koneksi DB
├── IndodaxPagination.fxml           ← layout UI
└── SqlConnector.java                ← koneksi database
