package fr.shidozz.esharp;

import fr.shidozz.esharp.Node.*;
import java.util.*;

/**
 *
 * @author shidozz
 */
public class Parser {
    private List<Token> tokens;
    private int index;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.index = 0;
    }
    
    public static boolean isBool(String str) {
        return "true".equals(str) || "false".equals(str);
    }
    
    public static boolean isChar(String str) {
        return str != null && str.length() == 1;
    }
    
    public static boolean isInt(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isString(String str) {
        return str != null && !str.isEmpty();
    }

    List<Node> parse() {
        List<Node> stats = new ArrayList<>();
        Node stat = parseStatement();
        while(stat != null){
            stats.add(stat);
            stat = parseStatement();
        }
        return stats;
    }

    private Node parseStatement() {
        Token token = peekNextToken();
        Node stat = null;
        switch (token.getType()) {
            case VAR:
                stat = parseVariableDeclaration();
                expect(TokenType.SEMICOLON);
                getNextToken();
                break;
            case FUNCTION:
                stat = parseFunctionDeclaration();
                break;
            case RETURN:
                stat = parseReturn();
                break;
            case IDENTIFIER:
                getNextToken();
                if (peekNextToken().getType() == TokenType.EQUALS) {
                    stat = parseAssignment(token.getValue());
                    expect(TokenType.SEMICOLON);
                    getNextToken();
                } else if (peekNextToken().getType() == TokenType.LPAREN){
                    stat = parseFunctionCall(token.getValue());
                    expect(TokenType.SEMICOLON);
                    getNextToken();
                }
                break;
        }
        return stat;
    }

    private List<String> parseParameters() {
        List<String> parameters = new ArrayList<>();
        if (peekNextToken().getType() != TokenType.RPAREN) {
            expect(TokenType.IDENTIFIER);
            parameters.add(getNextToken().getValue());
            while (peekNextToken().getType() == TokenType.COMMA) {
                getNextToken();
                expect(TokenType.IDENTIFIER);
                parameters.add(peekNextToken().getValue());
                getNextToken();
            }
        }
        return parameters;
    }

    private Node parseFunctionDeclaration(){
        expect(TokenType.FUNCTION);
        String func = getNextToken().getValue();

        expect(TokenType.IDENTIFIER);
        String identifier = getNextToken().getValue();
        expect(TokenType.LPAREN);
        getNextToken();
        List<String> parameters = parseParameters();
        expect(TokenType.RPAREN);
        getNextToken();
        expect(TokenType.LBRACE);
        getNextToken();

        Node block = parseBlock();
        expect(TokenType.RBRACE);
        getNextToken();
        return new NodeFunctionDeclaration(func, identifier, parameters, block);
    }

    private Node parseBlock() {
        List<Node> statements = new ArrayList<>();
        while (peekNextToken().getType() != TokenType.RBRACE) {
            statements.add(parseStatement());
        }
        return new NodeBlock(statements);
    }

    private Node parseFunctionCall(String functionName) {
        expect(TokenType.LPAREN);
        getNextToken();
        List<Node> arguments = parseArguments();
        expect(TokenType.RPAREN);
        getNextToken();
        return new NodeFunctionCall(functionName, arguments);
    }

    private List<Node> parseArguments() {
        List<Node> arguments = new ArrayList<>();
        if (peekNextToken().getType() != TokenType.RPAREN) {
            arguments.add(parseExpression());
            while (peekNextToken().getType() == TokenType.COMMA) {
                getNextToken();
                arguments.add(parseExpression());
            }
        }
        return arguments;
    }

    
    private Node parseVariableDeclaration() {

        expect(TokenType.VAR);
        String type = getNextToken().getValue();

        expect(TokenType.IDENTIFIER);
        String identifier = getNextToken().getValue();
        Node expression = null;
        if (peekNextToken().getType() == TokenType.EQUALS) {
            getNextToken();
            expression = parseExpression();
        }
        
        return new NodeVariableDeclaration(type, identifier, expression);
    }

    private Node parseAssignment(String identifier) {
        expect(TokenType.EQUALS);
        getNextToken();
        Node expression = parseExpression();
        return new NodeAssignment(identifier, expression);
    }

    private Node parseReturn() {
        expect(TokenType.RETURN);
        getNextToken();
        if (peekNextToken().getType() != TokenType.SEMICOLON) {
            Node expression = parseExpression();
            expect(TokenType.SEMICOLON);
            getNextToken();
            return new NodeReturn(expression);
        } else {
            getNextToken();
            return new NodeReturn(null);
        }
    }

    private Node parseExpression() {
        Node leftOperand = parseTerm();
        Node theOperand = leftOperand;
        while (peekNextToken().getType() == TokenType.PLUS || peekNextToken().getType() == TokenType.MINUS || peekNextToken().getType() == TokenType.DIVID || peekNextToken().getType() == TokenType.MULTIPLY) {
            TokenType operator = getNextToken().getType();
            Node rightOperand = parseTerm();
            theOperand = new NodeExpression(leftOperand, operator, rightOperand);
        }
        return theOperand;
    }
    
    private Node parseTerm() {
        Node leftOperand = parseFactor();
        while (peekNextToken().getType() == TokenType.MULTIPLY || peekNextToken().getType() == TokenType.DIVID) {
            TokenType operator = getNextToken().getType();
            Node rightOperand = parseFactor();
            leftOperand = new NodeExpression(leftOperand, operator, rightOperand);
        }
        return leftOperand;
    }

    private Node parseFactor() {
        Token token = getNextToken();
        if (token.getType() == TokenType.NUMBER || token.getType() == TokenType.IDENTIFIER) {
            if(token.getType() == TokenType.NUMBER){
                try {
                    int parseInt = Integer.parseInt(token.getValue());
                    return new NodeLiteral(parseInt, LiteralType.NUMBER);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            if(token.getType() == TokenType.IDENTIFIER){
                return new NodeLiteral(token.getValue(), LiteralType.IDENTIFIER);
            }
        } else if (token.getType() == TokenType.LPAREN) {
            Node expression = parseExpression();
            expect(TokenType.RPAREN);
            getNextToken();
            return expression;
        }
        throw new RuntimeException("Unexpected token in factor: " + token);
    }
    
    private Token getNextToken() {
        if (index < tokens.size()) {
            return tokens.get(index++);
        }
        return new Token(TokenType.EOF, "");
    }

    private Token peekNextToken() {
        if (index < tokens.size()) {
            return tokens.get(index);
        }
        return new Token(TokenType.EOF, "");
    }

    private void expect(TokenType expectedType) {
        Token token = peekNextToken();
        if (token.getType() != expectedType) {
            throw new RuntimeException("Unexpected token: " + token + ".\nToken Wanted: <Token {Type: " + expectedType + "}>");
        }
    }
}

