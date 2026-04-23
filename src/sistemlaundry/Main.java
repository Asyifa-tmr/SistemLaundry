package sistemlaundry;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class Main extends JFrame {
    private JTextField txtNama;
    private JTextField txtTelepon;
    private JTextField txtBerat;
    private JComboBox<String> cmbLayanan;
    private JComboBox<String> cmbPembayaran;
    private JLabel lblTarif;
    private JLabel lblEstimasi;
    private JPanel panelStruk;
    private JLabel lblStrukNomor;
    private JLabel lblStrukNama;
    private JLabel lblStrukTelepon;
    private JLabel lblStrukLayanan;
    private JLabel lblStrukBerat;
    private JLabel lblStrukTarif;
    private JLabel lblStrukEstimasi;
    private JLabel lblStrukPembayaran;
    private JLabel lblStrukMasuk;
    private JLabel lblStrukSelesai;
    private JLabel lblStrukTotal;
    private JTextArea txtRiwayat;
    private int jumlahOrder = 0;

    private JButton btnProses;
    private JButton btnReset;
    private final Color PINK_TUA    = new Color(136, 14, 79);
    private final Color PINK_MID    = new Color(194, 24, 91);
    private final Color PINK_MUDA   = new Color(252, 228, 236);
    private final Color PUTIH       = Color.WHITE;
    private final Color BG_MUDA     = new Color(255, 243, 247);
    private final Color PINK_BORDER = new Color(240, 180, 200);
    private final Color PINK_GELAP  = new Color(136, 14, 79);
    private final Color PINK_BG     = new Color(255, 235, 242);
    private final Color PINK_HARGA  = new Color(173, 20, 87);
    private final Color PINK_CERAH  = new Color(216, 27, 96);

    public Main() {
        setTitle("Sistem Layanan Laundry - UTS PBO2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 780);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG_MUDA);
        initComponents();
        updateInfoLayanan();
    }
    private void initComponents() {
        setLayout(new BorderLayout(0, 0));
        add(buatHeader(), BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabs.setBackground(BG_MUDA);
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBackground(BG_MUDA);
        panelForm.setBorder(new EmptyBorder(15, 20, 15, 20));
        panelForm.add(buatPanelInput());
        panelForm.add(Box.createVerticalStrut(12));
        panelForm.add(buatPanelTombol());
        panelForm.add(Box.createVerticalStrut(12));
        panelForm.add(buatPanelStruk());

        JScrollPane scrollForm = new JScrollPane(panelForm);
        scrollForm.setBorder(null);
        scrollForm.getVerticalScrollBar().setUnitIncrement(16);
        JPanel panelRiwayat = buatPanelRiwayat();

        tabs.addTab("Form Pesanan", scrollForm);
        tabs.addTab("Riwayat Pesanan", panelRiwayat);

        add(tabs, BorderLayout.CENTER);
        add(buatFooter(), BorderLayout.SOUTH);
    }
    private JPanel buatHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PINK_TUA);
        panel.setBorder(new EmptyBorder(18, 25, 18, 25));

        JLabel lblJudul = new JLabel("Sistem Layanan Laundry");
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblJudul.setForeground(PUTIH);

        JLabel lblSub = new JLabel("UTS Pemrograman Berorientasi Objek 2  |  TIF RP 24E CNS");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSub.setForeground(new Color(255, 192, 213));

        panel.add(lblJudul, BorderLayout.CENTER);
        panel.add(lblSub,   BorderLayout.SOUTH);
        return panel;
    }
    private JPanel buatPanelInput() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PUTIH);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(PINK_BORDER, 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);

        JLabel lblSeksi = new JLabel("Data Pesanan");
        lblSeksi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSeksi.setForeground(PINK_TUA);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblSeksi, gbc);

        JSeparator sep = new JSeparator();
        sep.setForeground(PINK_BORDER);
        gbc.gridy = 1; gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(sep, gbc);
        gbc.insets = new Insets(6, 0, 6, 0);

        gbc.gridwidth = 1; gbc.gridy = 2; gbc.gridx = 0; gbc.weightx = 0.38;
        panel.add(buatLabel("Nama Pelanggan :"), gbc);
        txtNama = buatTextField("Contoh: Angel amalia");
        gbc.gridx = 1; gbc.weightx = 0.62;
        panel.add(txtNama, gbc);

        gbc.gridy = 3; gbc.gridx = 0; gbc.weightx = 0.38;
        panel.add(buatLabel("No. Telepon :"), gbc);
        JPanel panelTelp = new JPanel(new BorderLayout(5, 0));
        panelTelp.setOpaque(false);
        txtTelepon = buatTextField("Contoh: 08123456789");
        JButton btnSalin = new JButton("");
        btnSalin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnSalin.setBackground(PINK_MUDA);
        btnSalin.setForeground(PINK_TUA);
        btnSalin.setBorderPainted(false);
        btnSalin.setFocusPainted(false);
        btnSalin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalin.setToolTipText("Salin nomor telepon");
        btnSalin.addActionListener(e -> salinNomorTelepon());
        panelTelp.add(txtTelepon, BorderLayout.CENTER);
        panelTelp.add(btnSalin,   BorderLayout.EAST);
        gbc.gridx = 1; gbc.weightx = 0.62;
        panel.add(panelTelp, gbc);

        gbc.gridy = 4; gbc.gridx = 0; gbc.weightx = 0.38;
        panel.add(buatLabel("Berat Cucian (kg) :"), gbc);
        txtBerat = buatTextField("Contoh: 4.5");
        gbc.gridx = 1; gbc.weightx = 0.62;
        panel.add(txtBerat, gbc);

        gbc.gridy = 5; gbc.gridx = 0; gbc.weightx = 0.38;
        panel.add(buatLabel("Jenis Layanan :"), gbc);
        String[] layananList = {"Regular", "Express", "Dry Clean"};
        cmbLayanan = new JComboBox<>(layananList);
        cmbLayanan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbLayanan.setBackground(PUTIH);
        cmbLayanan.setPreferredSize(new Dimension(0, 35));
        cmbLayanan.addActionListener(e -> updateInfoLayanan());
        gbc.gridx = 1; gbc.weightx = 0.62;
        panel.add(cmbLayanan, gbc);

        gbc.gridy = 6; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(buatPanelInfo(), gbc);
        gbc.gridy = 7; gbc.gridwidth = 1; gbc.gridx = 0; gbc.weightx = 0.38;
        panel.add(buatLabel("Jenis Pembayaran :"), gbc);
        String[] pembayaranList = {"Cash", "QRIS", "Transfer Bank"};
        cmbPembayaran = new JComboBox<>(pembayaranList);
        cmbPembayaran.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbPembayaran.setBackground(PUTIH);
        cmbPembayaran.setPreferredSize(new Dimension(0, 35));
        gbc.gridx = 1; gbc.weightx = 0.62;
        panel.add(cmbPembayaran, gbc);
        gbc.gridy = 8; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(buatPanelCatatanPembayaran(), gbc);

        return panel;
    }
    private JPanel buatPanelInfo() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
        panel.setBackground(PINK_MUDA);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(PINK_BORDER, 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));

        JPanel kiri = new JPanel(new BorderLayout(0, 3));
        kiri.setOpaque(false);
        JLabel lblTarifJudul = new JLabel("Tarif");
        lblTarifJudul.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTarifJudul.setForeground(PINK_TUA);
        lblTarif = new JLabel("Rp 5.000/kg");
        lblTarif.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTarif.setForeground(PINK_MID);
        kiri.add(lblTarifJudul, BorderLayout.NORTH);
        kiri.add(lblTarif,      BorderLayout.CENTER);

        JPanel kanan = new JPanel(new BorderLayout(0, 3));
        kanan.setOpaque(false);
        JLabel lblEstJudul = new JLabel("Estimasi Selesai");
        lblEstJudul.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEstJudul.setForeground(PINK_TUA);
        lblEstimasi = new JLabel("2-3 Hari");
        lblEstimasi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEstimasi.setForeground(PINK_CERAH);
        kanan.add(lblEstJudul,  BorderLayout.NORTH);
        kanan.add(lblEstimasi,  BorderLayout.CENTER);

        panel.add(kiri);
        panel.add(kanan);
        return panel;
    }
    private JPanel buatPanelCatatanPembayaran() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 248, 225));
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(255, 213, 79), 1, true),
            new EmptyBorder(8, 12, 8, 12)
        ));

        JLabel lbl = new JLabel("<html> <b>Info Pembayaran:</b><br>" +
            "• <b>Cash</b> — Bayar langsung di tempat<br>" +
            "• <b>QRIS</b> — Scan QR saat pengambilan<br>" +
            "• <b>Transfer</b> — BCA 12346675340 Laundryku</html>");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(new Color(100, 70, 0));
        panel.add(lbl);
        return panel;
    }
    private JPanel buatPanelTombol() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 12, 0));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        btnProses = new JButton("Proses Pesanan");
        btnProses.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnProses.setBackground(PINK_TUA);
        btnProses.setForeground(PUTIH);
        btnProses.setFocusPainted(false);
        btnProses.setBorderPainted(false);
        btnProses.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnProses.addActionListener(e -> prosesPesanan());

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnReset.setBackground(PINK_MID);
        btnReset.setForeground(PUTIH);
        btnReset.setFocusPainted(false);
        btnReset.setBorderPainted(false);
        btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReset.addActionListener(e -> resetForm());

        btnProses.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnProses.setBackground(PINK_MID); }
            public void mouseExited(MouseEvent e)  { btnProses.setBackground(PINK_TUA); }
        });
        btnReset.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnReset.setBackground(PINK_TUA); }
            public void mouseExited(MouseEvent e)  { btnReset.setBackground(PINK_MID); }
        });

        panel.add(btnProses);
        panel.add(btnReset);
        return panel;
    }
    private JPanel buatPanelStruk() {
        panelStruk = new JPanel(new GridBagLayout());
        panelStruk.setBackground(PINK_BG);
        panelStruk.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(PINK_BORDER, 1, true),
            new EmptyBorder(18, 20, 18, 20)
        ));
        panelStruk.setVisible(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);

        JLabel lblJudulStruk = new JLabel("STRUK PEMBAYARAN", SwingConstants.CENTER);
        lblJudulStruk.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblJudulStruk.setForeground(PINK_GELAP);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelStruk.add(lblJudulStruk, gbc);

        JSeparator sep = new JSeparator();
        sep.setForeground(PINK_BORDER);
        gbc.gridy = 1; gbc.insets = new Insets(2, 0, 10, 0);
        panelStruk.add(sep, gbc);
        gbc.insets = new Insets(4, 0, 4, 0);

        lblStrukNomor      = new JLabel();
        lblStrukNama       = new JLabel();
        lblStrukTelepon    = new JLabel();
        lblStrukLayanan    = new JLabel();
        lblStrukBerat      = new JLabel();
        lblStrukTarif      = new JLabel();
        lblStrukEstimasi   = new JLabel();
        lblStrukPembayaran = new JLabel();
        lblStrukMasuk      = new JLabel();
        lblStrukSelesai    = new JLabel();
        lblStrukTotal      = new JLabel();

        gbc.gridwidth = 1; gbc.weightx = 0.45;
        int b = 2;
        b = baris(panelStruk, gbc, b, "No. Order",        lblStrukNomor,      false);
        b = baris(panelStruk, gbc, b, "Nama Pelanggan",   lblStrukNama,       false);
        b = baris(panelStruk, gbc, b, "No. Telepon",      lblStrukTelepon,    false);
        b = baris(panelStruk, gbc, b, "Jenis Layanan",    lblStrukLayanan,    false);
        b = baris(panelStruk, gbc, b, "Berat Cucian",     lblStrukBerat,      false);
        b = baris(panelStruk, gbc, b, "Tarif",            lblStrukTarif,      false);
        b = baris(panelStruk, gbc, b, "Estimasi Selesai", lblStrukEstimasi,   false);
        b = baris(panelStruk, gbc, b, "Tgl. Masuk",       lblStrukMasuk,      false);
        b = baris(panelStruk, gbc, b, "Tgl. Selesai",     lblStrukSelesai,    false);
        b = baris(panelStruk, gbc, b, "Pembayaran",       lblStrukPembayaran, false);

        gbc.gridx = 0; gbc.gridy = b; gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 0, 8, 0);
        JSeparator sep2 = new JSeparator();
        sep2.setForeground(PINK_BORDER);
        panelStruk.add(sep2, gbc);
        gbc.insets = new Insets(4, 0, 4, 0);
        b++;

        baris(panelStruk, gbc, b, "TOTAL HARGA", lblStrukTotal, true);
        lblStrukTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblStrukTotal.setForeground(PINK_HARGA);
        
        b += 2;
        JButton btnHubungi = new JButton("Salin No. Telepon Pelanggan");
        btnHubungi.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnHubungi.setBackground(PINK_TUA);
        btnHubungi.setForeground(PUTIH);
        btnHubungi.setFocusPainted(false);
        btnHubungi.setBorderPainted(false);
        btnHubungi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHubungi.addActionListener(e -> salinNomorTelepon());
        gbc.gridx = 0; gbc.gridy = b; gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 0, 0, 0);
        panelStruk.add(btnHubungi, gbc);

        return panelStruk;
    }

    private int baris(JPanel panel, GridBagConstraints gbc,
                      int baris, String judul, JLabel lblNilai, boolean bold) {
        JLabel lblJudul = new JLabel(judul + " :");
        lblJudul.setFont(new Font("Segoe UI", bold ? Font.BOLD : Font.PLAIN, 13));
        lblJudul.setForeground(bold ? PINK_GELAP : new Color(80, 80, 80));

        lblNilai.setFont(new Font("Segoe UI", Font.BOLD, bold ? 16 : 13));
        lblNilai.setForeground(bold ? PINK_HARGA : new Color(30, 30, 30));

        gbc.gridx = 0; gbc.gridy = baris; gbc.gridwidth = 1; gbc.weightx = 0.45;
        panel.add(lblJudul, gbc);
        gbc.gridx = 1; gbc.weightx = 0.55;
        panel.add(lblNilai, gbc);
        return baris + 1;
    }
    private JPanel buatPanelRiwayat() {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(BG_MUDA);
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel lblJudul = new JLabel("Riwayat Pesanan");
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblJudul.setForeground(PINK_TUA);
        lblJudul.setBorder(new EmptyBorder(0, 0, 10, 0));
        panel.add(lblJudul, BorderLayout.NORTH);

        txtRiwayat = new JTextArea();
        txtRiwayat.setEditable(false);
        txtRiwayat.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtRiwayat.setBackground(PUTIH);
        txtRiwayat.setForeground(new Color(50, 50, 50));
        txtRiwayat.setBorder(new EmptyBorder(10, 10, 10, 10));
        txtRiwayat.setText("Belum ada pesanan yang diproses.");

        JScrollPane scrollRiwayat = new JScrollPane(txtRiwayat);
        scrollRiwayat.setBorder(new LineBorder(PINK_BORDER, 1, true));
        panel.add(scrollRiwayat, BorderLayout.CENTER);

        JButton btnHapus = new JButton("Hapus Riwayat");
        btnHapus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnHapus.setBackground(PINK_MID);
        btnHapus.setForeground(PUTIH);
        btnHapus.setFocusPainted(false);
        btnHapus.setBorderPainted(false);
        btnHapus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHapus.addActionListener(e -> {
            txtRiwayat.setText("Belum ada pesanan yang diproses.");
            jumlahOrder = 0;
        });

        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBawah.setOpaque(false);
        panelBawah.setBorder(new EmptyBorder(10, 0, 0, 0));
        panelBawah.add(btnHapus);
        panel.add(panelBawah, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel buatFooter() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(PINK_MUDA);
        panel.setBorder(new MatteBorder(1, 0, 0, 0, PINK_BORDER));

        JLabel lbl = new JLabel("Strategy Pattern  •  Factory Pattern  •  SOLID Principles  |  OOP 2 © 2026");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lbl.setForeground(PINK_HARGA);
        panel.add(lbl);
        return panel;
    }
    private JLabel buatLabel(String teks) {
        JLabel lbl = new JLabel(teks);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(new Color(60, 60, 60));
        return lbl;
    }

    private JTextField buatTextField(String placeholder) {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tf.setPreferredSize(new Dimension(0, 35));
        tf.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(PINK_BORDER, 1, true),
            new EmptyBorder(0, 8, 0, 8)
        ));
        tf.setForeground(Color.GRAY);
        tf.setText(placeholder);
        tf.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tf.getText().equals(placeholder)) {
                    tf.setText("");
                    tf.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (tf.getText().isEmpty()) {
                    tf.setForeground(Color.GRAY);
                    tf.setText(placeholder);
                }
            }
        });
        return tf;
    }
    private void updateInfoLayanan() {
        String pilihan = (String) cmbLayanan.getSelectedItem();
        LaundryStrategy strategy = LaundryFactory.createService(pilihan);
        lblTarif.setText("Rp " + String.format("%,d", strategy.getTarifPerKg()).replace(",", ".") + "/kg");
        lblEstimasi.setText(strategy.getEstimasiWaktu());
    }
    private void salinNomorTelepon() {
        String noTelp = txtTelepon.getText().trim();
        if (noTelp.isEmpty() || noTelp.equals("Contoh: 08123456789")) {
            JOptionPane.showMessageDialog(this,
                "No. telepon belum diisi!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringSelection ss = new StringSelection(noTelp);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        JOptionPane.showMessageDialog(this,
            "No. telepon " + noTelp + " berhasil disalin!\n" +
            "Tempelkan (Ctrl+V) ke WhatsApp atau aplikasi lain.",
            "Berhasil Disalin", JOptionPane.INFORMATION_MESSAGE);
    }
    private void prosesPesanan() {
        String nama = txtNama.getText().trim();
        if (nama.isEmpty() || nama.equals("Contoh: Angel amalia")) {
            tampilkanError("Nama pelanggan tidak boleh kosong!");
            txtNama.requestFocus(); return;
        }

        String telepon = txtTelepon.getText().trim();
        if (telepon.isEmpty() || telepon.equals("Contoh: 08123456789")) {
            tampilkanError("No. telepon tidak boleh kosong!");
            txtTelepon.requestFocus(); return;
        }
        if (!telepon.matches("^[0-9+\\-]{8,15}$")) {
            tampilkanError("No. telepon tidak valid!\nGunakan angka, minimal 8 digit.\nContoh: 08123456789");
            txtTelepon.requestFocus(); return;
        }

        String beratStr = txtBerat.getText().trim();
        if (beratStr.isEmpty() || beratStr.equals("Contoh: 4.5")) {
            tampilkanError("Berat cucian tidak boleh kosong!");
            txtBerat.requestFocus(); return;
        }
        double berat;
        try {
            berat = Double.parseDouble(beratStr);
            if (berat <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            tampilkanError("Berat cucian harus berupa angka positif!\nContoh: 4.5");
            txtBerat.requestFocus(); return;
        }

        String jenis      = (String) cmbLayanan.getSelectedItem();
        String pembayaran = ((String) cmbPembayaran.getSelectedItem())
                            .replaceAll("[^a-zA-Z ]", "").trim();
        LaundryStrategy strategy = LaundryFactory.createService(jenis);
        LaundryOrder order = new LaundryOrder(nama, telepon, berat, strategy, pembayaran);

        lblStrukNomor.setText(order.getNomorOrder());
        lblStrukNama.setText(order.getNamaPelanggan());
        lblStrukTelepon.setText(order.getNoTelepon());
        lblStrukLayanan.setText(order.getNamaLayanan());
        lblStrukBerat.setText(order.getBeratKg() + " kg");
        lblStrukTarif.setText(order.getTarifFormatted());
        lblStrukEstimasi.setText(order.getEstimasiWaktu());
        lblStrukMasuk.setText(order.getTanggalMasuk());
        lblStrukSelesai.setText(order.getTanggalSelesai());
        lblStrukPembayaran.setText(order.getJenisPembayaran());
        lblStrukTotal.setText(order.getTotalHargaFormatted());
        panelStruk.setVisible(true);
        panelStruk.revalidate();
        panelStruk.repaint();
        tambahKeRiwayat(order);

        SwingUtilities.invokeLater(() -> {
            java.awt.Container c = panelStruk.getParent();
            while (c != null && !(c instanceof JScrollPane)) {
                c = c.getParent();
            }
            if (c instanceof JScrollPane) {
                JScrollBar vsb = ((JScrollPane) c).getVerticalScrollBar();
                vsb.setValue(vsb.getMaximum());
            }
        });
    }
    private void tambahKeRiwayat(LaundryOrder order) {
        jumlahOrder++;
        String entri =
            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
            "  ORDER #" + jumlahOrder + "  |  No: " + order.getNomorOrder() + "\n" +
            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
            "  Nama        : " + order.getNamaPelanggan() + "\n" +
            "  Telepon     : " + order.getNoTelepon() + "\n" +
            "  Layanan     : " + order.getNamaLayanan() + "\n" +
            "  Berat       : " + order.getBeratKg() + " kg\n" +
            "  Tgl Masuk   : " + order.getTanggalMasuk() + "\n" +
            "  Tgl Selesai : " + order.getTanggalSelesai() + "\n" +
            "  Pembayaran  : " + order.getJenisPembayaran() + "\n" +
            "  TOTAL       : " + order.getTotalHargaFormatted() + "\n\n";

        if (txtRiwayat.getText().equals("Belum ada pesanan yang diproses.")) {
            txtRiwayat.setText(entri);
        } else {
            txtRiwayat.setText(txtRiwayat.getText() + entri);
        }
    }
    private void resetForm() {
        txtNama.setForeground(Color.GRAY);
        txtNama.setText("Contoh: Angel amalia");
        txtTelepon.setForeground(Color.GRAY);
        txtTelepon.setText("Contoh: 08123456789");
        txtBerat.setForeground(Color.GRAY);
        txtBerat.setText("Contoh: 4.5");
        cmbLayanan.setSelectedIndex(0);
        cmbPembayaran.setSelectedIndex(0);
        panelStruk.setVisible(false);
        updateInfoLayanan();
    }

    private void tampilkanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Input Tidak Valid",
            JOptionPane.WARNING_MESSAGE);
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }

        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}