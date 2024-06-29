//THIS IS GUI VERSION 2.0 WITH ENHANCED INTERFACE LOOK 
//and it is working properly
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Base64;
import javax.crypto.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;

class EncodeDecode {
    public static String encrypt(String plaintext, String key) throws Exception {
        SecretKey secretKey = createKey(key);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, String key) throws Exception {
        SecretKey secretKey = createKey(key);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static SecretKey createKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] truncatedKeyBytes = Arrays.copyOf(keyBytes, 8);
        return new SecretKeySpec(truncatedKeyBytes, "DES");
    }

    public static String fileToText(String filePath) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            return Base64.getEncoder().encodeToString(fileBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean textToFile(String text, String filePath) {
        try {
            byte[] textBytes = Base64.getDecoder().decode(text);
            Files.write(Paths.get(filePath), textBytes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static SecretKey generateDESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        return keyGenerator.generateKey();
    }
}

public class FileEncodeGUI extends JFrame {
    private JButton encryptButton;
    private JButton decryptButton;
    private JButton generateKeyButton;
    private JButton exitButton;

    public FileEncodeGUI() {
        setTitle("File Encoder");
        setSize(500, 300); // Increased height for better layout
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.WHITE); // Set background color

        // Add the title label
        JLabel titleLabel = new JLabel("File Encoder");
        titleLabel.setForeground(Color.BLACK); // Set text color to black
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0); // Add some spacing
        panel.add(titleLabel, gbc);

        // Create buttons
        encryptButton = createStyledButton("Encrypt File");
        decryptButton = createStyledButton("Decrypt File");
        generateKeyButton = createStyledButton("Generate Sample Key");
        exitButton = createStyledButton("Exit");
        exitButton.setBackground(Color.RED); // Set exit button color to red

        // Add buttons to panel
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10); // Adjust spacing
        gbc.gridy = 1;
        panel.add(encryptButton, gbc);
        gbc.gridx = 1;
        panel.add(decryptButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(generateKeyButton, gbc);
        gbc.gridx = 1;
        panel.add(exitButton, gbc);

        add(panel);
        setVisible(true);

        // Attach action listeners to buttons
        attachButtonListeners();
    }

    // Method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(59, 89, 182));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 40)); // Set button size
        return button;
    }

    // Attach action listeners to buttons
    private void attachButtonListeners() {
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filePath = selectFile();
                if (filePath != null) {
                    String[] keys = enterKeys();
                    if (keys != null) {
                        try {
                            String text = EncodeDecode.fileToText(filePath);
                            String key = keys[0] + keys[1]; // Combine key parts
                            String encryptedText = EncodeDecode.encrypt(text, key);
                            EncodeDecode.textToFile(encryptedText, filePath);
                            JOptionPane.showMessageDialog(null, "File has been Encrypted.");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filePath = selectFile();
                if (filePath != null) {
                    String[] keys = enterKeys();
                    if (keys != null) {
                        try {
                            String text = EncodeDecode.fileToText(filePath);
                            String key = keys[0] + keys[1]; // Combine key parts
                            String decryptedText = EncodeDecode.decrypt(text, key);
                            EncodeDecode.textToFile(decryptedText, filePath);
                            JOptionPane.showMessageDialog(null, "File has been Decrypted.");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        generateKeyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    SecretKey desKey1 = EncodeDecode.generateDESKey();
                    String key = Base64.getEncoder().encodeToString(desKey1.getEncoded());
                    String key1 = key.substring(0, 6);
                    String key2 = key.substring(6, 12);
                    JPanel panel = new JPanel(new GridLayout(4, 1));
                    panel.add(new JLabel("Key part1: " + key1));
                    JButton copyButton1 = new JButton("Copy Key Part 1");
                    panel.add(copyButton1);
                    panel.add(new JLabel("Key part2: " + key2));
                    JButton copyButton2 = new JButton("Copy Key Part 2");
                    panel.add(copyButton2);

                    copyButton1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            StringSelection stringSelection = new StringSelection(key1);
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            clipboard.setContents(stringSelection, null);
                            JOptionPane.showMessageDialog(null, "Key part 1 has been copied to clipboard.");
                        }
                    });

                    copyButton2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            StringSelection stringSelection = new StringSelection(key2);
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            clipboard.setContents(stringSelection, null);
                            JOptionPane.showMessageDialog(null, "Key part 2 has been copied to clipboard.");
                        }
                    });

                    JOptionPane.showMessageDialog(null, panel);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private String selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    private String[] enterKeys() {
        JTextField keyField1 = new JTextField();
        JTextField keyField2 = new JTextField();
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Enter Key Part 1:"));
        panel.add(keyField1);
        panel.add(new JLabel("Enter Key Part 2:"));
        panel.add(keyField2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Keys", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String key1 = keyField1.getText();
            String key2 = keyField2.getText();
            if (key1.isEmpty() || key2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Keys cannot be empty.");
                return null;
            } else if (key1.length() != 6 || key2.length() != 6) {
                JOptionPane.showMessageDialog(null, "Key parts must be 6 characters long.");
                return null;
            } else {
                return new String[]{key1, key2};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileEncodeGUI();
            }
        });
    }
}


