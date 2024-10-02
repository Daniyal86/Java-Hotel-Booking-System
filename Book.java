import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private JFrame frame;
    private JTextField nameField;
    private JComboBox<String> roomTypeComboBox;
    private JComboBox<String> roomIdComboBox;
    private JTextField bookDateField;
    private JTextField checkOutDateField;
    private JTextField totalBillField;
    private JComboBox<String> billStatusComboBox;
    private JButton btnSaveBooking;
    private JButton btnShowBookedRooms;
    private JButton btnGoToBooking;
    private List<RoomBooking> bookedRooms;
    private JPanel backgroundPanel;

    public Book() {
        bookedRooms = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Hotel Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("pic.jpg"); // Assuming pic.jpg is in the project root directory
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        buttonPanel.setOpaque(false);

        btnGoToBooking = new JButton("Go to Booking");
        customizeButton(btnGoToBooking, Color.WHITE, new Color(26, 36, 113));
        btnGoToBooking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showBookingScreen();
            }
        });
        buttonPanel.add(btnGoToBooking);

        btnShowBookedRooms = new JButton("Show Booked Rooms");
        customizeButton(btnShowBookedRooms, Color.WHITE, new Color(26, 36, 113));
        btnShowBookedRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showBookedRoomsScreen();
            }
        });
        buttonPanel.add(btnShowBookedRooms);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.setContentPane(backgroundPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void showBookedRoomsScreen() {
        JPanel bookedRoomsPanel = new JPanel(new BorderLayout());

        String[] columnNames = { "User Name", "Room Type", "Room ID", "Book Date", "Check Out Date", "Total Bill",
                "Bill Status" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        for (RoomBooking booking : bookedRooms) {
            Object[] rowData = { booking.getUserName(), booking.getRoomType(), booking.getRoomId(),
                    booking.getBookDate(),
                    booking.getCheckOutDate(), booking.getTotalBill(), booking.getBillStatus() };
            model.addRow(rowData);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        bookedRoomsPanel.add(scrollPane, BorderLayout.CENTER);

        JButton btnBackToHome = new JButton("Back to Home");
        customizeButton(btnBackToHome, new Color(59, 89, 182), new Color(26, 36, 113));
        btnBackToHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(backgroundPanel);
                frame.revalidate();
            }
        });
        bookedRoomsPanel.add(btnBackToHome, BorderLayout.SOUTH);

        frame.setContentPane(bookedRoomsPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void showBookingScreen() {
        JPanel bookingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        bookingPanel.add(new JLabel("Name:"), gbc);
        nameField = new JTextField(20);
        nameField.setFont(nameField.getFont().deriveFont(Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        bookingPanel.add(nameField, gbc);

        // Room Type
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        bookingPanel.add(new JLabel("Room Type:"), gbc);
        roomTypeComboBox = new JComboBox<>(new String[] { "Single", "Double", "Suite" });
        roomTypeComboBox.setFont(roomTypeComboBox.getFont().deriveFont(Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        bookingPanel.add(roomTypeComboBox, gbc);

        // Room ID
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        bookingPanel.add(new JLabel("Room ID:"), gbc);
        roomIdComboBox = new JComboBox<>(new String[] { "101", "102", "103", "201", "202", "203" });
        roomIdComboBox.setFont(roomIdComboBox.getFont().deriveFont(Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        bookingPanel.add(roomIdComboBox, gbc);

        // Book Date
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        bookingPanel.add(new JLabel("Book Date (dd-MM-yyyy):"), gbc);
        bookDateField = new JTextField(10);
        bookDateField.setFont(bookDateField.getFont().deriveFont(Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        bookingPanel.add(bookDateField, gbc);

        // Check Out Date
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        bookingPanel.add(new JLabel("Check Out Date (dd-MM-yyyy):"), gbc);
        checkOutDateField = new JTextField(10);
        checkOutDateField.setFont(checkOutDateField.getFont().deriveFont(Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        bookingPanel.add(checkOutDateField, gbc);

        // Total Bill
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        bookingPanel.add(new JLabel("Total Bill:"), gbc);
        totalBillField = new JTextField(10);
        totalBillField.setFont(totalBillField.getFont().deriveFont(Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        bookingPanel.add(totalBillField, gbc);

        // Generate Bill Button
        JButton btnGenerateBill = new JButton("Generate Bill");
        btnGenerateBill.setFont(btnGenerateBill.getFont().deriveFont(Font.PLAIN, 14));
        btnGenerateBill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bookingPanel.add(btnGenerateBill, gbc);

        // Bill Status
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        bookingPanel.add(new JLabel("Bill Status:"), gbc);
        billStatusComboBox = new JComboBox<>(new String[] { "Paid", "Unpaid" });
        billStatusComboBox.setFont(billStatusComboBox.getFont().deriveFont(Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        bookingPanel.add(billStatusComboBox, gbc);

        // Save Booking Button
        btnSaveBooking = new JButton("Save Booking");
        btnSaveBooking.setFont(btnSaveBooking.getFont().deriveFont(Font.BOLD, 14));
        btnSaveBooking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveBooking();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        bookingPanel.add(btnSaveBooking, gbc);

        // Back to Home Button
        JButton btnBackToHome = new JButton("Back to Home");
        btnBackToHome.setFont(btnBackToHome.getFont().deriveFont(Font.BOLD, 14));
        btnBackToHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(backgroundPanel);
                frame.revalidate();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        bookingPanel.add(btnBackToHome, gbc);

        frame.setContentPane(bookingPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void saveBooking() {
        String userName = nameField.getText();
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        String roomId = (String) roomIdComboBox.getSelectedItem();
        String bookDate = bookDateField.getText();
        String checkOutDate = checkOutDateField.getText();
        double totalBill;
        try {
            totalBill = Double.parseDouble(totalBillField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid total bill amount.");
            return;
        }
        String billStatus = (String) billStatusComboBox.getSelectedItem();
        RoomBooking booking = new RoomBooking(userName, roomType, roomId, bookDate, checkOutDate, totalBill,
                billStatus);
        bookedRooms.add(booking);
        JOptionPane.showMessageDialog(frame, "Booking Saved Successfully!");
    }

    private void generateBill() {
        try {
            double totalBill = calculateTotalBill();
            totalBillField.setText(String.valueOf(totalBill));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid check-in and check-out dates.");
        }
    }

    private double calculateTotalBill() {
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        double roomRate = getRoomRate(roomType);
        long stayDays = calculateStayDays();
        return roomRate * stayDays;
    }

    private long calculateStayDays() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            long diff = format.parse(checkOutDateField.getText()).getTime()
                    - format.parse(bookDateField.getText()).getTime();
            return diff / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            throw new NumberFormatException();
        }
    }

    private double getRoomRate(String roomType) {
        switch (roomType) {
            case "Single":
                return 100.0;
            case "Double":
                return 150.0;
            case "Suite":
                return 200.0;
            default:
                return 0.0;
        }
    }

    private void customizeButton(JButton button, Color backgroundColor, Color foregroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setFont(button.getFont().deriveFont(Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 40));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.brighter());
                button.setForeground(Color.BLACK); // Change text color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
                button.setForeground(foregroundColor);
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Book();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class RoomBooking {
        private String userName;
        private String roomType;
        private String roomId;
        private String bookDate;
        private String checkOutDate;
        private double totalBill;
        private String billStatus;

        public RoomBooking(String userName, String roomType, String roomId, String bookDate, String checkOutDate,
                double totalBill, String billStatus) {
            this.userName = userName;
            this.roomType = roomType;
            this.roomId = roomId;
            this.bookDate = bookDate;
            this.checkOutDate = checkOutDate;
            this.totalBill = totalBill;
            this.billStatus = billStatus;
        }

        public String getUserName() {
            return userName;
        }

        public String getRoomType() {
            return roomType;
        }

        public String getRoomId() {
            return roomId;
        }

        public String getBookDate() {
            return bookDate;
        }

        public String getCheckOutDate() {
            return checkOutDate;
        }

        public double getTotalBill() {
            return totalBill;
        }

        public String getBillStatus() {
            return billStatus;
        }
    }
}
