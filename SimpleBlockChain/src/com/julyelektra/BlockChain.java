package com.julyelektra;


import java.util.ArrayList;
import java.util.Date;

/**
 * Meta class for storing block chain
 */
public class BlockChain {
    private ArrayList<Block> blockChain;

    public BlockChain(String data) {
        this.blockChain = new ArrayList<>();
        Block initialBlock = new Block(0, null, data);
        blockChain.add(initialBlock);
    }

    /**
     * Get last block of the chain
     *
     * @return
     */
    public Block getLast() {
        return blockChain.get(blockChain.size() - 1);
    }

    /**
     * Generate new Block and add it current block chain
     *
     * @param data
     * @return
     */
    public Block generateBlock(String data) {
        Block newBlock = getNewBlock(data);
        blockChain.add(newBlock);
        return newBlock;
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

    public ArrayList<Block> getBlockChain() {
        return blockChain;
    }

    public void setBlockChain(ArrayList<Block> blockChain) {
        this.blockChain = blockChain;
    }

    /**
     * Replace block chain if new block chain is valid and longer
     *
     * @param newBlocks
     */
    public void replaceChain(ArrayList<Block> newBlocks) {
        if (BlockChainValidator.isValidChain(newBlocks) && newBlocks.size() > blockChain.size()) {
            System.out.println("Received blockchain is valid. Replacing current blockchain with received blockchain");
            blockChain = newBlocks;
        } else {
            System.out.println("Received blockchain invalid");
        }
    }
}
