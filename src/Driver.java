import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Iterator;

public class Driver extends JFrame {

    private final HospitalManagementSystem hospital;
    private final FileManager fileManager;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);

    private JLabel patientCount;
    private JLabel doctorCount;
    private JLabel appointmentCount;
    private JLabel recordCount;
    private JLabel billCount;

    private JTextField globalSearchField;
    private JButton scheduleMenuButton;
    private JTable scheduleTable;
    private DefaultTableModel scheduleModel;
    private LiveActivityChart liveActivityChart;
    private JPanel reminderListPanel;
    private JPanel appointmentQueuePanel;
    private JPanel medicalBillQueuePanel;

    // Live Activity metric labels
    private JLabel livePatientMetric;
    private JLabel liveDoctorMetric;
    private JLabel liveAppointmentMetric;

    // Premium light dashboard palette inspired by the reference design.
    private static final Color SIDEBAR = new Color(224, 239, 234);
    private static final Color SIDEBAR_TEXT = new Color(72, 103, 94);
    private static final Color PRIMARY = new Color(20, 151, 112);
    private static final Color PRIMARY_DARK = new Color(14, 120, 89);
    private static final Color BG = new Color(238, 247, 243);
    private static final Color CARD = Color.WHITE;
    private static final Color TEXT = new Color(29, 58, 49);
    private static final Color MUTED = new Color(91, 119, 109);
    private static final Color BORDER = new Color(204, 224, 216);
    private static final Color SUCCESS = new Color(19, 166, 125);
    private static final Color DANGER = new Color(213, 74, 82);
    private static final Color WARNING = new Color(211, 151, 42);
    private static final Color SLATE = new Color(67, 93, 84);
    private static final Color ROW_ALT = new Color(246, 251, 249);
    private static final Color NAVY_TABLE_HEADER = new Color(35, 78, 65);
    private static final Color WHITE = Color.WHITE;
    private static final Color SOFT_SHADOW = new Color(29, 58, 49, 28);

    public Driver() {
        hospital = new HospitalManagementSystem();
        fileManager = new FileManager(hospital);

        setTitle("Hospital Management System");
        setSize(1320, 790);
        setMinimumSize(new Dimension(1120, 690));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

        buildGUI();
    }

    private void buildGUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);

        root.add(createSidebar(), BorderLayout.WEST);

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(BG);
        main.add(createHeader(), BorderLayout.NORTH);

        contentPanel.setBackground(BG);
        contentPanel.add(createDashboard(), "Dashboard");
        contentPanel.add(createPatientPanel(), "Patients");
        contentPanel.add(createDoctorPanel(), "Doctors");
        contentPanel.add(createAppointmentPanel(), "Appointments");
        contentPanel.add(createSchedulePanel(), "Schedule");
        contentPanel.add(createRecordPanel(), "Records");
        contentPanel.add(createBillPanel(), "Bills");

        main.add(contentPanel, BorderLayout.CENTER);
        root.add(main, BorderLayout.CENTER);

        setContentPane(root);
        showPage("Dashboard");
    }

    private JButton activeMenuButton;
    private JButton dashboardMenuButton;

    private JPanel createSidebar() {
        JPanel side = new JPanel(new BorderLayout());
        side.setPreferredSize(new Dimension(236, 0));
        side.setBackground(SIDEBAR);
        side.setBorder(new EmptyBorder(24, 16, 20, 16));

        JPanel brand = new JPanel(new BorderLayout(12, 0));
        brand.setOpaque(false);

        JLabel logo = new JLabel("✚", SwingConstants.CENTER);
        logo.setFont(new Font("SansSerif", Font.BOLD, 22));
        logo.setForeground(Color.WHITE);
        logo.setOpaque(true);
        logo.setBackground(PRIMARY);
        logo.setBorder(new EmptyBorder(7, 9, 7, 9));
        logo.setPreferredSize(new Dimension(48, 48));

        JPanel brandText = new JPanel();
        brandText.setOpaque(false);
        brandText.setLayout(new BoxLayout(brandText, BoxLayout.Y_AXIS));

        JLabel brandName = new JLabel("MedPulse");
        brandName.setForeground(TEXT);
        brandName.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel brandSub = new JLabel("Hospital Administration");
        brandSub.setForeground(MUTED);
        brandSub.setFont(new Font("SansSerif", Font.PLAIN, 11));

        brandText.add(brandName);
        brandText.add(Box.createVerticalStrut(2));
        brandText.add(brandSub);

        brand.add(logo, BorderLayout.WEST);
        brand.add(brandText, BorderLayout.CENTER);
        side.add(brand, BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);

        JLabel mainMenu = new JLabel("MAIN MENU");
        mainMenu.setForeground(MUTED);
        mainMenu.setFont(new Font("SansSerif", Font.BOLD, 11));
        mainMenu.setBorder(new EmptyBorder(30, 12, 12, 0));
        center.add(mainMenu, BorderLayout.NORTH);

        JPanel menu = new JPanel();
        menu.setOpaque(false);
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        dashboardMenuButton = menuButton("▦", "Dashboard", "Dashboard");
        menu.add(dashboardMenuButton);
        activeMenuButton = dashboardMenuButton;
        dashboardMenuButton.setBackground(PRIMARY);
        dashboardMenuButton.setForeground(Color.WHITE);

        menu.add(Box.createVerticalStrut(8));
        menu.add(menuButton("♙", "Patients Directory", "Patients"));
        menu.add(Box.createVerticalStrut(8));
        menu.add(menuButton("✚", "Doctors List", "Doctors"));
        menu.add(Box.createVerticalStrut(8));

        scheduleMenuButton = menuButton("◷", "Schedule", "Schedule");
        menu.add(scheduleMenuButton);

        menu.add(Box.createVerticalStrut(8));
        menu.add(menuButton("▤", "Medical Records", "Records"));
        menu.add(Box.createVerticalStrut(8));
        menu.add(menuButton("▣", "Billing & Payments", "Bills"));

        center.add(menu, BorderLayout.CENTER);
        side.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

        JPanel quick = new JPanel(new BorderLayout(10, 0));
        quick.setOpaque(false);
        quick.setBorder(new EmptyBorder(8, 10, 8, 10));
        JLabel quickIcon = new JLabel("●", SwingConstants.CENTER);
        quickIcon.setForeground(PRIMARY);
        quickIcon.setFont(new Font("SansSerif", Font.BOLD, 12));
        JLabel quickText = new JLabel("Administration");
        quickText.setForeground(TEXT);
        quickText.setFont(new Font("SansSerif", Font.BOLD, 12));
        quick.add(quickIcon, BorderLayout.WEST);
        quick.add(quickText, BorderLayout.CENTER);
        bottom.add(quick);

        JButton exit = menuButton("⏻", "Exit", null);
        exit.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );
            if (result == JOptionPane.YES_OPTION) System.exit(0);
        });
        bottom.add(exit);
        side.add(bottom, BorderLayout.SOUTH);

        return side;
    }

    private JButton menuButton(String icon, String text, String page) {
        RoundedButton button = new RoundedButton(icon + "   " + text, 14);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(new EmptyBorder(0, 14, 0, 10));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        button.setPreferredSize(new Dimension(204, 46));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(SIDEBAR_TEXT);
        button.setBackground(SIDEBAR);

        if (page != null) {
            button.addActionListener(e -> {
                setActiveMenu(button);
                if (page.equals("Dashboard")) refreshDashboard();
                showPage(page);
            });
        }
        return button;
    }

    private void setActiveMenu(JButton button) {
        if (activeMenuButton != null) {
            activeMenuButton.setBackground(SIDEBAR);
            activeMenuButton.setForeground(SIDEBAR_TEXT);
        }
        activeMenuButton = button;
        activeMenuButton.setBackground(PRIMARY);
        activeMenuButton.setForeground(Color.WHITE);
        activeMenuButton.repaint();
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout(18, 0));
        header.setBackground(BG);
        header.setBorder(new EmptyBorder(12, 22, 8, 22));
        header.setPreferredSize(new Dimension(0, 76));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        left.setOpaque(false);

        JLabel menuIcon = new JLabel("☰");
        menuIcon.setForeground(MUTED);
        menuIcon.setFont(new Font("SansSerif", Font.BOLD, 18));

        RoundedButton home = new RoundedButton("⌂  Home", 14);
        home.setBackground(PRIMARY);
        home.setForeground(Color.WHITE);
        home.setFont(new Font("SansSerif", Font.BOLD, 13));
        home.setPreferredSize(new Dimension(95, 38));
        home.addActionListener(e -> {
            if (dashboardMenuButton != null) setActiveMenu(dashboardMenuButton);
            showPage("Dashboard");
            refreshDashboard();
        });

        JLabel separator = new JLabel("›");
        separator.setForeground(new Color(162, 174, 193));
        separator.setFont(new Font("SansSerif", Font.BOLD, 17));

        JLabel pageHint = new JLabel("Hospital Management Administration");
        pageHint.setForeground(MUTED);
        pageHint.setFont(new Font("SansSerif", Font.PLAIN, 13));

        left.add(menuIcon);
        left.add(home);
        left.add(separator);
        left.add(pageHint);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        right.setOpaque(false);

        RoundedPanel searchBox = new RoundedPanel(18);
        searchBox.setBackground(Color.WHITE);
        searchBox.setShadowEnabled(true);
        searchBox.setBorder(new RoundedLineBorder(BORDER, 18));
        searchBox.setLayout(new BorderLayout(5, 0));
        searchBox.setPreferredSize(new Dimension(300, 38));

        JButton searchIcon = new RoundedButton("⌕", 14);
        searchIcon.setBackground(Color.WHITE);
        searchIcon.setForeground(MUTED);
        searchIcon.setFont(new Font("SansSerif", Font.BOLD, 18));
        searchIcon.setPreferredSize(new Dimension(40, 34));

        globalSearchField = new JTextField();
        globalSearchField.setFont(new Font("SansSerif", Font.PLAIN, 12));
        globalSearchField.setForeground(TEXT);
        globalSearchField.setBackground(Color.WHITE);
        globalSearchField.setBorder(BorderFactory.createEmptyBorder(4, 3, 4, 8));
        globalSearchField.setToolTipText("Search patients, doctors or appointments");
        globalSearchField.setText("");
        globalSearchField.addActionListener(e -> globalSearch());

        searchIcon.addActionListener(e -> globalSearch());
        searchBox.add(searchIcon, BorderLayout.WEST);
        searchBox.add(globalSearchField, BorderLayout.CENTER);

        JLabel notification = new JLabel("●");
        notification.setForeground(PRIMARY);
        notification.setFont(new Font("SansSerif", Font.BOLD, 16));

        RoundedPanel profile = new RoundedPanel(18);
        profile.setShadowEnabled(true);
        profile.setLayout(new BorderLayout(8, 0));
        profile.setBackground(Color.WHITE);
        profile.setBorder(new EmptyBorder(4, 6, 4, 12));

        JLabel avatar = avatarLabel("HA", 34);
        profile.add(avatar, BorderLayout.WEST);

        JPanel pText = new JPanel();
        pText.setOpaque(false);
        pText.setLayout(new BoxLayout(pText, BoxLayout.Y_AXIS));
        JLabel admin = new JLabel("Hospital Admin");
        admin.setFont(new Font("SansSerif", Font.BOLD, 12));
        admin.setForeground(TEXT);
        JLabel role = new JLabel("Administrator");
        role.setFont(new Font("SansSerif", Font.PLAIN, 10));
        role.setForeground(MUTED);
        pText.add(admin);
        pText.add(role);
        profile.add(pText, BorderLayout.CENTER);

        right.add(searchBox);
        right.add(notification);
        right.add(profile);

        header.add(left, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);
        return header;
    }

    private void globalSearch() {
        String query = globalSearchField == null ? "" : globalSearchField.getText().trim();

        if (query.isEmpty()) {
            error("Please enter something to search.");
            return;
        }

        String q = query.toLowerCase();
        java.util.List<String> matches = new java.util.ArrayList<>();

        for (Patient p : hospital.getPatientList()) {
            if (String.valueOf(p.getID()).contains(q) || p.getName().toLowerCase().contains(q)) {
                matches.add("PATIENT  •  ID " + p.getID() + "  •  " + p.getName());
            }
        }

        for (Doctor d : hospital.getDoctorList()) {
            if (String.valueOf(d.getID()).contains(q) || d.getName().toLowerCase().contains(q)
                    || d.getSpecialization().toLowerCase().contains(q)) {
                matches.add("DOCTOR   •  ID " + d.getID() + "  •  " + d.getName());
            }
        }

        for (Appointment a : hospital.getAppointmentList()) {
            if (String.valueOf(a.getAppointment_id()).contains(q)
                    || String.valueOf(a.getPatient_id()).contains(q)
                    || String.valueOf(a.getDoctor_id()).contains(q)
                    || a.getReason().toLowerCase().contains(q)) {
                matches.add("APPOINTMENT  •  ID " + a.getAppointment_id()
                        + "  •  Patient " + a.getPatient_id()
                        + "  •  " + a.getDate());
            }
        }

        if (matches.isEmpty()) {
            error("No matching patient, doctor or appointment found.");
            return;
        }

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : matches) listModel.addElement(item);

        JList<String> list = new JList<>(listModel);
        list.setFont(new Font("SansSerif", Font.PLAIN, 13));
        list.setForeground(TEXT);
        list.setBackground(Color.WHITE);
        list.setSelectionBackground(new Color(225, 244, 237));
        list.setSelectionForeground(TEXT);
        list.setBorder(new EmptyBorder(6, 8, 6, 8));

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(new RoundedLineBorder(BORDER, 14));
        scroll.setPreferredSize(new Dimension(520, 250));

        JLabel heading = new JLabel("Search results for:  \"" + query + "\"");
        heading.setFont(new Font("SansSerif", Font.BOLD, 14));
        heading.setForeground(TEXT);
        heading.setBorder(new EmptyBorder(0, 0, 10, 0));

        JPanel box = new JPanel(new BorderLayout(0, 8));
        box.setBackground(Color.WHITE);
        box.add(heading, BorderLayout.NORTH);
        box.add(scroll, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(
                this,
                box,
                "Global Search",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private JPanel createDashboard() {
        JPanel panel = new JPanel(new BorderLayout(0, 16));
        panel.setBackground(BG);
        panel.setBorder(new EmptyBorder(6, 22, 20, 22));

        JPanel welcome = new JPanel(new BorderLayout(15, 0));
        welcome.setOpaque(false);

        JPanel welcomeLeft = new JPanel(new BorderLayout(12, 0));
        welcomeLeft.setOpaque(false);
        welcomeLeft.add(avatarLabel("HA", 66), BorderLayout.WEST);

        JPanel welcomeText = new JPanel();
        welcomeText.setOpaque(false);
        welcomeText.setLayout(new BoxLayout(welcomeText, BoxLayout.Y_AXIS));

        int hour = java.time.LocalTime.now().getHour();
        String greeting = hour < 12 ? "Good Morning!" : (hour < 18 ? "Good Afternoon!" : "Good Evening!");

        JLabel morning = new JLabel(greeting);
        morning.setForeground(TEXT);
        morning.setFont(new Font("SansSerif", Font.BOLD, 15));

        JLabel admin = new JLabel("Hospital Dashboard");
        admin.setForeground(PRIMARY);
        admin.setFont(new Font("SansSerif", Font.BOLD, 27));

        JLabel quote = new JLabel("Manage patient care, schedules and hospital records from one workspace.");
        quote.setForeground(MUTED);
        quote.setFont(new Font("SansSerif", Font.PLAIN, 11));

        welcomeText.add(morning);
        welcomeText.add(Box.createVerticalStrut(1));
        welcomeText.add(admin);
        welcomeText.add(Box.createVerticalStrut(4));
        welcomeText.add(quote);

        welcomeLeft.add(welcomeText, BorderLayout.CENTER);
        welcome.add(welcomeLeft, BorderLayout.CENTER);

        RoundedButton addPatient = new RoundedButton("＋  Add Patient", 14);
        addPatient.setBackground(PRIMARY);
        addPatient.setForeground(Color.WHITE);
        addPatient.setFont(new Font("SansSerif", Font.BOLD, 12));
        addPatient.setPreferredSize(new Dimension(144, 42));
        addPatient.addActionListener(e -> {

            showPage("Patients");
        });
        welcome.add(addPatient, BorderLayout.EAST);

        JPanel top = new JPanel(new BorderLayout(0, 14));
        top.setOpaque(false);
        top.add(welcome, BorderLayout.NORTH);

        JPanel stats = new JPanel(new GridLayout(1, 4, 14, 0));
        stats.setOpaque(false);

        patientCount = bigCountLabel();
        doctorCount = bigCountLabel();
        appointmentCount = bigCountLabel();
        recordCount = bigCountLabel();
        billCount = bigCountLabel();

        stats.add(lightStatCard(
                "TOTAL PATIENTS", patientCount, "Registered patients",
                "♙", new Color(231, 248, 215), new Color(100, 165, 30)
        ));
        stats.add(lightStatCard(
                "ACTIVE DOCTORS", doctorCount, "Medical team",
                "✚", new Color(222, 244, 255), new Color(20, 148, 205)
        ));
        stats.add(lightStatCard(
                "TODAY'S SCHEDULES", appointmentCount, "Scheduled appointments",
                "◷", new Color(255, 242, 202), new Color(206, 145, 21)
        ));
        stats.add(lightStatCard(
                "MEDICAL BILLS", billCount, "Billing records",
                "▣", new Color(255, 229, 235), new Color(220, 72, 104)
        ));

        top.add(stats, BorderLayout.CENTER);
        panel.add(top, BorderLayout.NORTH);

        JPanel lower = new JPanel(new GridBagLayout());
        lower.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.60;
        gbc.weighty = 1.0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 0, 0, 14);
        lower.add(createLiveActivityCard(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.20;
        gbc.weighty = 0.5;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 10, 14);
        lower.add(createAppointmentQueueCard(), gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 14);
        lower.add(createMedicalBillQueueCard(), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.20;
        gbc.weighty = 1.0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        lower.add(createRemindersCard(), gbc);

        panel.add(lower, BorderLayout.CENTER);
        return panel;
    }

    private JPanel lightStatCard(String title, JLabel value, String subtitle,
                                 String iconText, Color iconBg, Color iconFg) {
        JPanel card = new RoundedPanel(18);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                new RoundedLineBorder(BORDER, 18),
                new EmptyBorder(13, 13, 13, 13)
        ));
        card.setLayout(new BorderLayout(11, 0));

        JLabel icon = new JLabel(iconText, SwingConstants.CENTER);
        icon.setFont(new Font("SansSerif", Font.BOLD, 18));
        icon.setForeground(iconFg);
        icon.setOpaque(true);
        icon.setBackground(iconBg);
        icon.setPreferredSize(new Dimension(42, 42));

        JPanel data = new JPanel();
        data.setOpaque(false);
        data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(MUTED);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 10));

        value.setForeground(TEXT);
        value.setFont(new Font("SansSerif", Font.BOLD, 24));

        JLabel sub = new JLabel(subtitle);
        sub.setForeground(SLATE);
        sub.setFont(new Font("SansSerif", Font.PLAIN, 10));

        data.add(titleLabel);
        data.add(Box.createVerticalStrut(1));
        data.add(value);
        data.add(Box.createVerticalStrut(1));
        data.add(sub);

        card.add(icon, BorderLayout.WEST);
        card.add(data, BorderLayout.CENTER);
        return card;
    }

    private JPanel createLiveActivityCard() {
        JPanel card = cardPanel();
        card.setBorder(BorderFactory.createCompoundBorder(
                new RoundedLineBorder(BORDER, 20),
                new EmptyBorder(16, 18, 13, 18)
        ));
        card.setLayout(new BorderLayout(0, 10));

        JPanel head = new JPanel(new BorderLayout());
        head.setOpaque(false);

        JLabel title = cardTitle("Live Activity");
        JLabel live = new JLabel("●  LIVE TRACKING");
        live.setForeground(SUCCESS);
        live.setFont(new Font("SansSerif", Font.BOLD, 10));

        head.add(title, BorderLayout.WEST);
        head.add(live, BorderLayout.EAST);

        JPanel metrics = new JPanel(new GridLayout(1, 3, 20, 0));
        metrics.setOpaque(false);

        livePatientMetric = metricValue(String.valueOf(hospital.getPatientList().size()));
        liveDoctorMetric = metricValue(String.valueOf(hospital.getDoctorList().size()));
        liveAppointmentMetric = metricValue(String.valueOf(hospital.getAppointmentList().size()));

        metrics.add(metricBlock(livePatientMetric, "Patients"));
        metrics.add(metricBlock(liveDoctorMetric, "Doctors"));
        metrics.add(metricBlock(liveAppointmentMetric, "Appointments"));

        liveActivityChart = new LiveActivityChart();
        liveActivityChart.initializeData(
                hospital.getPatientList().size(),
                hospital.getDoctorList().size(),
                hospital.getMedicalRecordList().size(),
                hospital.getAppointmentList().size(),
                hospital.getMedicalBillList().size()
        );

        card.add(head, BorderLayout.NORTH);
        card.add(metrics, BorderLayout.CENTER);
        card.add(liveActivityChart, BorderLayout.SOUTH);

        return card;
    }

    private JLabel metricValue(String value) {
        JLabel label = new JLabel(value);
        label.setFont(new Font("SansSerif", Font.BOLD, 19));
        label.setForeground(TEXT);
        return label;
    }

    private JPanel createAppointmentQueueCard() {
        JPanel card = cardPanel();
        card.setLayout(new BorderLayout(0, 12));
        card.setBorder(new EmptyBorder(16, 18, 16, 18));

        JPanel head = new JPanel(new BorderLayout());
        head.setOpaque(false);
        JLabel title = cardTitle("Today's Appointment Queue");
        RoundedButton view = new RoundedButton("View All  →", 10);
        view.setBackground(Color.WHITE);
        view.setForeground(PRIMARY);
        view.setFont(new Font("SansSerif", Font.BOLD, 10));
        view.setPreferredSize(new Dimension(92, 30));
        view.addActionListener(e -> {
            setActiveMenuByPage("Schedule");
            showPage("Appointments");
        });
        head.add(title, BorderLayout.WEST);
        head.add(view, BorderLayout.EAST);

        appointmentQueuePanel = new JPanel();
        appointmentQueuePanel.setOpaque(false);
        appointmentQueuePanel.setLayout(new BoxLayout(appointmentQueuePanel, BoxLayout.Y_AXIS));
        refreshAppointmentQueue();

        JScrollPane scroll = new JScrollPane(appointmentQueuePanel);
        stylePremiumScrollPane(scroll);

        card.add(head, BorderLayout.NORTH);
        card.add(scroll, BorderLayout.CENTER);
        return card;
    }

    private void refreshAppointmentQueue() {
        if (appointmentQueuePanel == null) return;

        appointmentQueuePanel.removeAll();

        int shown = hospital.getAppointmentList().size();

        if (shown == 0) {
            JLabel empty = new JLabel("No appointments scheduled yet.");
            empty.setForeground(MUTED);
            empty.setFont(new Font("SansSerif", Font.PLAIN, 12));
            appointmentQueuePanel.add(empty);
        } else {
            for (int i = 0; i < shown; i++) {
                Appointment a = hospital.getAppointmentList().get(i);
                appointmentQueuePanel.add(appointmentRow(a, i + 1));
                if (i < shown - 1) appointmentQueuePanel.add(Box.createVerticalStrut(9));
            }
        }

        appointmentQueuePanel.revalidate();
        appointmentQueuePanel.repaint();
    }

    private JPanel appointmentRow(Appointment a, int number) {
        JPanel row = new RoundedPanel(13);
        row.setBackground(new Color(247, 249, 253));
        row.setBorder(new EmptyBorder(9, 10, 9, 10));
        row.setLayout(new BorderLayout(10, 0));

        JLabel id = new JLabel(String.valueOf(number), SwingConstants.CENTER);
        id.setOpaque(true);
        id.setBackground(new Color(232, 241, 255));
        id.setForeground(PRIMARY);
        id.setFont(new Font("SansSerif", Font.BOLD, 11));
        id.setPreferredSize(new Dimension(34, 34));

        JPanel mid = new JPanel();
        mid.setOpaque(false);
        mid.setLayout(new BoxLayout(mid, BoxLayout.Y_AXIS));
        JLabel patient = new JLabel("Patient ID: " + a.getPatient_id());
        patient.setForeground(TEXT);
        patient.setFont(new Font("SansSerif", Font.BOLD, 12));
        JLabel details = new JLabel("Doctor ID: " + a.getDoctor_id() + "  •  " + a.getReason());
        details.setForeground(MUTED);
        details.setFont(new Font("SansSerif", Font.PLAIN, 10));
        mid.add(patient);
        mid.add(Box.createVerticalStrut(3));
        mid.add(details);

        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        JLabel time = new JLabel(a.getTime());
        time.setForeground(PRIMARY);
        time.setFont(new Font("SansSerif", Font.BOLD, 11));
        JLabel date = new JLabel(a.getDate());
        date.setForeground(MUTED);
        date.setFont(new Font("SansSerif", Font.PLAIN, 9));
        right.add(time);
        right.add(Box.createVerticalStrut(2));
        right.add(date);

        row.add(id, BorderLayout.WEST);
        row.add(mid, BorderLayout.CENTER);
        row.add(right, BorderLayout.EAST);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 62));
        return row;
    }

    private JPanel createMedicalBillQueueCard() {
        JPanel card = cardPanel();
        card.setLayout(new BorderLayout(0, 10));
        card.setBorder(new EmptyBorder(16, 16, 16, 16));

        JPanel head = new JPanel(new BorderLayout());
        head.setOpaque(false);

        JLabel title = cardTitle("Medical Bill Queue");
        RoundedButton view = new RoundedButton("View All  →", 10);
        view.setBackground(Color.WHITE);
        view.setForeground(PRIMARY);
        view.setFont(new Font("SansSerif", Font.BOLD, 10));
        view.setPreferredSize(new Dimension(92, 30));
        view.addActionListener(e -> showPage("Bills"));

        head.add(title, BorderLayout.WEST);
        head.add(view, BorderLayout.EAST);

        medicalBillQueuePanel = new JPanel();
        medicalBillQueuePanel.setOpaque(false);
        medicalBillQueuePanel.setLayout(new BoxLayout(medicalBillQueuePanel, BoxLayout.Y_AXIS));
        refreshMedicalBillQueue();

        JScrollPane scroll = new JScrollPane(medicalBillQueuePanel);
        stylePremiumScrollPane(scroll);

        card.add(head, BorderLayout.NORTH);
        card.add(scroll, BorderLayout.CENTER);
        return card;
    }

    private void refreshMedicalBillQueue() {
        if (medicalBillQueuePanel == null) return;

        medicalBillQueuePanel.removeAll();

        int shown = hospital.getMedicalBillList().size();

        if (shown == 0) {
            JLabel empty = new JLabel("No medical bills yet.");
            empty.setForeground(MUTED);
            empty.setFont(new Font("SansSerif", Font.PLAIN, 11));
            medicalBillQueuePanel.add(empty);
        } else {
            for (int i = 0; i < shown; i++) {
                MedicalBill bill = hospital.getMedicalBillList().get(i);
                medicalBillQueuePanel.add(medicalBillRow(bill, i + 1));
                if (i < shown - 1) {
                    medicalBillQueuePanel.add(Box.createVerticalStrut(7));
                }
            }
        }

        medicalBillQueuePanel.revalidate();
        medicalBillQueuePanel.repaint();
    }

    private JPanel medicalBillRow(MedicalBill bill, int number) {
        JPanel row = new RoundedPanel(13);
        row.setBackground(new Color(247, 249, 253));
        row.setBorder(new EmptyBorder(8, 9, 8, 9));
        row.setLayout(new BorderLayout(8, 0));

        JLabel id = new JLabel(String.valueOf(number), SwingConstants.CENTER);
        id.setOpaque(true);
        id.setBackground(new Color(255, 234, 239));
        id.setForeground(new Color(220, 72, 104));
        id.setFont(new Font("SansSerif", Font.BOLD, 10));
        id.setPreferredSize(new Dimension(28, 28));

        JPanel mid = new JPanel();
        mid.setOpaque(false);
        mid.setLayout(new BoxLayout(mid, BoxLayout.Y_AXIS));

        String patientName = findPatientNameById(bill.getPatient_id());

        JLabel patient = new JLabel(patientName);
        patient.setForeground(TEXT);
        patient.setFont(new Font("SansSerif", Font.BOLD, 11));

        JLabel patientId = new JLabel("Patient ID: " + bill.getPatient_id());
        patientId.setForeground(MUTED);
        patientId.setFont(new Font("SansSerif", Font.PLAIN, 9));

        mid.add(patient);
        mid.add(Box.createVerticalStrut(2));
        mid.add(patientId);

        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JLabel amount = new JLabel(bill.getAmount() + " BDT");
        amount.setForeground(PRIMARY);
        amount.setFont(new Font("SansSerif", Font.BOLD, 10));

        JLabel status = new JLabel(bill.getPayment_status());
        status.setForeground(bill.getPayment_status().equalsIgnoreCase("Paid") ? SUCCESS : WARNING);
        status.setFont(new Font("SansSerif", Font.BOLD, 9));

        right.add(amount);
        right.add(Box.createVerticalStrut(2));
        right.add(status);

        row.add(id, BorderLayout.WEST);
        row.add(mid, BorderLayout.CENTER);
        row.add(right, BorderLayout.EAST);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));
        return row;
    }

    private String findPatientNameById(int patientId) {
        for (Patient p : hospital.getPatientList()) {
            if (p.getID() == patientId) {
                return p.getName();
            }
        }
        return "Unknown Patient";
    }

    private JLabel bigCountLabel() {
        JLabel label = new JLabel("0");
        label.setFont(new Font("SansSerif", Font.BOLD, 25));
        label.setForeground(TEXT);
        return label;
    }

    private JPanel statCard(String title, JLabel value, String subtitle, String iconText, Color iconBg) {
        JPanel card = new RoundedPanel(18);
        card.setBackground(CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                new RoundedLineBorder(BORDER, 18),
                new EmptyBorder(14, 14, 14, 14)
        ));
        card.setLayout(new BorderLayout(12, 0));

        JLabel icon = new JLabel(iconText, SwingConstants.CENTER);
        icon.setFont(new Font("SansSerif", Font.BOLD, 19));
        icon.setForeground(PRIMARY);
        icon.setOpaque(true);
        icon.setBackground(iconBg);
        icon.setPreferredSize(new Dimension(44, 44));

        JPanel data = new JPanel();
        data.setOpaque(false);
        data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(TEXT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        JLabel sub = new JLabel(subtitle);
        sub.setForeground(MUTED);
        sub.setFont(new Font("SansSerif", Font.PLAIN, 10));

        data.add(titleLabel);
        data.add(Box.createVerticalStrut(4));
        data.add(value);
        data.add(Box.createVerticalStrut(2));
        data.add(sub);

        card.add(icon, BorderLayout.WEST);
        card.add(data, BorderLayout.CENTER);
        return card;
    }

    private JPanel createActivityCard() {
        JPanel card = cardPanel();
        card.setLayout(new BorderLayout(0, 10));
        card.setBorder(new EmptyBorder(16, 18, 14, 18));

        JPanel head = new JPanel(new BorderLayout());
        head.setOpaque(false);
        JLabel title = cardTitle("Patient Activity");
        JLabel badge = new JLabel("Records: 0");
        badge.setFont(new Font("SansSerif", Font.BOLD, 10));
        badge.setForeground(PRIMARY);
        head.add(title, BorderLayout.WEST);
        head.add(badge, BorderLayout.EAST);

        JPanel metrics = new JPanel(new GridLayout(1, 3, 25, 0));
        metrics.setOpaque(false);
        JLabel patients = new JLabel("0");
        JLabel records = new JLabel("0");
        JLabel bills = new JLabel("0");
        for (JLabel label : new JLabel[]{patients, records, bills}) {
            label.setFont(new Font("SansSerif", Font.BOLD, 18));
            label.setForeground(TEXT);
        }
        metrics.add(metricBlock(patients, "Patients"));
        metrics.add(metricBlock(records, "Medical Records"));
        metrics.add(metricBlock(bills, "Bills"));

        ActivityChart chart = new ActivityChart();
        chart.setData(
                hospital.getPatientList().size(),
                hospital.getMedicalRecordList().size(),
                hospital.getAppointmentList().size(),
                hospital.getMedicalBillList().size()
        );

        patients.setText(String.valueOf(hospital.getPatientList().size()));
        records.setText(String.valueOf(hospital.getMedicalRecordList().size()));
        bills.setText(String.valueOf(hospital.getMedicalBillList().size()));
        badge.setText("Records: " + hospital.getMedicalRecordList().size());

        card.add(head, BorderLayout.NORTH);
        card.add(metrics, BorderLayout.CENTER);
        card.add(chart, BorderLayout.SOUTH);
        return card;
    }

    private JPanel metricBlock(JLabel value, String name) {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel n = new JLabel(name);
        n.setForeground(MUTED);
        n.setFont(new Font("SansSerif", Font.PLAIN, 10));
        p.add(value);
        p.add(Box.createVerticalStrut(1));
        p.add(n);
        return p;
    }

    private JPanel createSchedulePanel() {
        JPanel panel = basePage("Schedule");

        JPanel body = new JPanel(new GridBagLayout());
        body.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        // Left: interactive calendar
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.36;
        gbc.insets = new Insets(0, 0, 0, 14);

        JPanel calendarCard = cardPanel();
        calendarCard.setBorder(BorderFactory.createCompoundBorder(
                new RoundedLineBorder(BORDER, 20),
                new EmptyBorder(18, 18, 18, 18)
        ));
        calendarCard.setLayout(new BorderLayout(0, 12));

        JPanel calendarHead = new JPanel(new BorderLayout());
        calendarHead.setOpaque(false);
        JLabel title = cardTitle("Calendar");
        JLabel todayLabel = new JLabel(java.time.LocalDate.now().toString());
        todayLabel.setForeground(PRIMARY);
        todayLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        calendarHead.add(title, BorderLayout.WEST);
        calendarHead.add(todayLabel, BorderLayout.EAST);

        JPanel calendar = buildCalendarGrid(true);
        calendarCard.add(calendarHead, BorderLayout.NORTH);
        calendarCard.add(calendar, BorderLayout.CENTER);

        RoundedButton manage = new RoundedButton("＋  Manage Appointments", 13);
        manage.setBackground(PRIMARY);
        manage.setForeground(Color.WHITE);
        manage.setFont(new Font("SansSerif", Font.BOLD, 12));
        manage.setPreferredSize(new Dimension(190, 38));
        manage.addActionListener(e -> {
            setActiveMenuByPage("Schedule");
            showPage("Appointments");
        });
        JPanel manageWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        manageWrap.setOpaque(false);
        manageWrap.add(manage);
        calendarCard.add(manageWrap, BorderLayout.SOUTH);

        body.add(calendarCard, gbc);

        // Right: appointment table
        gbc.gridx = 1;
        gbc.weightx = 0.64;
        gbc.insets = new Insets(0, 0, 0, 0);

        JPanel queueCard = cardPanel();
        queueCard.setBorder(BorderFactory.createCompoundBorder(
                new RoundedLineBorder(BORDER, 20),
                new EmptyBorder(16, 16, 16, 16)
        ));
        queueCard.setLayout(new BorderLayout(0, 10));

        JPanel queueHead = new JPanel(new BorderLayout());
        queueHead.setOpaque(false);

        JLabel qTitle = cardTitle("Upcoming Appointments");
        JLabel qInfo = new JLabel("Select a row to view appointment details");
        qInfo.setForeground(MUTED);
        qInfo.setFont(new Font("SansSerif", Font.PLAIN, 10));

        queueHead.add(qTitle, BorderLayout.WEST);
        queueHead.add(qInfo, BorderLayout.EAST);

        scheduleModel = new DefaultTableModel(
                new String[]{"Appointment ID", "Patient ID", "Doctor ID", "Date", "Time", "Reason"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        scheduleTable = new JTable(scheduleModel);
        stylePremiumTable(scheduleTable);
        refreshScheduleTable();

        JScrollPane scroll = new JScrollPane(scheduleTable);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(Color.WHITE);
        stylePremiumScrollPane(scroll);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(Color.WHITE);

        scheduleTable.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int row = scheduleTable.getSelectedRow();
            if (row < 0) return;

            int modelRow = scheduleTable.convertRowIndexToModel(row);
            String id = String.valueOf(scheduleModel.getValueAt(modelRow, 0));
            showScheduleDetails(Integer.parseInt(id));
        });

        queueCard.add(queueHead, BorderLayout.NORTH);
        queueCard.add(scroll, BorderLayout.CENTER);
        body.add(queueCard, gbc);

        panel.add(body, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildCalendarGrid(boolean interactive) {
        JPanel calendar = new JPanel(new GridLayout(7, 7, 5, 5));
        calendar.setOpaque(false);

        String[] days = {"S", "M", "T", "W", "T", "F", "S"};
        for (String d : days) {
            JLabel day = new JLabel(d, SwingConstants.CENTER);
            day.setFont(new Font("SansSerif", Font.BOLD, 10));
            day.setForeground(MUTED);
            calendar.add(day);
        }

        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalDate first = today.withDayOfMonth(1);
        int start = first.getDayOfWeek().getValue() % 7;
        int daysInMonth = today.lengthOfMonth();

        for (int i = 0; i < 42; i++) {
            JLabel date = new JLabel("", SwingConstants.CENTER);
            date.setFont(new Font("SansSerif", Font.PLAIN, 10));
            date.setForeground(TEXT);

            if (i >= start && i < start + daysInMonth) {
                int number = i - start + 1;
                date.setText(String.valueOf(number));
                if (number == today.getDayOfMonth()) {
                    date.setOpaque(true);
                    date.setBackground(PRIMARY);
                    date.setForeground(Color.WHITE);
                }
            }
            calendar.add(date);
        }
        return calendar;
    }

    private void showScheduleDetails(int appointmentId) {
        for (Appointment a : hospital.getAppointmentList()) {
            if (a.getAppointment_id() == appointmentId) {
                String details =
                        "Appointment ID: " + a.getAppointment_id() + "\n" +
                                "Patient ID: " + a.getPatient_id() + "\n" +
                                "Doctor ID: " + a.getDoctor_id() + "\n" +
                                "Date: " + a.getDate() + "\n" +
                                "Time: " + a.getTime() + "\n" +
                                "Reason: " + a.getReason();

                JOptionPane.showMessageDialog(
                        this,
                        details,
                        "Appointment Details",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }
        }
    }

    private void refreshScheduleTable() {
        if (scheduleModel == null) return;
        scheduleModel.setRowCount(0);

        for (Appointment a : hospital.getAppointmentList()) {
            scheduleModel.addRow(new Object[]{
                    a.getAppointment_id(),
                    a.getPatient_id(),
                    a.getDoctor_id(),
                    a.getDate(),
                    a.getTime(),
                    a.getReason()
            });
        }
    }

    private JPanel createRecentPatientCard() {
        JPanel card = cardPanel();
        card.setLayout(new BorderLayout(0, 10));
        card.setBorder(new EmptyBorder(16, 18, 16, 18));

        JLabel title = cardTitle("Recent Patient");
        card.add(title, BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(12, 0));
        body.setOpaque(false);
        body.add(avatarLabel("PT", 50), BorderLayout.WEST);

        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        String name = "No patient yet";
        String id = "ID —";
        String phone = "No contact information";
        String condition = "No medical condition added";
        String blood = "—";

        if (!hospital.getPatientList().isEmpty()) {
            Patient p = hospital.getPatientList().get(hospital.getPatientList().size() - 1);
            name = p.getName();
            id = "Patient ID: " + p.getID();
            phone = p.getPhone_Number();
            condition = p.getDiseases();
            blood = p.getBlood_Group();
        }

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        nameLabel.setForeground(TEXT);
        JLabel idLabel = new JLabel(id);
        idLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        idLabel.setForeground(MUTED);
        details.add(nameLabel);
        details.add(idLabel);
        details.add(Box.createVerticalStrut(10));
        details.add(infoLine("Phone", phone));
        details.add(infoLine("Condition", condition));
        details.add(infoLine("Blood Group", blood));

        body.add(details, BorderLayout.CENTER);
        card.add(body, BorderLayout.CENTER);
        return card;
    }

    private JLabel infoLine(String label, String value) {
        JLabel line = new JLabel(label + ":  " + value);
        line.setFont(new Font("SansSerif", Font.PLAIN, 11));
        line.setForeground(new Color(108, 126, 153));
        return line;
    }

    private JPanel createCalendarCard() {
        JPanel card = cardPanel();
        card.setLayout(new BorderLayout(0, 10));
        card.setBorder(new EmptyBorder(16, 18, 16, 18));

        JPanel head = new JPanel(new BorderLayout());
        head.setOpaque(false);
        JLabel title = cardTitle("Schedule");
        JLabel month = new JLabel(java.time.LocalDate.now().getMonth().toString().substring(0, 3) + " " + java.time.LocalDate.now().getYear());
        month.setForeground(MUTED);
        month.setFont(new Font("SansSerif", Font.BOLD, 11));
        head.add(title, BorderLayout.WEST);
        head.add(month, BorderLayout.EAST);

        JPanel calendar = new JPanel(new GridLayout(7, 7, 4, 4));
        calendar.setOpaque(false);
        String[] days = {"S", "M", "T", "W", "T", "F", "S"};
        for (String d : days) {
            JLabel day = new JLabel(d, SwingConstants.CENTER);
            day.setFont(new Font("SansSerif", Font.BOLD, 9));
            day.setForeground(MUTED);
            calendar.add(day);
        }

        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalDate first = today.withDayOfMonth(1);
        int start = first.getDayOfWeek().getValue() % 7;
        int daysInMonth = today.lengthOfMonth();

        for (int i = 0; i < 42; i++) {
            JLabel date = new JLabel("", SwingConstants.CENTER);
            date.setFont(new Font("SansSerif", Font.PLAIN, 10));
            date.setForeground(TEXT);
            if (i >= start && i < start + daysInMonth) {
                int number = i - start + 1;
                date.setText(String.valueOf(number));
                if (number == today.getDayOfMonth()) {
                    date.setOpaque(true);
                    date.setBackground(PRIMARY);
                    date.setForeground(Color.WHITE);
                }
            }
            calendar.add(date);
        }

        card.add(head, BorderLayout.NORTH);
        card.add(calendar, BorderLayout.CENTER);
        return card;
    }

    private JPanel createRemindersCard() {
        JPanel card = cardPanel();
        card.setBorder(new EmptyBorder(16, 16, 16, 16));
        card.setLayout(new BorderLayout(0, 10));

        JPanel head = new JPanel(new BorderLayout());
        head.setOpaque(false);

        JLabel title = cardTitle("Reminders");
        JLabel status = new JLabel("Upcoming");
        status.setFont(new Font("SansSerif", Font.BOLD, 10));
        status.setForeground(SUCCESS);

        head.add(title, BorderLayout.WEST);
        head.add(status, BorderLayout.EAST);

        reminderListPanel = new JPanel();
        reminderListPanel.setOpaque(false);
        reminderListPanel.setLayout(new BoxLayout(reminderListPanel, BoxLayout.Y_AXIS));

        refreshReminders();

        JScrollPane scroll = new JScrollPane(reminderListPanel);
        stylePremiumScrollPane(scroll);

        card.add(head, BorderLayout.NORTH);
        card.add(scroll, BorderLayout.CENTER);
        return card;
    }

    private void refreshReminders() {
        if (reminderListPanel == null) return;

        reminderListPanel.removeAll();
        int shown = hospital.getAppointmentList().size();

        if (shown == 0) {
            JLabel empty = new JLabel("No reminders yet.");
            empty.setFont(new Font("SansSerif", Font.PLAIN, 11));
            empty.setForeground(MUTED);
            reminderListPanel.add(empty);
        } else {
            for (int i = 0; i < shown; i++) {
                Appointment a = hospital.getAppointmentList().get(i);

                JPanel item = new RoundedPanel(12);
                item.setBackground(new Color(240, 249, 245));
                item.setBorder(new EmptyBorder(9, 9, 9, 9));
                item.setLayout(new BorderLayout(7, 0));
                item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 66));

                JLabel icon = new JLabel("◷", SwingConstants.CENTER);
                icon.setFont(new Font("SansSerif", Font.BOLD, 14));
                icon.setForeground(SUCCESS);
                icon.setPreferredSize(new Dimension(24, 24));

                JPanel text = new JPanel();
                text.setOpaque(false);
                text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));

                JLabel main = new JLabel("Appointment #" + a.getAppointment_id());
                main.setFont(new Font("SansSerif", Font.BOLD, 11));
                main.setForeground(TEXT);

                JLabel details = new JLabel("Patient " + a.getPatient_id() + "  •  " + a.getDate());
                details.setFont(new Font("SansSerif", Font.PLAIN, 9));
                details.setForeground(MUTED);

                JLabel time = new JLabel(a.getTime());
                time.setFont(new Font("SansSerif", Font.BOLD, 9));
                time.setForeground(PRIMARY_DARK);

                text.add(main);
                text.add(Box.createVerticalStrut(2));
                text.add(details);

                item.add(icon, BorderLayout.WEST);
                item.add(text, BorderLayout.CENTER);
                item.add(time, BorderLayout.EAST);

                reminderListPanel.add(item);
                if (i < shown - 1) reminderListPanel.add(Box.createVerticalStrut(8));
            }
        }

        reminderListPanel.revalidate();
        reminderListPanel.repaint();
    }

    private JLabel avatarLabel(String initials, int size) {
        return new AvatarLabel(initials, size, PRIMARY);
    }

    private JPanel cardPanel() {
        RoundedPanel card = new RoundedPanel(18);
        card.setBackground(CARD);
        card.setShadowEnabled(true);
        return card;
    }

    private JLabel cardTitle(String text) {
        JLabel title = new JLabel(text);
        title.setForeground(TEXT);
        title.setFont(new Font("SansSerif", Font.BOLD, 15));
        return title;
    }

    private JPanel createPatientPanel() {
        JPanel panel = basePage("Patient Management");

        JTextField name = field();
        JTextField id = field();
        JTextField phone = field();
        JTextField age = field();
        JTextField address = field();
        JTextField diseases = field();
        JTextField blood = field();

        JPanel form = formPanel();
        addFormRow(form, "Name", name);
        addFormRow(form, "Patient ID", id);
        addFormRow(form, "Phone Number", phone);
        addFormRow(form, "Age", age);
        addFormRow(form, "Address", address);
        addFormRow(form, "Diseases", diseases);
        addFormRow(form, "Blood Group", blood);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Name", "Phone", "Age", "Address", "Diseases", "Blood Group"}, 0
        );
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (Patient p : hospital.getPatientList()) {
                model.addRow(new Object[]{
                        p.getID(), p.getName(), p.getPhone_Number(), p.getAge(),
                        p.getAddress(), p.getDiseases(), p.getBlood_Group()
                });
            }
            refreshDashboard();
        };

        JButton add = actionButton("Add");
        JButton update = actionButton("Update");
        JButton delete = actionButton("Delete");
        JButton clear = actionButton("Clear");

        add.addActionListener(e -> {
            try {
                String n = required(name, "Name");
                int patientId = integer(id, "Patient ID");
                String ph = required(phone, "Phone Number");
                int a = integer(age, "Age");
                String ad = required(address, "Address");
                String d = required(diseases, "Diseases");
                String bg = required(blood, "Blood Group");

                for (Patient p : hospital.getPatientList()) {
                    if (p.getID() == patientId) {
                        throw new CustomException("Patient ID already exists.");
                    }
                }

                hospital.getPatientList().add(
                        new Patient(n, patientId, ph, a, ad, d, bg)
                );
                fileManager.PatientFile();
                refresh.run();
                clearFields(name, id, phone, age, address, diseases, blood);
                success("Patient added successfully.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        update.addActionListener(e -> {
            try {
                int searchId = integer(id, "Patient ID");
                Patient found = null;

                for (Patient p : hospital.getPatientList()) {
                    if (p.getID() == searchId) {
                        found = p;
                        break;
                    }
                }

                if (found == null) {
                    throw new CustomException("Patient not found.");
                }

                found.setName(required(name, "Name"));
                found.setPhone_Number(required(phone, "Phone Number"));
                found.setAge(integer(age, "Age"));
                found.setAddress(required(address, "Address"));
                found.setDiseases(required(diseases, "Diseases"));
                found.setBlood_group(required(blood, "Blood Group"));

                fileManager.PatientFile();
                refresh.run();
                clearFields(name, id, phone, age, address, diseases, blood);
                success("Patient updated successfully.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        delete.addActionListener(e -> {
            try {
                int searchId = integer(id, "Patient ID");
                Iterator<Patient> iterator = hospital.getPatientList().iterator();

                while (iterator.hasNext()) {
                    if (iterator.next().getID() == searchId) {
                        iterator.remove();
                        fileManager.PatientFile();
                        refresh.run();
                        clearFields(name, id, phone, age, address, diseases, blood);
                        success("Patient deleted successfully.");
                        return;
                    }
                }

                throw new CustomException("Patient not found.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        clear.addActionListener(e -> clearFields(name, id, phone, age, address, diseases, blood));

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int modelRow = table.convertRowIndexToModel(row);
                name.setText(String.valueOf(model.getValueAt(modelRow, 1)));
                id.setText(String.valueOf(model.getValueAt(modelRow, 0)));
                phone.setText(String.valueOf(model.getValueAt(modelRow, 2)));
                age.setText(String.valueOf(model.getValueAt(modelRow, 3)));
                address.setText(String.valueOf(model.getValueAt(modelRow, 4)));
                diseases.setText(String.valueOf(model.getValueAt(modelRow, 5)));
                blood.setText(String.valueOf(model.getValueAt(modelRow, 6)));
            }
        });

        panel.add(formCard(form, add, update, delete, clear), BorderLayout.WEST);
        panel.add(tablePanel(table), BorderLayout.CENTER);
        refresh.run();
        return panel;
    }

    private JPanel createDoctorPanel() {
        JPanel panel = basePage("Doctor Management");

        JTextField name = field();
        JTextField id = field();
        JTextField phone = field();
        JTextField specialization = field();
        JTextField qualification = field();
        JTextField salary = field();

        JPanel form = formPanel();
        addFormRow(form, "Name", name);
        addFormRow(form, "Doctor ID", id);
        addFormRow(form, "Phone Number", phone);
        addFormRow(form, "Specialization", specialization);
        addFormRow(form, "Qualification", qualification);
        addFormRow(form, "Salary", salary);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Name", "Phone", "Specialization", "Qualification", "Salary"}, 0
        );
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (Doctor d : hospital.getDoctorList()) {
                model.addRow(new Object[]{
                        d.getID(), d.getName(), d.getPhone_Number(),
                        d.getSpecialization(), d.getQualification(), "$" + d.getSalary()
                });
            }
            refreshDashboard();
        };

        JButton add = actionButton("Add");
        JButton update = actionButton("Update");
        JButton delete = actionButton("Delete");
        JButton clear = actionButton("Clear");

        add.addActionListener(e -> {
            try {
                String n = required(name, "Name");
                int doctorId = integer(id, "Doctor ID");
                String ph = required(phone, "Phone Number");
                String sp = required(specialization, "Specialization");
                String q = required(qualification, "Qualification");
                int s = integer(salary, "Salary");

                for (Doctor d : hospital.getDoctorList()) {
                    if (d.getID() == doctorId) {
                        throw new CustomException("Doctor ID already exists.");
                    }
                }

                hospital.getDoctorList().add(
                        new Doctor(n, doctorId, ph, sp, q, s)
                );
                fileManager.DoctorFile();
                refresh.run();
                clearFields(name, id, phone, specialization, qualification, salary);
                success("Doctor added successfully.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        update.addActionListener(e -> {
            try {
                int searchId = integer(id, "Doctor ID");
                Doctor found = null;

                for (Doctor d : hospital.getDoctorList()) {
                    if (d.getID() == searchId) {
                        found = d;
                        break;
                    }
                }

                if (found == null) throw new CustomException("Doctor not found.");

                found.setName(required(name, "Name"));
                found.setPhone_Number(required(phone, "Phone Number"));
                found.setSpecialization(required(specialization, "Specialization"));
                found.setQualification(required(qualification, "Qualification"));
                found.setSalary(integer(salary, "Salary"));

                fileManager.DoctorFile();
                refresh.run();
                clearFields(name, id, phone, specialization, qualification, salary);
                success("Doctor updated successfully.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        delete.addActionListener(e -> {
            try {
                int searchId = integer(id, "Doctor ID");
                Iterator<Doctor> iterator = hospital.getDoctorList().iterator();

                while (iterator.hasNext()) {
                    if (iterator.next().getID() == searchId) {
                        iterator.remove();
                        fileManager.DoctorFile();
                        refresh.run();
                        clearFields(name, id, phone, specialization, qualification, salary);
                        success("Doctor deleted successfully.");
                        return;
                    }
                }

                throw new CustomException("Doctor not found.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        clear.addActionListener(e -> clearFields(name, id, phone, specialization, qualification, salary));

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int r = table.convertRowIndexToModel(row);
                id.setText(String.valueOf(model.getValueAt(r, 0)));
                name.setText(String.valueOf(model.getValueAt(r, 1)));
                phone.setText(String.valueOf(model.getValueAt(r, 2)));
                specialization.setText(String.valueOf(model.getValueAt(r, 3)));
                qualification.setText(String.valueOf(model.getValueAt(r, 4)));
                salary.setText(String.valueOf(model.getValueAt(r, 5)));
            }
        });

        panel.add(formCard(form, add, update, delete, clear), BorderLayout.WEST);
        panel.add(tablePanel(table), BorderLayout.CENTER);
        refresh.run();
        return panel;
    }

    private JPanel createAppointmentPanel() {
        JPanel panel = basePage("Appointment Management");

        JTextField appointmentId = field();
        JTextField patientId = field();
        JTextField doctorId = field();
        DatePickerField date = dateField();
        JTextField time = field();
        JTextField reason = field();

        JPanel form = formPanel();
        addFormRow(form, "Appointment ID", appointmentId);
        addFormRow(form, "Patient ID", patientId);
        addFormRow(form, "Doctor ID", doctorId);
        addFormRow(form, "Date", date);
        addFormRow(form, "Time", time);
        addFormRow(form, "Reason", reason);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Appointment ID", "Patient ID", "Doctor ID", "Date", "Time", "Reason"}, 0
        );
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (Appointment a : hospital.getAppointmentList()) {
                model.addRow(new Object[]{
                        a.getAppointment_id(), a.getPatient_id(), a.getDoctor_id(),
                        a.getDate(), a.getTime(), a.getReason()
                });
            }
            refreshDashboard();
        };

        JButton add = actionButton("Add");
        JButton update = actionButton("Update");
        JButton delete = actionButton("Delete");
        JButton clear = actionButton("Clear");

        add.addActionListener(e -> {
            try {
                int aid = integer(appointmentId, "Appointment ID");
                int pid = integer(patientId, "Patient ID");
                int did = integer(doctorId, "Doctor ID");
                String dt = required(date, "Date");
                String tm = required(time, "Time");
                String rs = required(reason, "Reason");

                for (Appointment a : hospital.getAppointmentList()) {
                    if (a.getAppointment_id() == aid) {
                        throw new CustomException("Appointment ID already exists.");
                    }
                }

                if (!patientExists(pid)) throw new CustomException("Patient ID does not exist.");
                if (!doctorExists(did)) throw new CustomException("Doctor ID does not exist.");

                hospital.getAppointmentList().add(
                        new Appointment(aid, pid, did, dt, tm, rs)
                );
                fileManager.AppointmentFile();
                refresh.run();
                clearFields(appointmentId, patientId, doctorId, date, time, reason);
                success("Appointment added successfully.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        update.addActionListener(e -> {
            try {
                int searchId = integer(appointmentId, "Appointment ID");
                Appointment found = null;

                for (Appointment a : hospital.getAppointmentList()) {
                    if (a.getAppointment_id() == searchId) {
                        found = a;
                        break;
                    }
                }

                if (found == null) throw new CustomException("Appointment not found.");

                int pid = integer(patientId, "Patient ID");
                int did = integer(doctorId, "Doctor ID");

                if (!patientExists(pid)) throw new CustomException("Patient ID does not exist.");
                if (!doctorExists(did)) throw new CustomException("Doctor ID does not exist.");

                found.setPatient_id(pid);
                found.setDoctor_id(did);
                found.setDate(required(date, "Date"));
                found.setTime(required(time, "Time"));
                found.setReason(required(reason, "Reason"));

                fileManager.AppointmentFile();
                refresh.run();
                clearFields(appointmentId, patientId, doctorId, date, time, reason);
                success("Appointment updated successfully.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        delete.addActionListener(e -> {
            try {
                int searchId = integer(appointmentId, "Appointment ID");
                Iterator<Appointment> iterator = hospital.getAppointmentList().iterator();

                while (iterator.hasNext()) {
                    if (iterator.next().getAppointment_id() == searchId) {
                        iterator.remove();
                        fileManager.AppointmentFile();
                        refresh.run();
                        clearFields(appointmentId, patientId, doctorId, date, time, reason);
                        success("Appointment deleted successfully.");
                        return;
                    }
                }

                throw new CustomException("Appointment not found.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        clear.addActionListener(e -> clearFields(appointmentId, patientId, doctorId, date, time, reason));

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int r = table.convertRowIndexToModel(row);
                appointmentId.setText(String.valueOf(model.getValueAt(r, 0)));
                patientId.setText(String.valueOf(model.getValueAt(r, 1)));
                doctorId.setText(String.valueOf(model.getValueAt(r, 2)));
                date.setText(String.valueOf(model.getValueAt(r, 3)));
                time.setText(String.valueOf(model.getValueAt(r, 4)));
                reason.setText(String.valueOf(model.getValueAt(r, 5)));
            }
        });

        panel.add(formCard(form, add, update, delete, clear), BorderLayout.WEST);
        panel.add(tablePanel(table), BorderLayout.CENTER);
        refresh.run();
        return panel;
    }

    private JPanel createRecordPanel() {
        JPanel panel = basePage("Medical Record Management");

        JTextField recordId = field();
        JTextField patientId = field();
        JTextField doctorId = field();
        JTextField diagnosis = field();
        JTextField treatment = field();

        JPanel form = formPanel();
        addFormRow(form, "Record ID", recordId);
        addFormRow(form, "Patient ID", patientId);
        addFormRow(form, "Doctor ID", doctorId);
        addFormRow(form, "Diagnosis", diagnosis);
        addFormRow(form, "Treatment", treatment);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Record ID", "Patient ID", "Doctor ID", "Diagnosis", "Treatment"}, 0
        );
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (MedicalRecord r : hospital.getMedicalRecordList()) {
                model.addRow(new Object[]{
                        r.getRecordId(), r.getPatient_id(), r.getDoctor_id(),
                        r.getDiagnosis(), r.getTreatment()
                });
            }
            refreshDashboard();
        };

        JButton add = actionButton("Add");
        JButton update = actionButton("Update");
        JButton delete = actionButton("Delete");
        JButton clear = actionButton("Clear");

        add.addActionListener(e -> {
            try {
                int rid = integer(recordId, "Record ID");
                int pid = integer(patientId, "Patient ID");
                int did = integer(doctorId, "Doctor ID");

                for (MedicalRecord r : hospital.getMedicalRecordList()) {
                    if (r.getRecordId() == rid) {
                        throw new CustomException("Medical Record ID already exists.");
                    }
                }

                if (!patientExists(pid)) throw new CustomException("Patient ID does not exist.");
                if (!doctorExists(did)) throw new CustomException("Doctor ID does not exist.");

                hospital.getMedicalRecordList().add(
                        new MedicalRecord(
                                rid,
                                pid,
                                did,
                                required(diagnosis, "Diagnosis"),
                                required(treatment, "Treatment")
                        )
                );

                fileManager.MedicalRecordFile();
                refresh.run();
                clearFields(recordId, patientId, doctorId, diagnosis, treatment);
                success("Medical record added successfully.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        update.addActionListener(e -> {
            try {
                int searchId = integer(recordId, "Record ID");
                MedicalRecord found = null;

                for (MedicalRecord r : hospital.getMedicalRecordList()) {
                    if (r.getRecordId() == searchId) {
                        found = r;
                        break;
                    }
                }

                if (found == null) throw new CustomException("Medical Record not found.");

                int pid = integer(patientId, "Patient ID");
                int did = integer(doctorId, "Doctor ID");

                if (!patientExists(pid)) throw new CustomException("Patient ID does not exist.");
                if (!doctorExists(did)) throw new CustomException("Doctor ID does not exist.");

                found.setPatient_id(pid);
                found.setDoctor_id(did);
                found.setDiagnosis(required(diagnosis, "Diagnosis"));
                found.setTreatment(required(treatment, "Treatment"));

                fileManager.MedicalRecordFile();
                refresh.run();
                clearFields(recordId, patientId, doctorId, diagnosis, treatment);
                success("Medical record updated successfully.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        delete.addActionListener(e -> {
            try {
                int searchId = integer(recordId, "Record ID");
                Iterator<MedicalRecord> iterator = hospital.getMedicalRecordList().iterator();

                while (iterator.hasNext()) {
                    if (iterator.next().getRecordId() == searchId) {
                        iterator.remove();
                        fileManager.MedicalRecordFile();
                        refresh.run();
                        clearFields(recordId, patientId, doctorId, diagnosis, treatment);
                        success("Medical record deleted successfully.");
                        return;
                    }
                }

                throw new CustomException("Medical Record not found.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        clear.addActionListener(e -> clearFields(recordId, patientId, doctorId, diagnosis, treatment));

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int r = table.convertRowIndexToModel(row);
                recordId.setText(String.valueOf(model.getValueAt(r, 0)));
                patientId.setText(String.valueOf(model.getValueAt(r, 1)));
                doctorId.setText(String.valueOf(model.getValueAt(r, 2)));
                diagnosis.setText(String.valueOf(model.getValueAt(r, 3)));
                treatment.setText(String.valueOf(model.getValueAt(r, 4)));
            }
        });

        panel.add(formCard(form, add, update, delete, clear), BorderLayout.WEST);
        panel.add(tablePanel(table), BorderLayout.CENTER);
        refresh.run();
        return panel;
    }

    private JPanel createBillPanel() {
        JPanel panel = basePage("Medical Bill Management");

        JTextField billId = field();
        JTextField patientId = field();
        JTextField amount = field();
        DatePickerField billDate = dateField();
        JTextField paymentStatus = field();

        JPanel form = formPanel();
        addFormRow(form, "Bill ID", billId);
        addFormRow(form, "Patient ID", patientId);
        addFormRow(form, "Amount (BDT)", amount);
        addFormRow(form, "Bill Date  •  Click to choose", billDate);
        addFormRow(form, "Payment Status", paymentStatus);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Bill ID", "Patient ID", "Amount", "Bill Date", "Payment Status"}, 0
        );
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (MedicalBill b : hospital.getMedicalBillList()) {
                model.addRow(new Object[]{
                        b.getBill_id(), b.getPatient_id(), b.getAmount() + " BDT",
                        b.getBill_date(), b.getPayment_status()
                });
            }
            refreshDashboard();
        };

        JButton add = actionButton("Add");
        JButton update = actionButton("Update");
        JButton delete = actionButton("Delete");
        JButton clear = actionButton("Clear");

        add.addActionListener(e -> {
            try {
                int bid = integer(billId, "Bill ID");
                int pid = integer(patientId, "Patient ID");
                int am = integer(amount, "Amount");

                for (MedicalBill b : hospital.getMedicalBillList()) {
                    if (b.getBill_id() == bid) {
                        throw new CustomException("Medical Bill ID already exists.");
                    }
                }

                if (!patientExists(pid)) throw new CustomException("Patient ID does not exist.");

                hospital.getMedicalBillList().add(
                        new MedicalBill(
                                bid,
                                pid,
                                am,
                                required(billDate, "Bill Date"),
                                required(paymentStatus, "Payment Status")
                        )
                );

                fileManager.MedicalBillFile();
                refresh.run();
                clearFields(billId, patientId, amount, billDate, paymentStatus);
                success("Medical bill added successfully.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        update.addActionListener(e -> {
            try {
                int searchId = integer(billId, "Bill ID");
                MedicalBill found = null;

                for (MedicalBill b : hospital.getMedicalBillList()) {
                    if (b.getBill_id() == searchId) {
                        found = b;
                        break;
                    }
                }

                if (found == null) throw new CustomException("Medical Bill not found.");

                int pid = integer(patientId, "Patient ID");
                if (!patientExists(pid)) throw new CustomException("Patient ID does not exist.");

                found.setPatient_id(pid);
                found.setAmount(integer(amount, "Amount"));
                found.setBill_date(required(billDate, "Bill Date"));
                found.setPayment_status(required(paymentStatus, "Payment Status"));

                fileManager.MedicalBillFile();
                refresh.run();
                clearFields(billId, patientId, amount, billDate, paymentStatus);
                success("Medical bill updated successfully.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        delete.addActionListener(e -> {
            try {
                int searchId = integer(billId, "Bill ID");
                Iterator<MedicalBill> iterator = hospital.getMedicalBillList().iterator();

                while (iterator.hasNext()) {
                    if (iterator.next().getBill_id() == searchId) {
                        iterator.remove();
                        fileManager.MedicalBillFile();
                        refresh.run();
                        clearFields(billId, patientId, amount, billDate, paymentStatus);
                        success("Medical bill deleted successfully.");
                        return;
                    }
                }

                throw new CustomException("Medical Bill not found.");
            } catch (Exception ex) {
                error(ex.getMessage());
            }
        });

        clear.addActionListener(e -> clearFields(billId, patientId, amount, billDate, paymentStatus));

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int r = table.convertRowIndexToModel(row);
                billId.setText(String.valueOf(model.getValueAt(r, 0)));
                patientId.setText(String.valueOf(model.getValueAt(r, 1)));
                String amountText = String.valueOf(model.getValueAt(r, 2)).replace(" BDT", "");
                amount.setText(amountText);
                billDate.setText(String.valueOf(model.getValueAt(r, 3)));
                paymentStatus.setText(String.valueOf(model.getValueAt(r, 4)));
            }
        });

        panel.add(formCard(form, add, update, delete, clear), BorderLayout.WEST);
        panel.add(tablePanel(table), BorderLayout.CENTER);
        refresh.run();
        return panel;
    }

    private JPanel basePage(String title) {
        JPanel panel = new JPanel(new BorderLayout(18, 16));
        panel.setBackground(BG);
        panel.setBorder(new EmptyBorder(6, 22, 22, 22));

        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setOpaque(false);

        JPanel titleBox = new JPanel();
        titleBox.setOpaque(false);
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel(title);
        heading.setFont(new Font("SansSerif", Font.BOLD, 26));
        heading.setForeground(TEXT);

        JLabel subtitle = new JLabel("Manage hospital information with a clean and centralized workspace");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 11));
        subtitle.setForeground(MUTED);

        titleBox.add(heading);
        titleBox.add(Box.createVerticalStrut(4));
        titleBox.add(subtitle);

        headingPanel.add(titleBox, BorderLayout.WEST);

        JLabel hint = new JLabel("Select a table row to edit");
        hint.setForeground(MUTED);
        hint.setFont(new Font("SansSerif", Font.PLAIN, 10));
        headingPanel.add(hint, BorderLayout.EAST);

        panel.add(headingPanel, BorderLayout.NORTH);
        return panel;
    }

    private JPanel formPanel() {
        JPanel form = new JPanel();
        form.setOpaque(false);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        return form;
    }

    private JPanel formCard(JPanel form, JButton add, JButton update, JButton delete, JButton clear) {
        JPanel holder = new JPanel(new BorderLayout());
        holder.setOpaque(false);
        holder.setPreferredSize(new Dimension(348, 0));

        RoundedPanel card = new RoundedPanel(20);
        card.setBackground(CARD);
        card.setShadowEnabled(true);
        card.setBorder(new EmptyBorder(16, 16, 15, 16));
        card.setLayout(new BorderLayout(0, 8));

        JLabel formTitle = new JLabel("Information");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 15));
        formTitle.setForeground(TEXT);

        JPanel formHead = new JPanel(new BorderLayout());
        formHead.setOpaque(false);
        formHead.add(formTitle, BorderLayout.WEST);

        JPanel fields = new JPanel();
        fields.setOpaque(false);
        fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
        for (Component c : form.getComponents()) fields.add(c);

        // Keep the original Information layout exactly as before; only enable mouse-wheel scrolling.
        JScrollPane fieldsScroll = new JScrollPane(
                fields,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        // Keep the original Information look: no visible box/border around the scroll area.
        fields.setBorder(BorderFactory.createEmptyBorder());
        fieldsScroll.setBorder(BorderFactory.createEmptyBorder());
        fieldsScroll.setOpaque(false);
        fieldsScroll.setBackground(CARD);
        fieldsScroll.setViewportBorder(BorderFactory.createEmptyBorder());
        fieldsScroll.getViewport().setOpaque(false);
        fieldsScroll.getVerticalScrollBar().setUnitIncrement(16);
        fieldsScroll.getVerticalScrollBar().setPreferredSize(new Dimension(1, 0));
        fieldsScroll.getVerticalScrollBar().setOpaque(false);
        fieldsScroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        fieldsScroll.setWheelScrollingEnabled(true);

        // Invisible scrollbar track/thumb: scrolling still works with the mouse wheel.
        fieldsScroll.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(0, 0, 0, 0);
                trackColor = new Color(0, 0, 0, 0);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                button.setOpaque(false);
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }
        });

        JPanel buttonBar = new JPanel(new GridLayout(2, 2, 8, 8));
        buttonBar.setOpaque(false);
        buttonBar.setBorder(new EmptyBorder(8, 0, 0, 0));
        buttonBar.add(add);
        buttonBar.add(update);
        buttonBar.add(delete);
        buttonBar.add(clear);

        card.add(formHead, BorderLayout.NORTH);
        card.add(fieldsScroll, BorderLayout.CENTER);
        card.add(buttonBar, BorderLayout.SOUTH);
        holder.add(card, BorderLayout.CENTER);
        return holder;
    }

    private void addFormRow(JPanel panel, String label, JTextField field) {
        JLabel l = new JLabel(label);
        l.setFont(new Font("SansSerif", Font.BOLD, 11));
        l.setForeground(TEXT);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setPreferredSize(new Dimension(310, 37));
        field.setMinimumSize(new Dimension(310, 37));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 37));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(l);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(9));
    }

    private JPanel tablePanel(JTable table) {
        RoundedPanel panel = new RoundedPanel(20);
        panel.setBackground(CARD);
        panel.setShadowEnabled(true);
        panel.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel.setLayout(new BorderLayout());

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.setBorder(new EmptyBorder(14, 16, 10, 16));

        JLabel label = cardTitle("Data Overview");
        JLabel hint = new JLabel("Click any row to load details");
        hint.setFont(new Font("SansSerif", Font.PLAIN, 10));
        hint.setForeground(MUTED);
        top.add(label, BorderLayout.WEST);
        top.add(hint, BorderLayout.EAST);

        table.setRowHeight(40);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(225, 244, 237));
        table.setSelectionForeground(TEXT);
        table.setBackground(Color.WHITE);
        table.setForeground(TEXT);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);

        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 11));
        table.getTableHeader().setBackground(NAVY_TABLE_HEADER);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 42));
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        scrollPane.getViewport().setBackground(CARD);
        stylePremiumScrollPane(scrollPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        panel.add(top, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void stylePremiumScrollPane(JScrollPane scrollPane) {
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(14);

        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 8));

        javax.swing.plaf.basic.BasicScrollBarUI verticalUI = new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(181, 205, 196);
                this.trackColor = new Color(244, 249, 247);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return zeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return zeroButton();
            }
        };
        scrollPane.getVerticalScrollBar().setUI(verticalUI);

        javax.swing.plaf.basic.BasicScrollBarUI horizontalUI = new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(181, 205, 196);
                this.trackColor = new Color(244, 249, 247);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return zeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return zeroButton();
            }
        };
        scrollPane.getHorizontalScrollBar().setUI(horizontalUI);
    }

    private JButton zeroButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(false);
        return button;
    }

    private JButton actionButton(String text) {
        RoundedButton button = new RoundedButton(text, 12);
        button.setPreferredSize(new Dimension(120, 38));
        button.setFont(new Font("SansSerif", Font.BOLD, 11));

        if (text.equals("Add")) {
            button.setBackground(SUCCESS);
            button.setForeground(Color.WHITE);
        } else if (text.equals("Delete")) {
            button.setBackground(DANGER);
            button.setForeground(Color.WHITE);
        } else if (text.equals("Update")) {
            button.setBackground(PRIMARY);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(239, 242, 248));
            button.setForeground(TEXT);
        }
        return button;
    }

    private void stylePremiumTable(JTable table) {
        table.setRowHeight(34);
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.setForeground(TEXT);
        table.setBackground(Color.WHITE);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFillsViewportHeight(true);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(0, 38));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 11));
        table.getTableHeader().setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean selected,
                    boolean focus, int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        table, value, selected, focus, row, column);
                label.setOpaque(true);
                label.setBackground(NAVY_TABLE_HEADER);
                label.setForeground(Color.WHITE);
                label.setBorder(new EmptyBorder(0, 9, 0, 9));
                return label;
            }
        });

        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean selected,
                    boolean focus, int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        table, value, selected, focus, row, column);
                label.setOpaque(true);
                label.setFont(new Font("SansSerif", Font.PLAIN, 12));
                label.setForeground(TEXT);
                label.setBorder(new EmptyBorder(0, 9, 0, 9));
                label.setBackground(selected
                        ? new Color(225, 244, 237)
                        : (row % 2 == 0 ? Color.WHITE : ROW_ALT));
                return label;
            }
        });
    }

    private JTextField field() {
        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 12));
        field.setForeground(TEXT);
        field.setBackground(new Color(248, 252, 250));
        field.setCaretColor(PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
                new RoundedLineBorder(BORDER, 11),
                BorderFactory.createEmptyBorder(5, 11, 5, 11)
        ));
        field.setMargin(new Insets(5, 10, 5, 10));
        return field;
    }

    private DatePickerField dateField() {
        DatePickerField field = new DatePickerField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 12));
        field.setForeground(TEXT);
        field.setBackground(new Color(248, 252, 250));
        field.setCaretColor(PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
                new RoundedLineBorder(BORDER, 11),
                BorderFactory.createEmptyBorder(5, 11, 5, 11)
        ));
        field.setMargin(new Insets(5, 10, 5, 10));
        field.setToolTipText("Click here to select a date from the calendar");
        return field;
    }

    private class DatePickerField extends JTextField {
        DatePickerField() {
            super();
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        showDatePicker(DatePickerField.this);
                    }
                }
            });
        }

        @Override
        protected void processFocusEvent(java.awt.event.FocusEvent e) {
            super.processFocusEvent(e);
            if (e.getID() == java.awt.event.FocusEvent.FOCUS_GAINED) {
                setToolTipText("Click to choose a date from the calendar");
            }
        }
    }

    private void showDatePicker(Component owner) {
        DatePickerField target = (DatePickerField) owner;
        java.time.LocalDate initial = java.time.LocalDate.now();
        String existing = target.getText().trim();

        try {
            if (existing.matches("\\d{4}-\\d{2}-\\d{2}")) {
                initial = java.time.LocalDate.parse(existing);
            }
        } catch (Exception ignored) {
        }

        final java.time.LocalDate[] shownMonth = {
                java.time.YearMonth.from(initial).atDay(1)
        };

        JDialog dialog = new JDialog(this, "Select Date", false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);

        RoundedPanel root = new RoundedPanel(18);
        root.setBackground(Color.WHITE);
        root.setShadowEnabled(true);
        root.setBorder(new EmptyBorder(14, 14, 12, 14));
        root.setLayout(new BorderLayout(0, 10));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        RoundedButton prev = new RoundedButton("‹", 12);
        prev.setBackground(new Color(238, 244, 240));
        prev.setForeground(TEXT);
        prev.setPreferredSize(new Dimension(36, 34));

        RoundedButton next = new RoundedButton("›", 12);
        next.setBackground(new Color(238, 244, 240));
        next.setForeground(TEXT);
        next.setPreferredSize(new Dimension(36, 34));

        JLabel monthLabel = new JLabel("", SwingConstants.CENTER);
        monthLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        monthLabel.setForeground(TEXT);

        header.add(prev, BorderLayout.WEST);
        header.add(monthLabel, BorderLayout.CENTER);
        header.add(next, BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        JPanel calendar = new JPanel(new GridLayout(7, 7, 4, 4));
        calendar.setOpaque(false);
        root.add(calendar, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        actions.setOpaque(false);

        RoundedButton clear = new RoundedButton("Clear", 10);
        clear.setBackground(new Color(241, 243, 247));
        clear.setForeground(TEXT);
        clear.setPreferredSize(new Dimension(72, 32));

        RoundedButton today = new RoundedButton("Today", 10);
        today.setBackground(new Color(224, 247, 240));
        today.setForeground(SUCCESS.darker());
        today.setPreferredSize(new Dimension(72, 32));

        RoundedButton cancel = new RoundedButton("Cancel", 10);
        cancel.setBackground(new Color(241, 243, 247));
        cancel.setForeground(TEXT);
        cancel.setPreferredSize(new Dimension(78, 32));

        actions.add(clear);
        actions.add(today);
        actions.add(cancel);
        root.add(actions, BorderLayout.SOUTH);

        Runnable renderCalendar = new Runnable() {
            @Override
            public void run() {
                calendar.removeAll();

                String[] names = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                for (String name : names) {
                    JLabel day = new JLabel(name, SwingConstants.CENTER);
                    day.setFont(new Font("SansSerif", Font.BOLD, 10));
                    day.setForeground(MUTED);
                    calendar.add(day);
                }

                java.time.LocalDate firstDay = shownMonth[0];
                java.time.YearMonth ym = java.time.YearMonth.from(firstDay);
                monthLabel.setText(firstDay.getMonth().toString().substring(0, 1)
                        + firstDay.getMonth().toString().substring(1).toLowerCase()
                        + " " + firstDay.getYear());

                int start = firstDay.getDayOfWeek().getValue() % 7;
                int maxDay = ym.lengthOfMonth();
                java.time.LocalDate selected = null;
                try {
                    String txt = target.getText().trim();
                    if (txt.matches("\\d{4}-\\d{2}-\\d{2}")) selected = java.time.LocalDate.parse(txt);
                } catch (Exception ignored) {
                }

                for (int i = 0; i < 42; i++) {
                    int number = i - start + 1;
                    if (number < 1 || number > maxDay) {
                        calendar.add(new JLabel(""));
                        continue;
                    }

                    final java.time.LocalDate chosen = ym.atDay(number);
                    RoundedButton day = new RoundedButton(String.valueOf(number), 10);
                    day.setFont(new Font("SansSerif", Font.PLAIN, 11));
                    day.setBackground(Color.WHITE);
                    day.setForeground(TEXT);
                    day.setPreferredSize(new Dimension(42, 32));

                    if (chosen.equals(java.time.LocalDate.now())) {
                        day.setBackground(new Color(224, 247, 240));
                        day.setForeground(TEAL_DARK());
                    }
                    if (selected != null && chosen.equals(selected)) {
                        day.setBackground(SUCCESS);
                        day.setForeground(Color.WHITE);
                        day.setFont(new Font("SansSerif", Font.BOLD, 11));
                    }

                    day.addActionListener(e -> {
                        target.setText(chosen.toString());
                        target.requestFocusInWindow();
                        dialog.dispose();
                    });
                    calendar.add(day);
                }

                calendar.revalidate();
                calendar.repaint();
                dialog.pack();
            }
        };

        prev.addActionListener(e -> {
            shownMonth[0] = shownMonth[0].minusMonths(1);
            renderCalendar.run();
        });

        next.addActionListener(e -> {
            shownMonth[0] = shownMonth[0].plusMonths(1);
            renderCalendar.run();
        });

        today.addActionListener(e -> {
            target.setText(java.time.LocalDate.now().toString());
            target.requestFocusInWindow();
            dialog.dispose();
        });

        clear.addActionListener(e -> {
            target.setText("");
            target.requestFocusInWindow();
            dialog.dispose();
        });

        cancel.addActionListener(e -> dialog.dispose());

        dialog.setContentPane(root);
        renderCalendar.run();
        dialog.setSize(360, 365);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private Color TEAL_DARK() {
        return new Color(14, 120, 89);
    }

    private String required(JTextField field, String name) throws CustomException {
        String value = field.getText().trim();
        if (value.isEmpty()) {
            throw new CustomException(name + " cannot be empty.");
        }
        return value;
    }

    private int integer(JTextField field, String name) throws CustomException {
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (NumberFormatException e) {
            throw new CustomException(name + " must be a valid number.");
        }
    }

    private boolean patientExists(int id) {
        for (Patient p : hospital.getPatientList()) {
            if (p.getID() == id) return true;
        }
        return false;
    }

    private boolean doctorExists(int id) {
        for (Doctor d : hospital.getDoctorList()) {
            if (d.getID() == id) return true;
        }
        return false;
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private void success(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void error(String message) {
        if (message == null || message.trim().isEmpty()) {
            message = "Invalid input.";
        }
        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void setActiveMenuByPage(String page) {
        if ("Dashboard".equals(page) && dashboardMenuButton != null) {
            setActiveMenu(dashboardMenuButton);
        } else if ("Schedule".equals(page) && scheduleMenuButton != null) {
            setActiveMenu(scheduleMenuButton);
        }
    }

    private void showPage(String page) {
        cardLayout.show(contentPanel, page);
        if ("Schedule".equals(page)) {
            refreshScheduleTable();
            refreshReminders();
        }
    }

    private void refreshDashboard() {
        if (patientCount != null) {
            patientCount.setText(String.valueOf(hospital.getPatientList().size()));
            doctorCount.setText(String.valueOf(hospital.getDoctorList().size()));
            appointmentCount.setText(String.valueOf(hospital.getAppointmentList().size()));
            recordCount.setText(String.valueOf(hospital.getMedicalRecordList().size()));
            billCount.setText(String.valueOf(hospital.getMedicalBillList().size()));

            if (livePatientMetric != null) {
                livePatientMetric.setText(String.valueOf(hospital.getPatientList().size()));
                liveDoctorMetric.setText(String.valueOf(hospital.getDoctorList().size()));
                liveAppointmentMetric.setText(String.valueOf(hospital.getAppointmentList().size()));
            }

            if (liveActivityChart != null) {
                liveActivityChart.updateData(
                        hospital.getPatientList().size(),
                        hospital.getDoctorList().size(),
                        hospital.getMedicalRecordList().size(),
                        hospital.getAppointmentList().size(),
                        hospital.getMedicalBillList().size()
                );
            }

            refreshScheduleTable();
            refreshAppointmentQueue();
            refreshMedicalBillQueue();
            refreshReminders();
        }
    }

    private static class AvatarLabel extends JLabel {
        private final int diameter;
        private final Color fill;

        AvatarLabel(String text, int diameter, Color fill) {
            super(text, SwingConstants.CENTER);
            this.diameter = diameter;
            this.fill = fill;
            setPreferredSize(new Dimension(diameter, diameter));
            setMinimumSize(new Dimension(diameter, diameter));
            setMaximumSize(new Dimension(diameter, diameter));
            setFont(new Font("SansSerif", Font.BOLD, Math.max(12, diameter / 3)));
            setForeground(Color.WHITE);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(fill);
            g2.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
            g2.setColor(new Color(255, 255, 255, 170));
            g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private class ActivityChart extends JPanel {
        private int[] data = new int[]{0, 0, 0, 0};

        ActivityChart() {
            setOpaque(false);
            setPreferredSize(new Dimension(0, 150));
        }

        void setData(int patients, int records, int appointments, int bills) {
            data = new int[]{patients, records, appointments, bills};
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int left = 8;
            int right = w - 8;
            int bottom = h - 24;
            int top = 16;

            g2.setColor(new Color(205, 214, 228));
            g2.drawLine(left, bottom, right, bottom);
            g2.drawLine(left, top, left, bottom);

            int max = 1;
            for (int value : data) max = Math.max(max, value);

            int[] xs = new int[data.length];
            int[] ys = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                xs[i] = left + i * (right - left) / (data.length - 1);
                ys[i] = bottom - (data[i] * (bottom - top - 8) / max);
            }

            g2.setColor(PRIMARY);
            g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (int i = 0; i < xs.length - 1; i++) {
                g2.drawLine(xs[i], ys[i], xs[i + 1], ys[i + 1]);
            }
            for (int i = 0; i < xs.length; i++) {
                g2.fillOval(xs[i] - 4, ys[i] - 4, 8, 8);
            }

            String[] labels = {"Patients", "Records", "Appointments", "Bills"};
            g2.setFont(new Font("SansSerif", Font.PLAIN, 9));
            g2.setColor(MUTED);
            for (int i = 0; i < labels.length; i++) {
                int sw = g2.getFontMetrics().stringWidth(labels[i]);
                g2.drawString(labels[i], xs[i] - sw / 2, h - 6);
            }
            g2.dispose();
        }
    }

    private class LiveActivityChart extends JPanel {
        private final java.util.List<int[]> history = new java.util.ArrayList<>();
        private double phase = 0.0;
        private final Timer timer;
        private int sampleNumber = 0;

        private final Color PATIENT_LINE = new Color(20, 151, 112);
        private final Color DOCTOR_LINE = new Color(47, 127, 192);
        private final Color RECORD_LINE = new Color(151, 91, 194);
        private final Color APPOINTMENT_LINE = new Color(214, 145, 47);
        private final Color BILL_LINE = new Color(215, 82, 104);

        LiveActivityChart() {
            setOpaque(false);
            setPreferredSize(new Dimension(0, 185));

            timer = new Timer(60, e -> {
                phase += 0.055;
                repaint();
            });
            timer.start();
        }

        void initializeData(int patients, int doctors, int records, int appointments, int bills) {
            history.clear();
            int[] snapshot = snapshot(patients, doctors, records, appointments, bills);
            // Start with several samples so the graph is visible immediately.
            for (int i = 0; i < 8; i++) {
                history.add(snapshot.clone());
            }
            sampleNumber = history.size();
            repaint();
        }

        void updateData(int patients, int doctors, int records, int appointments, int bills) {
            if (history.isEmpty()) {
                initializeData(patients, doctors, records, appointments, bills);
                return;
            }

            int[] latest = snapshot(patients, doctors, records, appointments, bills);
            history.add(latest);
            sampleNumber++;

            // Keep the graph clean and prevent unlimited memory growth.
            if (history.size() > 32) {
                history.remove(0);
            }
            repaint();
        }

        private int[] snapshot(int patients, int doctors, int records, int appointments, int bills) {
            return new int[]{
                    Math.max(0, patients),
                    Math.max(0, doctors),
                    Math.max(0, records),
                    Math.max(0, appointments),
                    Math.max(0, bills)
            };
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int left = 12;
            int right = Math.max(left + 20, w - 12);
            int top = 12;
            int bottom = Math.max(top + 20, h - 34);

            // Grid lines
            g2.setStroke(new BasicStroke(1f));
            g2.setColor(new Color(222, 235, 229));
            for (int i = 0; i < 4; i++) {
                int y = top + i * (bottom - top) / 3;
                g2.drawLine(left, y, right, y);
            }

            if (history.size() < 2) {
                g2.dispose();
                return;
            }

            int max = 1;
            for (int[] sample : history) {
                for (int value : sample) {
                    max = Math.max(max, value);
                }
            }

            Color[] seriesColors = {
                    PATIENT_LINE,
                    DOCTOR_LINE,
                    RECORD_LINE,
                    APPOINTMENT_LINE,
                    BILL_LINE
            };

            String[] labels = {
                    "Patients",
                    "Doctors",
                    "Records",
                    "Appointments",
                    "Bills"
            };

            // Draw each historical series.
            for (int series = 0; series < 5; series++) {
                g2.setColor(seriesColors[series]);
                g2.setStroke(new BasicStroke(
                        series == 0 || series == 1 ? 2.4f : 1.5f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                ));

                for (int i = 0; i < history.size() - 1; i++) {
                    int x1 = left + i * (right - left) / (history.size() - 1);
                    int x2 = left + (i + 1) * (right - left) / (history.size() - 1);

                    int y1 = bottom - (history.get(i)[series] * (bottom - top - 8) / max);
                    int y2 = bottom - (history.get(i + 1)[series] * (bottom - top - 8) / max);

                    g2.drawLine(x1, y1, x2, y2);
                }
            }

            // Animated vertical live cursor.
            int liveIndex = (int) ((phase * 1.8) % history.size());
            int liveX = left + liveIndex * (right - left) / (history.size() - 1);

            g2.setColor(new Color(20, 151, 112, 55));
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawLine(liveX, top, liveX, bottom);

            int liveValue = history.get(liveIndex)[0];
            int liveY = bottom - (liveValue * (bottom - top - 8) / max);

            g2.setColor(new Color(20, 151, 112, 35));
            g2.fillOval(liveX - 12, liveY - 12, 24, 24);
            g2.setColor(SUCCESS);
            g2.fillOval(liveX - 5, liveY - 5, 10, 10);

            // Legend
            int legendX = left;
            int legendY = h - 10;
            g2.setFont(new Font("SansSerif", Font.PLAIN, 9));
            for (int i = 0; i < labels.length; i++) {
                g2.setColor(seriesColors[i]);
                g2.fillRoundRect(legendX, legendY - 8, 9, 4, 4, 4);
                legendX += 14;

                g2.setColor(MUTED);
                g2.drawString(labels[i], legendX, legendY);
                legendX += g2.getFontMetrics().stringWidth(labels[i]) + 14;

                if (legendX > right - 50) {
                    break;
                }
            }

            // Small live status text.
            String status = "Live samples: " + sampleNumber;
            int statusWidth = g2.getFontMetrics().stringWidth(status);
            g2.setColor(MUTED);
            g2.drawString(status, right - statusWidth, top - 1);

            g2.dispose();
        }
    }

    private static class RoundedButton extends JButton {
        private final int radius;
        RoundedButton(String text, int radius) {
            super(text); this.radius = radius;
            setFont(new Font("SansSerif", Font.BOLD, 12));
            setFocusPainted(false); setBorderPainted(false); setContentAreaFilled(false); setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2=(Graphics2D)g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color c=getBackground();
            if(getModel().isPressed()) {
                c=c.darker();
            } else if(getModel().isRollover()) {
                c = new Color(
                        Math.min(255, c.getRed() + 8),
                        Math.min(255, c.getGreen() + 8),
                        Math.min(255, c.getBlue() + 8)
                );
            }
            g2.setColor(c); g2.fillRoundRect(0,0,getWidth(),getHeight(),radius,radius); g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class RoundedPanel extends JPanel {
        private final int radius;
        private boolean shadowEnabled = false;

        RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        void setShadowEnabled(boolean enabled) {
            shadowEnabled = enabled;
            setOpaque(false);
            setBorder(getBorder());
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            int inset = shadowEnabled ? 2 : 0;
            if (shadowEnabled) {
                g2.setColor(SOFT_SHADOW);
                g2.fillRoundRect(1, 2, Math.max(0, getWidth() - 2), Math.max(0, getHeight() - 1), radius + 2, radius + 2);
            }

            g2.setColor(getBackground());
            g2.fillRoundRect(inset, 0, Math.max(0, getWidth() - inset * 2), Math.max(0, getHeight() - inset), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class GradientPanel extends JPanel {
        private final Color start;
        private final Color end;

        GradientPanel(Color start, Color end) {
            this.start = start;
            this.end = end;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gradient = new GradientPaint(0, 0, start, getWidth(), 0, end);
            g2.setPaint(gradient);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class RoundedLineBorder extends javax.swing.border.AbstractBorder {
        private final Color color; private final int radius;
        RoundedLineBorder(Color color,int radius){this.color=color;this.radius=radius;}
        public void paintBorder(Component c,Graphics g,int x,int y,int w,int h){
            Graphics2D g2=(Graphics2D)g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color); g2.drawRoundRect(x,y,w-1,h-1,radius,radius); g2.dispose();
        }
        public Insets getBorderInsets(Component c){return new Insets(8,10,8,10);}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                UIManager.put("nimbusBase", PRIMARY_DARK);
                UIManager.put("nimbusFocus", PRIMARY);
                UIManager.put("control", BG);
                UIManager.put("text", TEXT);
                UIManager.put("nimbusSelectionBackground", PRIMARY);
                UIManager.put("nimbusSelectedText", Color.WHITE);
            } catch (Exception ignored) {
            }

            new Driver().setVisible(true);
        });
    }
}

