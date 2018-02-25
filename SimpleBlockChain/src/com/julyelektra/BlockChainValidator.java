package com.julyelektra;

import java.util.ArrayList;

/**
 * Class for determining if block chain is valid
 */
public class BlockChainValidator {

    /**
     * Validate of integrity of a new block
     *
     * @param block
     * @param previousBlock
     * @return
     */
    public static boolean isValidBlock(Block block, Block previousBlock) {
        if (previousBlock.getIndex() + 1 != block.getIndex()) {
            System.out.println("invalid index");
            return false;
        } else if (!previousBlock.getHash().equals(block.getPreviousHash())) {
            System.out.println("invalid previoushash");
            return false;
        } else if (!Utils.calculateHashForBlock(block).equals(block.getHash())) {
            System.out.println("invalid hash: " + Utils.calculateHashForBlock(block) + " " + block.getHash());
            return false;
        }
        return true;
    }


    /**
     * Check if block chain is valid
     *
     * @param blockChain
     * @return
     */
    public static boolean isValidChain(ArrayList<Block> blockChain) {
        for (Block currentBlock : blockChain) {
            if (currentBlock.getIndex() == 0) {
                continue;
            } else {
                Block previousBlock = blockChain.get(currentBlock.getIndex() - 1);
                if (!isValidBlock(currentBlock, previousBlock)) {
                    return false;
                }
            }
        }
        return true;
    }
}