//below code is normal gui , simple design
// /// this is now done as a hui, it has no errors
// // use this code freely for now, 
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.util.Base64;
// import javax.crypto.*;
// import java.awt.datatransfer.StringSelection;
// import java.awt.datatransfer.Clipboard;
// import java.awt.Toolkit;
// import java.nio.charset.StandardCharsets;
// import javax.crypto.spec.SecretKeySpec;
// import java.util.Arrays;
// import java.nio.file.Files;
// import java.nio.file.Paths;

// class EncodeDecode {
//     public static String encrypt(String plaintext, String key) throws Exception {
//         SecretKey secretKey = createKey(key);
//         Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//         cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//         byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
//         return Base64.getEncoder().encodeToString(encryptedBytes);
//     }

//     public static String decrypt(String encryptedText, String key) throws Exception {
//         SecretKey secretKey = createKey(key);
//         Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//         cipher.init(Cipher.DECRYPT_MODE, secretKey);
//         byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
//         return new String(decryptedBytes, StandardCharsets.UTF_8);
//     }

//     private static SecretKey createKey(String key) {
//         byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
//         byte[] truncatedKeyBytes = Arrays.copyOf(keyBytes, 8);
//         return new SecretKeySpec(truncatedKeyBytes, "DES");
//     }

//     public static String fileToText(String filePath) {
//         try {
//             byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
//             return Base64.getEncoder().encodeToString(fileBytes);
//         } catch (Exception e) {
//             e.printStackTrace();
//             return null;
//         }
//     }

//     public static boolean textToFile(String text, String filePath) {
//         try {
//             byte[] textBytes = Base64.getDecoder().decode(text);
//             Files.write(Paths.get(filePath), textBytes);
//             return true;
//         } catch (Exception e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public static SecretKey generateDESKey() throws Exception {
//         KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
//         return keyGenerator.generateKey();
//     }
// }

// public class FileEncodeGUI extends JFrame {
//     private JButton encryptButton;
//     private JButton decryptButton;
//     private JButton generateKeyButton;
//     private JButton exitButton;

//     public FileEncodeGUI() {
//         setTitle("File Encode");
//         setSize(400, 200);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         JPanel panel = new JPanel();
//         panel.setLayout(new GridLayout(4, 1));

//         encryptButton = new JButton("Encrypt File");
//         decryptButton = new JButton("Decrypt File");
//         generateKeyButton = new JButton("Generate Sample Key");
//         exitButton = new JButton("Exit");

//         panel.add(encryptButton);
//         panel.add(decryptButton);
//         panel.add(generateKeyButton);
//         panel.add(exitButton);

//         encryptButton.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 String filePath = selectFile();
//                 if (filePath != null) {
//                     String[] keys = enterKeys();
//                     if (keys != null) {
//                         try {
//                             String text = EncodeDecode.fileToText(filePath);
//                             String key = keys[0] + keys[1]; // Combine key parts
//                             String encryptedText = EncodeDecode.encrypt(text, key);
//                             EncodeDecode.textToFile(encryptedText, filePath);
//                             JOptionPane.showMessageDialog(null, "File has been Encrypted.");
//                         } catch (Exception ex) {
//                             JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
//                         }
//                     }
//                 }
//             }
//         });

