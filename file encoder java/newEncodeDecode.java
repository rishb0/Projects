// // this code is a updated ersion of using 3ddes direcctly,  is previous EncodeDecode.java
// // we were suging des 3times to do 3des, but now we directky using it , 
// // 16 march 2024

// import java.util.Scanner;
// import javax.crypto.*;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.security.NoSuchAlgorithmException;
// import java.util.Base64;
// import javax.crypto.spec.SecretKeySpec;
// import java.nio.charset.StandardCharsets;
// import java.util.InputMismatchException;

// class TripleDES {
//     public static String encrypt(String plaintext, String key) throws Exception {
//         SecretKey secretKey = createKey(key);
//         Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
//         cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//         byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
//         return Base64.getEncoder().encodeToString(encryptedBytes);
//     }

//     public static String decrypt(String encryptedText, String key) throws Exception {
//         SecretKey secretKey = createKey(key);
//         Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
//         cipher.init(Cipher.DECRYPT_MODE, secretKey);
//         byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
//         return new String(decryptedBytes, StandardCharsets.UTF_8);
//     }

//     private static SecretKey createKey(String key) {
//         byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
//         return new SecretKeySpec(keyBytes, "DESede");
//     }
// }

// public class newEncodeDecode {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         try {
//             int choice = 0;
//             while (choice != 4) {
//                 try {
//                     System.out.println("\nChoose an option:");
//                     System.out.println("1. Encrypt file");
//                     System.out.println("2. Decrypt file");
//                     System.out.println("3. Generate key");
//                     System.out.println("4. Exit\n");
//                     System.out.print(">> ");
//                     choice = scanner.nextInt();
//                     scanner.nextLine(); // Consume the newline

//                     if (choice == 1) {
//                         System.out.println("\nEnter the file path:");
//                         String filePath = scanner.nextLine();
//                         if (!Files.exists(Paths.get(filePath))) {
//                             System.out.println("Invalid file path. Please enter a valid file path.");
//                             continue; // Return to the menu
//                         }
//                         System.out.println("\nEnter key:");
//                         String key1 = scanner.nextLine();
//                         String text = fileToText(filePath);
//                         try {
//                             String encryptedText = encrypt(text, key1);
//                             textToFile(encryptedText, filePath);
//                             System.out.println("\nFile has been Encrypted.\n");
//                         } catch (Exception e) {
//                             System.out.println("Error: Invalid key. Please enter a valid key.");
//                         }
//                     } else if (choice == 2) {
//                         System.out.println("\nEnter file path:");
//                         String filePath = scanner.nextLine();
//                         if (!Files.exists(Paths.get(filePath))) {
//                             System.out.println("Invalid file path. Please enter a valid file path.");
//                             continue; // Return to the menu
//                         }
//                         System.out.println("\nEnter key:");
//                         String key1 = scanner.nextLine();
//                         String text = fileToText(filePath);
//                         try {
//                             String decryptedText = decrypt(text, key1);
//                             textToFile(decryptedText, filePath);
//                             System.out.println("\nFile has been Decrypted.\n");
//                         } catch (Exception e) {
//                             System.out.println("Error: Invalid key. Please enter a valid key.");
//                         }
//                     } else if (choice == 3) {
//                         System.out.println("Generating key...\n");
//                         SecretKey desKey1 = generateTripleDESKey();
//                         String key1 = Base64.getEncoder().encodeToString(desKey1.getEncoded());
//                         System.out.println("Key: " + key1 + "\n");
//                     } else if (choice == 4) {
//                         System.out.println("\nExiting...\n");
//                     } else {
//                         System.out.println("Invalid choice. Please select a valid option (1, 2, 3, or 4).");
//                     }
//                 } catch (InputMismatchException e) {
//                     System.out.println("Invalid input. Please enter a valid menu choice (1, 2, 3, or 4).");
//                     scanner.nextLine(); // Consume the invalid input
//                 }
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             System.out.println("An error occurred during encryption/decryption.");
//         } finally {
//             scanner.close();
//         }
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

//     public static SecretKey generateTripleDESKey() throws Exception {
//     KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
//     keyGenerator.init(168); // Specify the key size explicitly
//     return keyGenerator.generateKey();
//     }
    
//     public static String encrypt(String text, String key) throws Exception {
//         return TripleDES.encrypt(text, key);
//     }

//     public static String decrypt(String text, String key) throws Exception {
//         return TripleDES.decrypt(text, key);
//     }
// }

// this is a AES algo encryptor

import java.util.Scanner;
import javax.crypto.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;

class AES {
    public static String encrypt(String plaintext, String key) throws Exception {
        SecretKeySpec secretKey = createKey(key);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, String key) throws Exception {
        SecretKeySpec secretKey = createKey(key);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static SecretKeySpec createKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, "AES");
    }
}

public class newEncodeDecode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int choice = 0;
            while (choice != 4) {
                try {
                    System.out.println("\nChoose an option:");
                    System.out.println("1. Encrypt file");
                    System.out.println("2. Decrypt file");
                    System.out.println("3. Generate key");
                    System.out.println("4. Exit\n");
                    System.out.print(">> ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline

                    if (choice == 1) {
                        System.out.println("\nEnter the file path:");
                        String filePath = scanner.nextLine();
                        if (!Files.exists(Paths.get(filePath))) {
                            System.out.println("Invalid file path. Please enter a valid file path.");
                            continue; // Return to the menu
                        }
                        System.out.println("\nEnter key:");
                        String key = scanner.nextLine();
                        String text = fileToText(filePath);
                        try {
                            String encryptedText = AES.encrypt(text, key);
                            textToFile(encryptedText, filePath);
                            System.out.println("\nFile has been Encrypted.\n");
                        } catch (Exception e) {
                            System.out.println("Error: Invalid key. Please enter a valid key.");
                        }
                    } else if (choice == 2) {
                        System.out.println("\nEnter file path:");
                        String filePath = scanner.nextLine();
                        if (!Files.exists(Paths.get(filePath))) {
                            System.out.println("Invalid file path. Please enter a valid file path.");
                            continue; // Return to the menu
                        }
                        System.out.println("\nEnter key:");
                        String key = scanner.nextLine();
                        String text = fileToText(filePath);
                        try {
                            String decryptedText = AES.decrypt(text, key);
                            textToFile(decryptedText, filePath);
                            System.out.println("\nFile has been Decrypted.\n");
                        } catch (Exception e) {
                            System.out.println("Error: Invalid key. Please enter a valid key.");
                        }
                    } else if (choice == 3) {
                        System.out.println("Generating key...\n");
                        SecretKey aesKey = generateAESKey();
                        String key = Base64.getEncoder().encodeToString(aesKey.getEncoded());
                        System.out.println("Key: " + key + "\n");
                    } else if (choice == 4) {
                        System.out.println("\nExiting...\n");
                    } else {
                        System.out.println("Invalid choice. Please select a valid option (1, 2, 3, or 4).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid menu choice (1, 2, 3, or 4).");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during encryption/decryption.");
        } finally {
            scanner.close();
        }
    }

    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        return keyGenerator.generateKey();
    }

    // Methods for file to text and text to file conversion
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
}
