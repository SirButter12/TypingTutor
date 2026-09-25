package com.programacion3.typingtutor;

import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyBoardData {
    public static final List<String> keys = new ArrayList<>(List.of(
            "`","1","2","3","4","5", "6", "7", "8", "9", "0", "<-", "\n"
            ,"tab" , "q", "w", "e", "r", "t", "y", "u", "i" , "o", "p","tab" ,"\n"
            ,"shift", "a", "s", "d", "f", "g", "h", "j", "k" , "l", ";","shift" ,"\n"
            ,"alt" , "z", "x", "c", "v", "b", "n", "m", "," , ".", "/","alt" ,"\n",
            "spc"
    ));

    public static final List<String> shiftKeys = new ArrayList<>(List.of(
            "~" ,"!","@","#","$","%", "^", "&", "*", "(", ")", "<-","\n"
            ,"tab" , "Q", "W", "E", "R", "T", "Y", "U", "I" , "O", "P","tab" ,"\n"
            ,"shift" , "A", "S", "D", "F", "G", "H", "J", "K" , "L", ":","shift" ,"\n"
            ,"alt" ,"Z", "X", "C", "V", "B", "N", "M", "<" , ">", "?","alt", "\n",
            "spc"
    ));

    public static final Map<KeyCode, int[]> keyMapping = new HashMap<>();

    static {
        keyMapping.put(KeyCode.BACK_QUOTE, new int[]{0, 0});
        keyMapping.put(KeyCode.DIGIT1, new int[]{1, 0});
        keyMapping.put(KeyCode.DIGIT2, new int[]{2, 0});
        keyMapping.put(KeyCode.DIGIT3, new int[]{3, 0});
        keyMapping.put(KeyCode.DIGIT4, new int[]{4, 0});
        keyMapping.put(KeyCode.DIGIT5, new int[]{5, 0});
        keyMapping.put(KeyCode.DIGIT6, new int[]{6, 0});
        keyMapping.put(KeyCode.DIGIT7, new int[]{7, 0});
        keyMapping.put(KeyCode.DIGIT8, new int[]{8, 0});
        keyMapping.put(KeyCode.DIGIT9, new int[]{9, 0});
        keyMapping.put(KeyCode.DIGIT0, new int[]{10, 0});
        keyMapping.put(KeyCode.BACK_SPACE, new int[]{11, 0});

        keyMapping.put(KeyCode.TAB, new int[]{0, 1});
        keyMapping.put(KeyCode.Q, new int[]{1, 1});
        keyMapping.put(KeyCode.W, new int[]{2, 1});
        keyMapping.put(KeyCode.E, new int[]{3, 1});
        keyMapping.put(KeyCode.R, new int[]{4, 1});
        keyMapping.put(KeyCode.T, new int[]{5, 1});
        keyMapping.put(KeyCode.Y, new int[]{6, 1});
        keyMapping.put(KeyCode.U, new int[]{7, 1});
        keyMapping.put(KeyCode.I, new int[]{8, 1});
        keyMapping.put(KeyCode.O, new int[]{9, 1});
        keyMapping.put(KeyCode.P, new int[]{10, 1});

        keyMapping.put(KeyCode.SHIFT, new int[]{0, 2});
        keyMapping.put(KeyCode.A, new int[]{1, 2});
        keyMapping.put(KeyCode.S, new int[]{2, 2});
        keyMapping.put(KeyCode.D, new int[]{3, 2});
        keyMapping.put(KeyCode.F, new int[]{4, 2});
        keyMapping.put(KeyCode.G, new int[]{5, 2});
        keyMapping.put(KeyCode.H, new int[]{6, 2});
        keyMapping.put(KeyCode.J, new int[]{7, 2});
        keyMapping.put(KeyCode.K, new int[]{8, 2});
        keyMapping.put(KeyCode.L, new int[]{9, 2});
        keyMapping.put(KeyCode.SEMICOLON, new int[]{10, 2});

        keyMapping.put(KeyCode.ALT, new int[]{0, 3});
        keyMapping.put(KeyCode.Z, new int[]{1, 3});
        keyMapping.put(KeyCode.X, new int[]{2, 3});
        keyMapping.put(KeyCode.C, new int[]{3, 3});
        keyMapping.put(KeyCode.V, new int[]{4, 3});
        keyMapping.put(KeyCode.B, new int[]{5, 3});
        keyMapping.put(KeyCode.N, new int[]{6, 3});
        keyMapping.put(KeyCode.M, new int[]{7, 3});
        keyMapping.put(KeyCode.COMMA, new int[]{8, 3});
        keyMapping.put(KeyCode.PERIOD, new int[]{9, 3});
        keyMapping.put(KeyCode.SLASH, new int[]{10, 3});
        keyMapping.put(KeyCode.SPACE, new int[]{0, 4});
    }
    
    public static final Map<String, int[]> characterMapping = new HashMap<>();

    static {
        characterMapping.put("`", new int[]{0, 0});
        characterMapping.put("1", new int[]{1, 0});
        characterMapping.put("2", new int[]{2, 0});
        characterMapping.put("3", new int[]{3, 0});
        characterMapping.put("4", new int[]{4, 0});
        characterMapping.put("5", new int[]{5, 0});
        characterMapping.put("6", new int[]{6, 0});
        characterMapping.put("7", new int[]{7, 0});
        characterMapping.put("8", new int[]{8, 0});
        characterMapping.put("9", new int[]{9, 0});
        characterMapping.put("0", new int[]{10, 0});

        characterMapping.put("q", new int[]{1, 1});
        characterMapping.put("w", new int[]{2, 1});
        characterMapping.put("e", new int[]{3, 1});
        characterMapping.put("r", new int[]{4, 1});
        characterMapping.put("t", new int[]{5, 1});
        characterMapping.put("y", new int[]{6, 1});
        characterMapping.put("u", new int[]{7, 1});
        characterMapping.put("i", new int[]{8, 1});
        characterMapping.put("o", new int[]{9, 1});
        characterMapping.put("p", new int[]{10, 1});

        characterMapping.put("a", new int[]{1, 2});
        characterMapping.put("s", new int[]{2, 2});
        characterMapping.put("d", new int[]{3, 2});
        characterMapping.put("f", new int[]{4, 2});
        characterMapping.put("g", new int[]{5, 2});
        characterMapping.put("h", new int[]{6, 2});
        characterMapping.put("j", new int[]{7, 2});
        characterMapping.put("k", new int[]{8, 2});
        characterMapping.put("l", new int[]{9, 2});
        characterMapping.put(";", new int[]{10, 2});

        characterMapping.put("z", new int[]{1, 3});
        characterMapping.put("x", new int[]{2, 3});
        characterMapping.put("c", new int[]{3, 3});
        characterMapping.put("v", new int[]{4, 3});
        characterMapping.put("b", new int[]{5, 3});
        characterMapping.put("n", new int[]{6, 3});
        characterMapping.put("m", new int[]{7, 3});
        characterMapping.put(",", new int[]{8, 3});
        characterMapping.put(".", new int[]{9, 3});
        characterMapping.put("/", new int[]{10, 3});

        characterMapping.put(" ", new int[]{0, 4});
    }
    
    public static final Map<String, int[]> shiftMapping = new HashMap<>();

    static {
        shiftMapping.put("~", new int[]{0, 0});
        shiftMapping.put("!", new int[]{1, 0});
        shiftMapping.put("@", new int[]{2, 0});
        shiftMapping.put("#", new int[]{3, 0});
        shiftMapping.put("$", new int[]{4, 0});
        shiftMapping.put("%", new int[]{5, 0});
        shiftMapping.put("^", new int[]{6, 0});
        shiftMapping.put("&", new int[]{7, 0});
        shiftMapping.put("*", new int[]{8, 0});
        shiftMapping.put("(", new int[]{9, 0});
        shiftMapping.put(")", new int[]{10, 0});

        shiftMapping.put("Q", new int[]{1, 1});
        shiftMapping.put("W", new int[]{2, 1});
        shiftMapping.put("E", new int[]{3, 1});
        shiftMapping.put("R", new int[]{4, 1});
        shiftMapping.put("T", new int[]{5, 1});
        shiftMapping.put("Y", new int[]{6, 1});
        shiftMapping.put("U", new int[]{7, 1});
        shiftMapping.put("I", new int[]{8, 1});
        shiftMapping.put("O", new int[]{9, 1});
        shiftMapping.put("P", new int[]{10, 1});

        shiftMapping.put("A", new int[]{1, 2});
        shiftMapping.put("S", new int[]{2, 2});
        shiftMapping.put("D", new int[]{3, 2});
        shiftMapping.put("F", new int[]{4, 2});
        shiftMapping.put("G", new int[]{5, 2});
        shiftMapping.put("H", new int[]{6, 2});
        shiftMapping.put("J", new int[]{7, 2});
        shiftMapping.put("K", new int[]{8, 2});
        shiftMapping.put("L", new int[]{9, 2});
        shiftMapping.put(":", new int[]{10, 2});

        shiftMapping.put("Z", new int[]{1, 3});
        shiftMapping.put("X", new int[]{2, 3});
        shiftMapping.put("C", new int[]{3, 3});
        shiftMapping.put("V", new int[]{4, 3});
        shiftMapping.put("B", new int[]{5, 3});
        shiftMapping.put("N", new int[]{6, 3});
        shiftMapping.put("M", new int[]{7, 3});
        shiftMapping.put("<", new int[]{8, 3});
        shiftMapping.put(">", new int[]{9, 3});
        shiftMapping.put("?", new int[]{10, 3});
    }
}