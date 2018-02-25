package com.julyelektra;


import java.util.ArrayList;
import java.util.Date;

/**
 * Meta class for storing block chain
 */
public class BlockChain {
    private ArrayList<Block> blocks;

    /**
     * Constructor for creation of block chain.
     *
     * @param data needed to create the first block in the chain
     */
    public BlockChain(String data) {
        this.blocks = new ArrayList<>();
        Block initialBlock = new Block(0, data);
        blocks.add(initialBlock);
    }

    /**
     * Get last block of the chain
     *
     * @return
     */
    private Block getLast() {
        return blocks.get(blocks.size() - 1);
    }

    /**
     * Generate new Block and add it current block chain
     *
     * @param data
     * @return
     */
    public Block generateBlock(String data) {
        Block newBlock = getNewBlock(data);
        if (BlockChainValidator.isValidBlock(newBlock, getLast())) {
            blocks.add(newBlock);
            return newBlock;
        } else {
            System.out.println("You are trying to add invalid block into chain.");
            return null;
        }
    }

    /**
     * Generate new block with data
     *
     * @param data to be stored in block
     * @return new Block
     */
    private Block getNewBlock(String data) {
        Block latestBlock = getLast();
        int nextIndex = latestBlock.getIndex() + 1;
        Date nextTimeStamp = new Date();
        String nextHash = Utils.calculateHash(nextIndex, latestBlock.getHash(), nextTimeStamp, data);
        return new Block(nextIndex, latestBlock.getHash(), nextTimeStamp, data, nextHash);
    }

    public ArrayList<Block> getAllBlocks() {
        return blocks;
    }

    /**
     * Replace block chain if new block chain is valid and longer
     *
     * @param newBlocks
     */
    public void replaceChain(ArrayList<Block> newBlocks) {
        if (BlockChainValidator.isValidChain(newBlocks) && newBlocks.size() > blocks.size()) {
            System.out.println("Received blockchain is valid. Replacing current blockchain with received blockchain.");
            blocks = newBlocks;
        } else {
            System.out.println("Received blockchain invalid.");
        }
    }
}
