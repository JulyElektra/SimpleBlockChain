package com.julyelektra;

public class Main {

    public static void main(String[] args) {
        BlockChain chainLarge = new BlockChain("some data in the block");
        chainLarge.generateBlock("one more data");
        chainLarge.generateBlock("one two three");
        chainLarge.generateBlock("four five six");
        chainLarge.generateBlock("seven eight ten");

        BlockChain chainSmall = new BlockChain("some data in the block");
        chainSmall.generateBlock("one more data");
        chainSmall.generateBlock("one two three");

        chainLarge.replaceChain(chainSmall.getAllBlocks());
        chainSmall.replaceChain(chainLarge.getAllBlocks());
    }
}
