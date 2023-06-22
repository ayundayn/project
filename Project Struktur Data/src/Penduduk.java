class Penduduk {
    private String nama;
    private int umur;
    private String rtrw;
    private boolean sudahDiproses;

    public String getNama() {
        return nama;
    }
    public int getUmur() {
        return umur;
    }
    public String getRtrw() {
        return rtrw;
    }
    public boolean isSudahDiproses() {
        return sudahDiproses;
    }
    public void setSudahDiproses(boolean sudahDiproses) {
        this.sudahDiproses = sudahDiproses;
    }

    public Penduduk(String nama, int umur, String rtrw) {
        this.nama = nama;
        this.umur = umur;
        this.rtrw = rtrw;
        this.sudahDiproses = false;
    }
}