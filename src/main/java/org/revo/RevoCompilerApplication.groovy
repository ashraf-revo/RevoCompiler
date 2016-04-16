package org.revo

class RevoCompilerApplication {

    static void main(String[] args) {
    }
}

class Block {
    List<Block> blocks = []
    String name
    BlockType blockType
    Token token
}

enum BlockType {
    Expression, Statement
}
