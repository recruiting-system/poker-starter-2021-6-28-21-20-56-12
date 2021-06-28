package com.thoughtworks.refactor;

import java.util.*;

public class Poker {
    public String compairResult(String black, String white) {
        String winResult = "";
        String blackType = judgeType(black);
        String whiteType = judgeType(white);
        String[] type = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};
        int[] blackNumber = strNumber(black);
        int[] whiteNumber = strNumber(white);
        int blackIndex = judgeIndex(blackType);
        int whiteIndex = judgeIndex(whiteType);
        int[] blackArraySort = arraySort(blackNumber);
        int[] whiteArraySort = arraySort(whiteNumber);
        int[] blackRepeat = noOrRepeatNumber(blackNumber, 0);
        int[] whiteRepeat = noOrRepeatNumber(whiteNumber, 0);
        int[] blackNoRepeat = noOrRepeatNumber(blackNumber, 1);
        int[] whiteNoRepeat = noOrRepeatNumber(whiteNumber, 1);
        if (blackIndex < whiteIndex) {
            winResult = "black wins - " + type[blackIndex];
        } else if (blackIndex > whiteIndex) {
            winResult = "white wins - " + type[whiteIndex];
        } else {
            if (blackIndex == 0) { // Straight Flush
                if (blackNumber[0] < whiteNumber[0]) {
                    String sig = intNumber(whiteNumber[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackNumber[0] > whiteNumber[0]) {
                    String sig = intNumber(blackNumber[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackIndex == 1) { // Four Of A Kind
                if (blackArraySort[0] < whiteArraySort[0]) {
                    String sig = intNumber(whiteArraySort[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackArraySort[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackIndex == 2) { // Full House
                if (blackArraySort[0] < whiteArraySort[0]) {
                    String sig = intNumber(whiteArraySort[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackArraySort[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackIndex == 3) { // Flush
                for (int i = 0; i < 5; i++) {
                    if (blackNumber[i] < whiteNumber[i]) {
                        String sig = intNumber(whiteNumber[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackNumber[i] > whiteNumber[i]) {
                        String sig = intNumber(blackNumber[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackIndex == 4) { // Straight
                if (blackNumber[0] < whiteNumber[0]) {
                    String sig = intNumber(whiteNumber[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackNumber[0] > whiteNumber[0]) {
                    String sig = intNumber(blackNumber[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    winResult = "tie";
                }
            } else if (blackIndex == 5) { // Three Of A Kind
                if (blackRepeat[0] < whiteRepeat[0]) {
                    String sig = intNumber(whiteRepeat[0]);
                    winResult = "white wins - high card:" + sig;
                } else {
                    String sig = intNumber(blackRepeat[0]);
                    winResult = "black wins - high card:" + sig;
                }
            } else if (blackIndex == 6) { // Two Pair
                for (int i = 0; i < 2; i++) {
                    if (blackRepeat[i] < whiteRepeat[i]) {
                        String sig = intNumber(whiteRepeat[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackRepeat[i] > whiteRepeat[i]) {
                        String sig = intNumber(blackRepeat[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    }
                }
                if (winResult == "") {
                    if (blackNoRepeat[0] < whiteNoRepeat[0]) {
                        String sig = intNumber(whiteNoRepeat[0]);
                        winResult = "white wins - high card:" + sig;
                    } else if (blackNoRepeat[0] > whiteNoRepeat[0]) {
                        String sig = intNumber(blackNoRepeat[0]);
                        winResult = "black wins - high card:" + sig;
                    } else {
                        winResult = "tie";
                    }
                }
            } else if (blackIndex == 7) { // One Pair
                if (blackRepeat[0] < whiteRepeat[0]) {
                    String sig = intNumber(whiteRepeat[0]);
                    winResult = "white wins - high card:" + sig;
                } else if (blackRepeat[0] > whiteRepeat[0]) {
                    String sig = intNumber(blackRepeat[0]);
                    winResult = "black wins - high card:" + sig;
                } else {
                    for (int i = 0; i < 3; i++) {
                        if (blackNoRepeat[i] < whiteNoRepeat[i]) {
                            String sig = intNumber(whiteNoRepeat[i]);
                            winResult = "white wins - high card:" + sig;
                            break;
                        } else if (blackNoRepeat[i] > whiteNoRepeat[i]) {
                            String sig = intNumber(blackNoRepeat[i]);
                            winResult = "black wins - high card:" + sig;
                            break;
                        } else {
                            winResult = "tie";
                        }
                    }
                }
            } else { // High Card
                for (int i = 0; i < 5; i++) {
                    if (blackNumber[i] < whiteNumber[i]) {
                        String sig = intNumber(whiteNumber[i]);
                        winResult = "white wins - high card:" + sig;
                        break;
                    } else if (blackNumber[i] > whiteNumber[i]) {
                        String sig = intNumber(blackNumber[i]);
                        winResult = "black wins - high card:" + sig;
                        break;
                    } else {
                        winResult = "tie";
                    }
                }
            }
        }
        return winResult;
    }

    private String intNumber(int i) {
        String[] strNumber = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        return strNumber[i - 2];
    }

    private int[] arraySort(int[] number) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < number.length; i++) {
            if (map.get(number[i]) != null) {
                map.put(number[i], map.get(number[i]) + 1);
            } else {
                map.put(number[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> arg0, Map.Entry<Integer, Integer> arg1) {
                return arg1.getValue().compareTo(arg0.getValue());
            }
        });
        int[] arrayresult = new int[list.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            arrayresult[i] = entry.getKey();
            i++;
        }
        return arrayresult;
    }

    // First get the number of occurrences of each element in the array,
    // then calculate the number of occurrences greater than 1 and the number of occurrences equal to 1.
    private int[] noOrRepeatNumber(int[] number, int flag) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < number.length; i++) {
            if (map.get(number[i]) != null) {
                map.put(number[i], map.get(number[i]) + 1);
            } else {
                map.put(number[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> arg0, Map.Entry<Integer, Integer> arg1) {
                return arg1.getValue().compareTo(arg0.getValue());
            }
        });
        int[] repeatnumber = new int[list.size()];
        int[] norepeatnumber = new int[list.size()];
        int i = 0;
        if (flag == 0) {
            for (Map.Entry<Integer, Integer> entry : list) {
                if (entry.getValue() > 1) {
                    repeatnumber[i] = entry.getKey();
                    i++;
                }
            }
        } else {
            for (Map.Entry<Integer, Integer> entry : list) {
                if (entry.getValue() == 1) {
                    norepeatnumber[i] = entry.getKey();
                    i++;
                }
            }
        }
        HashSet<Integer> hashSet = new HashSet<Integer>();
        if (flag == 0) {
            for (i = 0; i < repeatnumber.length; i++) {
                hashSet.add(repeatnumber[i]);
            }
        } else {
            for (i = 0; i < norepeatnumber.length; i++) {
                hashSet.add(norepeatnumber[i]);
            }
        }
        hashSet.remove(0);
        int[] result = new int[hashSet.size()];
        i = 0;
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            result[i] = iterator.next();
            i++;
        }
        int[] reResult = new int[result.length];
        for (i = 0; i < result.length; i++) {
            reResult[i] = result[result.length - i - 1];
        }
        return reResult;
    }

    private int judgeIndex(String strType) {
        int index = -1;
        String[] type = {"StraightFlush", "FourOfAKind", "FullHouse", "Flush", "Straight", "ThreeOfAKind", "TwoPair", "OnePair", "HighCard"};
        for (int i = 0; i < 9; i++) {
            if (type[i].equals(strType)) {
                index = i;
            }
        }
        return index;
    }

    // judge the type of card
    private String judgeType(String str) {
        String type = "";
        String[] strArray = str.split("");
        int[] number = strNumber(str);
        int i;
        String[] color = new String[5];
        for (i = 0; i < 5; i++) {
            color[i] = strArray[i * 3 + 1];
        }
        HashSet<Integer> hashSetNumber = new HashSet<Integer>();
        for (i = 0; i < 5; i++) {
            hashSetNumber.add(number[i]);
        }
        HashSet<String> hashSetType = new HashSet<String>();
        for (i = 0; i < 5; i++) {
            hashSetType.add(color[i]);
        }
        if (hashSetNumber.size() == 5) {
            if ((number[0] - number[4] == 4) && (hashSetType.size() == 1) && (hashSetNumber.size() == 5)) { // five adjacent numbers with same color - Straight Flush
                type = "StraightFlush";
            } else if (number[0] - number[4] == 4 && (hashSetNumber.size() == 5)) { // five adjacent numbers - Straight
                type = "Straight";
            } else if (hashSetType.size() == 1) { // same color - Flush
                type = "Flush";
            } else { // five non-adjacent numbers - High Card
                type = "HighCard";
            }
        } else if (hashSetNumber.size() == 4) { // two numbers are one pair, the other three are different - One Pair
            type = "OnePair";
        } else if (hashSetNumber.size() == 3) {
            if ((number[0] == number[1] && number[2] == number[3]) || (number[1] == number[2] && number[3] == number[4]) || (number[0] == number[1] && number[3] == number[4])) { // Two Pair
                type = "TwoPair";
            } else { // three same numbers, the other two are different - Three Of A Kind
                type = "ThreeOfAKind";
            }
        } else {
            if (number[0] != number[1] || number[3] != number[4]) { // three same numbers, the other two are a pair - Full House
                type = "FourOfAKind";
            } else { // four same numbers - Four Of A Kind
                type = "FullHouse";
            }
        }
        return type;
    }

    // Convert to numbers and sort them from largest to smallest
    private int[] strNumber(String str) {
        int[] number = new int[5];
        String[] strArray = str.split("");
        int i;
        for (i = 0; i < 5; i++) {
            String c = strArray[i * 3];
            switch (c) {
                case "T":
                    number[i] = 10;
                    break;
                case "J":
                    number[i] = 11;
                    break;
                case "Q":
                    number[i] = 12;
                    break;
                case "K":
                    number[i] = 13;
                    break;
                case "A":
                    number[i] = 14;
                    break;
                default:
                    number[i] = Integer.valueOf(c);
                    break;
            }
        }

        Arrays.sort(number);
        int[] renumber = new int[number.length];
        for (i = 0; i < number.length; i++) {
            renumber[i] = number[number.length - i - 1];
        }
        return renumber;
    }
}
