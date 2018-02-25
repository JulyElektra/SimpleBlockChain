package com.julyelektra;

import java.util.Date;

/**
 * Meta class for blocks with data
 */
public class Block implements Comparable {

    private final Integer index;
    private final String previousHash;
    private final Date timestamp;
    private final String data;
    private final String hash;

    public Block(int index, String previousHash, Date timestamp, String data, String hash) {
        this.index = index;
        this.previousHash = previousHash.toString();
        this.timestamp = timestamp;
        this.data = data;
        this.hash = hash.toString();
    }

    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.previousHash = previousHash.toString();
        this.timestamp = new Date();
        this.data = data;
        this.hash = Utils.calculateHash(index, previousHash, timestamp, data);
    }

    public Integer getIndex() {
        return index;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public int hashCode() {
        return Utils.calculateHash(index, previousHash, timestamp, data).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Block && hashCode() == obj.hashCode()) {
            Block block = (Block) obj;
            return this.hash.equals(block.hash) && previousHash.equals(block.previousHash) &&
                    index == block.index && data.equals(block.data) && timestamp.equals(block.timestamp);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Block) {
            return index.compareTo(((Block) o).index);
        } else {
            throw new IllegalArgumentException("Error to compare different types.");
        }
    }
}
