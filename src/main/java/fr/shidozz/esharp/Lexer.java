package fr.shidozz.esharp;

import java.util.*;
import java.io.*;
/**
 *
 * @author shidozz
 */
public class Lexer {
    public String filename;
    public List<Token> tokens;
    public String data;
    public char c;
    public int line;
    public int pos;
    public HashSet<String> keywords = new HashSet<>();
    
    public Lexer(String filename){
        this.filename = filename;
        this.tokens = new ArrayList<>();
        this.keywords.add("var");
        this.keywords.add("function");
        this.pos = 0;
        this.data = "";
        File f = new File(filename);
        
        if (!f.exists()) {
            System.out.println("Le fichier `" + filename + "` n'existe pas.");
            return;
        }
        BufferedReader buffer = null;

        try {
            buffer = new BufferedReader(new FileReader(f));
            
            String ligne;
            while ((ligne = buffer.readLine()) != null) {
                this.data += ligne;
            }
            this.data += '\0';
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void tokenize() {
        while (pos < data.length() && peekNextToken().getType() != TokenType.EOF){
            
            Token token = getNextToken();
            if(token.getType() != TokenType.SPACE && token.getType() != TokenType.NULL)
                tokens.add(token);
        }
    }

    
    public Token getNextToken(){
        c = data.charAt(pos);
        String val = "";
        switch (c){
            case '\n':
                ++line;
                while(c == ' ' || c == '\r' || c == '\t' || c == '\n') {
                    ++pos;
                    c = data.charAt(pos);
                }
                return new Token(TokenType.SPACE, "");
            case ' ':
            case '\r':
            case '\t':
                while(c == ' ' || c == '\r' || c == '\t') {
                    ++pos;
                    c = data.charAt(pos);
                }
                return new Token(TokenType.SPACE, "");
            case '=':
                pos += 1;
                return new Token(TokenType.EQUALS, "=");
            case ',':
                pos += 1;
                return new Token(TokenType.COMMA, ",");
            case '/':
                pos += 1;
                return new Token(TokenType.DIVID, "/");
            case '+':
                pos += 1;
                return new Token(TokenType.PLUS, "+");
            case '-':
                pos += 1;
                return new Token(TokenType.MINUS, "-");
            case '*':
                pos += 1;
                return new Token(TokenType.MULTIPLY, "*");
            case '(':
                pos += 1;
                return new Token(TokenType.LPAREN, "(");
            case ')':
                pos += 1;
                return new Token(TokenType.RPAREN, ")");
            case '{':
                pos += 1;
                return new Token(TokenType.LBRACE, "{");
            case '}':
                pos += 1;
                return new Token(TokenType.RBRACE, "}");
            case ';':
                pos += 1;
                return new Token(TokenType.SEMICOLON, ";");
            case '"':
                while(c != '\"'){
                    val += c;
                    pos += 1;
                    c = data.charAt(pos);
                }
                return new Token(TokenType.STRING, val);
            default:
                
                if(Character.isDigit(c)){
                    val += c;
                    pos += 1;
                    c = data.charAt(pos);

                    while(Character.isDigit(c)){
                        val += c;
                        pos += 1;
                        c = data.charAt(pos);
                    }
                    return new Token(TokenType.NUMBER, val);
                }
                if(Character.isAlphabetic(c) || c == '_'){
                    val += c;
                    pos += 1;
                    c = data.charAt(pos);

                    
                    while(Character.isAlphabetic(c) || Character.isDigit(c) || c == '_'){
                        val += c;
                        pos += 1;
                        c = data.charAt(pos);
                    }
                    if(val.contains("function")){
                        return new Token(TokenType.FUNCTION, val);
                    }
                    if(val.contains("var")){
                        return new Token(TokenType.VAR, val);
                    }
                    if(val.contains("return")){
                        return new Token(TokenType.RETURN, val);
                    }
                    return new Token(TokenType.IDENTIFIER, val);
                }
                break;
        }
        return new Token(TokenType.EOF, new String());
    }
    
    public Token peekNextToken(){
        int savedPos = pos;
        Token nextToken = getNextToken();
        pos = savedPos;
        return nextToken;
    }
}
