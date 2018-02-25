package com.julyelektra;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Methods for various purposes
 */
public class Utils {
    /**
     * Get hash for integrity of data
     *
     * @param index
     * @param previousHash
     * @param timestamp
     * @param data
     * @return
     */
    public static String calculateHash(int index, String previousHash, Date timestamp, String data) {
        return getSHA256(index + previousHash + timestamp + data);
    }

    /**
     * Get String input and hash it into another sting
     *
     * @param value input string
     * @return hashed string
     */
    private static String getSHA256(String value) {
        StringBuffer result = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            byte byteData[] = md.digest();

            for (byte b : byteData) {
                result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get hash for Block
     *
     * @param block
     * @return hashed string
     */
    public static String calculateHashForBlock(Block block) {
        return calculateHash(block.getIndex(), block.getPreviousHash(), block.getTimestamp(), block.getData());
    }
}