//         decryptButton.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 String filePath = selectFile();
//                 if (filePath != null) {
//                     String[] keys = enterKeys();
//                     if (keys != null) {
//                         try {
//                             String text = EncodeDecode.fileToText(filePath);
//                             String key = keys[0] + keys[1]; // Combine key parts
//                             String decryptedText = EncodeDecode.decrypt(text, key);
//                             EncodeDecode.textToFile(decryptedText, filePath);
//                             JOptionPane.showMessageDialog(null, "File has been Decrypted.");
//                         } catch (Exception ex) {
//                             JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
//                         }
//                     }
//                 }
//             }
//         });

//         generateKeyButton.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 try {
//                     SecretKey desKey1 = EncodeDecode.generateDESKey();
//                     String key = Base64.getEncoder().encodeToString(desKey1.getEncoded());
//                     String key1 = key.substring(0, 6);
//                     String key2 = key.substring(6, 12);
//                     JPanel panel = new JPanel(new GridLayout(4, 1));
//                     panel.add(new JLabel("Key part1: " + key1));
//                     JButton copyButton1 = new JButton("Copy Key Part 1");
//                     panel.add(copyButton1);
//                     panel.add(new JLabel("Key part2: " + key2));
//                     JButton copyButton2 = new JButton("Copy Key Part 2");
//                     panel.add(copyButton2);

//                     copyButton1.addActionListener(new ActionListener() {
//                         public void actionPerformed(ActionEvent e) {
//                             StringSelection stringSelection = new StringSelection(key1);
//                             Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//                             clipboard.setContents(stringSelection, null);
//                             JOptionPane.showMessageDialog(null, "Key part 1 has been copied to clipboard.");
//                         }
//                     });

//                     copyButton2.addActionListener(new ActionListener() {
//                         public void actionPerformed(ActionEvent e) {
//                             StringSelection stringSelection = new StringSelection(key2);
//                             Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//                             clipboard.setContents(stringSelection, null);
//                             JOptionPane.showMessageDialog(null, "Key part 2 has been copied to clipboard.");
//                         }
//                     });

//                     JOptionPane.showMessageDialog(null, panel);
//                 } catch (Exception ex) {
//                     JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
//                 }
//             }
//         });

//         exitButton.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 System.exit(0);
//             }
//         });

//         add(panel);
//         setVisible(true);
//     }

//     private String selectFile() {
//         JFileChooser fileChooser = new JFileChooser();
//         int result = fileChooser.showOpenDialog(null);
//         if (result == JFileChooser.APPROVE_OPTION) {
//             return fileChooser.getSelectedFile().getAbsolutePath();
//         }
//         return null;
//     }

//     private String[] enterKeys() {
//         JTextField keyField1 = new JTextField();
//         JTextField keyField2 = new JTextField();
//         JPanel panel = new JPanel(new GridLayout(2, 2));
//         panel.add(new JLabel("Enter Key Part 1:"));
//         panel.add(keyField1);
//         panel.add(new JLabel("Enter Key Part 2:"));
//         panel.add(keyField2);
//         int result = JOptionPane.showConfirmDialog(null, panel, "Enter Keys", JOptionPane.OK_CANCEL_OPTION);
//         if (result == JOptionPane.OK_OPTION) {
//             String key1 = keyField1.getText();
//             String key2 = keyField2.getText();
//             if (key1.isEmpty() || key2.isEmpty()) {
//                 JOptionPane.showMessageDialog(null, "Keys cannot be empty.");
//                 return null;
//             } else if (key1.length() != 6 || key2.length() != 6) {
//                 JOptionPane.showMessageDialog(null, "Key parts must be 6 characters long.");
//                 return null;
//             } else {
//                 return new String[]{key1, key2};
//             }
//         }
//         return null;
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 new FileEncodeGUI();
//             }
//         });
//     }
// }
