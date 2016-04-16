package org.revo

import groovy.json.JsonOutput

/**
 * Created by ashraf on 4/15/2016.
 */
class Parser {
    private Lexer lexer
    private List<Token> tokens
    private int index = -1

    Parser(Lexer lexer) {
        this.lexer = lexer
        tokens = lexer.scan()
    }


    public static void main(String[] args) {
        new Parser(new LexerImpl("C:\\Users\\ashraf\\Downloads\\RevoCompiler\\src\\main\\resources\\code1")).parse();
    }

    private Block block(Block current) {
        ShouldMatch(Tag.OpenBrace)
        Deceleration(current)
        Expression(current)
        Deceleration(current)
        ShouldMatch(Tag.CloseBrace)
        current
    }

    private void Expression(Block current) {
        while (FuckToken().tag == Tag.If||FuckToken().tag == Tag.While) {
            if (FuckToken().tag == Tag.If) {
                Block inside = new Block(token: NextToken(), blockType: BlockType.Expression, name: CurrentToken().lexeme)
                current.blocks.add(inside)
                ShouldMatch(Tag.OpenParenthesis)
                ShouldMatch(Tag.CloseParenthesis)
                block(inside)
            }
        }
    }

    private void ShouldMatch(int i) {
        if (NextToken().tag != i) throw new Error("error in your code")
    }


    Token NextToken() {
        tokens[++index]
    }

    Token FuckToken() {
        tokens[index + 1]
    }

    Token CurrentToken() {
        tokens[index]
    }


    private void Deceleration(Block current) {
        while (TokenType.Type == FuckToken().tokenType) {
            current.blocks.add(new Block(token: NextToken(), blockType: BlockType.Statement, name: NextToken().lexeme))
            if (FuckToken().tag==Tag.Assign){
//                NextToken()
//                NextToken().lexeme
                TokenType.Assign
                Tag.Assign
                BlockType.Statement
            }
            ShouldMatch(Tag.Semicolon)
        }

    }

    void parse() {
        println(JsonOutput.toJson(block(new Block(name: "Main"))))
    }
}
