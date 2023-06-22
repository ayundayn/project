import java.util.*;

class pendataanMasyarakat {
    private List<Penduduk> daftarPenduduk;
    private Queue<Penduduk> antrianPenduduk;
    private Scanner scanner;

    public pendataanMasyarakat() {
        daftarPenduduk = new ArrayList<>();
        antrianPenduduk = new LinkedList<>();
        scanner = new Scanner(System.in);

        Penduduk[] pendudukBaru = {
                new Penduduk("Ayunda", 19, "003/002"),
                new Penduduk("Tara", 25, "002/001"),
                new Penduduk("Siti", 32, "001/002")
        };

        for (Penduduk penduduk : pendudukBaru) {
            penduduk.setSudahDiproses(true);
            daftarPenduduk.add(penduduk);
        }
    }

    private void tambahPenduduk() {
        System.out.print("\nNama: ");
        String nama = scanner.nextLine();
        System.out.print("Umur: ");
        int umur = scanner.nextInt();
        scanner.nextLine();
        System.out.print("RT/RW: ");
        String rtrw = scanner.nextLine();

        Penduduk penduduk = new Penduduk(nama, umur, rtrw);
        antrianPenduduk.offer(penduduk);
        penduduk.setSudahDiproses(true);
        daftarPenduduk.add(penduduk);
        System.out.println("Penduduk baru berhasil masuk dalam antrian.");
        System.out.println();
    }

    private void prosesAntrianPenduduk() {
        if (antrianPenduduk.isEmpty()) {
            System.out.println("Antrian penduduk kosong.");
        } else {
            Penduduk penduduk = antrianPenduduk.poll();
            penduduk.setSudahDiproses(true);
            System.out.println("Penduduk dengan nama " + penduduk.getNama() + " sedang diproses.");
        }
        System.out.println();
    }

    private void tampilkanDataPenduduk() {
        if (daftarPenduduk.isEmpty()) {
            System.out.println("Belum ada data penduduk.");
        } else {
            System.out.println("=== Data Penduduk ===");
            for (Penduduk p : daftarPenduduk) {
                if (p.isSudahDiproses()) {
                    System.out.println("Nama: " + p.getNama());
                    System.out.println("Umur: " + p.getUmur());
                    System.out.println("RT/RW: " + p.getRtrw());
                    System.out.println("-----------------------");
                }
            }
        }
        System.out.println();
    }

    private void cariPenduduk() {
        System.out.print("Masukkan kata kunci: ");
        String kataKunci = scanner.nextLine();
        List<Penduduk> hasilPencarian = cariPendudukByKataKunci(kataKunci);
        if (hasilPencarian.isEmpty()) {
            System.out.println("Tidak ditemukan penduduk dengan kata kunci tersebut.");
        } else {
            System.out.println("=== Hasil Pencarian ===");
            for (Penduduk p : hasilPencarian) {
                System.out.println("Nama: " + p.getNama());
                System.out.println("Umur: " + p.getUmur());
                System.out.println("RT/RW: " + p.getRtrw());
                System.out.println("-----------------------");
            }
        }
        System.out.println();
    }

    private List<Penduduk> cariPendudukByKataKunci(String kataKunci) {
        List<Penduduk> hasilPencarian = new ArrayList<>();
        for (Penduduk p : daftarPenduduk) {
            if (p.getNama().toLowerCase().contains(kataKunci.toLowerCase())) {
                hasilPencarian.add(p);
            }
        }
        return hasilPencarian;
    }

    private void urutkanDataPenduduk() {
        System.out.println("=== Urutkan Data Penduduk ===");
        System.out.println("1. Urutkan berdasarkan Nama (A-Z)");
        System.out.println("2. Urutkan berdasarkan Umur (tertinggi-terendah)");
        System.out.print("Pilihan: ");
        int pilihanUrutan = scanner.nextInt();
        if (pilihanUrutan == 1) {
            Collections.sort(daftarPenduduk, Comparator.comparing(Penduduk::getNama));
            System.out.println("Data Penduduk berhasil diurutkan berdasarkan Nama.");
        } else if (pilihanUrutan == 2) {
            bubbleSortByUmur();
            System.out.println("Data Penduduk berhasil diurutkan berdasarkan Umur.");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
        System.out.println();
    }

    private void bubbleSortByUmur() {
        int n = daftarPenduduk.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (daftarPenduduk.get(j).getUmur() < daftarPenduduk.get(j + 1).getUmur()) {
                    Penduduk temp = daftarPenduduk.get(j);
                    daftarPenduduk.set(j, daftarPenduduk.get(j + 1));
                    daftarPenduduk.set(j + 1, temp);
                }
            }
        }
    }

    public void menu() {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("=== Sistem Pendataan Masyarakat ===");
            System.out.println("1. Tambah Penduduk");
            System.out.println("2. Proses Antrian Penduduk");
            System.out.println("3. Tampilkan Data Penduduk");
            System.out.println("4. Cari Penduduk");
            System.out.println("5. Urutkan Data Penduduk");
            System.out.println("6. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    tambahPenduduk();
                    break;
                case 2:
                    prosesAntrianPenduduk();
                    break;
                case 3:
                    tampilkanDataPenduduk();
                    break;
                case 4:
                    cariPenduduk();
                    break;
                case 5:
                    urutkanDataPenduduk();
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    System.out.println();
                    break;
            }
        }
        input.close();
    }
}