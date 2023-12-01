package advent;

// https://adventofcode.com/2023/day/1

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

class Day01 {
    public static void main(String ... args) throws Exception {

        AtomicInteger result = new AtomicInteger();

        // read all Lines from file Day01.txt
        Files.readAllLines(Path.of("Input/Day01.txt")).forEach(line -> {

            // find in each line the first digit and the last digit, concat them, parse to int and add to result.
            // Digits could also be spelled out with letters: one, two, three, four, five, six, seven, eight, nine
            // example:
            // line1 = "a1asd23sds4dd" -> first = 1, last = 4 -> concat = "14" -> int = 14
            // line2 = "a1asddsfssddd" -> first = 1, last = 1 -> concat = "11" -> int = 11
            // line3 = "abcone2threexyz" -> first = 1, last = 3 -> concat = "13" -> int = 13
            // line4 = "7pqrstsixteen" -> first = 7, last = 6 -> concat = "76" -> int = 76

            var first = "";

            for (int i = 0; i < line.length(); i++) {

                if (Character.isDigit(line.charAt(i))) {
                    first = String.valueOf(line.charAt(i));
                } else {
                    // check if the line starting at index i begins with a spelled digit
                    for (DIGIT digit : DIGIT.values()) {
                        if (line.startsWith(digit.spelled, i)) {
                            first = String.valueOf(digit.value);
                            break;
                        }
                    }
                }

                if (!first.isEmpty()) {
                    break;
                }
            }

            var last = "";

            for (int i = line.length() - 1; i >= 0; i--) {

                if (Character.isDigit(line.charAt(i))) {
                    last = String.valueOf(line.charAt(i));
                } else {
                    // check if the line starting at index i starts with a spelled digit
                    for (DIGIT digit : DIGIT.values()) {
                        if (line.startsWith(digit.spelled, i)) {
                            last = String.valueOf(digit.value);
                            break;
                        }
                    }
                }

                if (!last.isEmpty()) {
                    break;
                }
            }

            result.addAndGet(Integer.parseInt(first + last));

        });

        System.out.println("My result: " + result);

    }

    // Create an Enum which contains for each digit the spelled value and the value itself
    public enum DIGIT {

        ONE("one", 1),
        TWO("two", 2),
        THREE("three", 3),
        FOUR("four", 4),
        FIVE("five", 5),
        SIX("six", 6),
        SEVEN("seven", 7),
        EIGHT("eight", 8),
        NINE("nine", 9);

        final String spelled;
        final int value;

        DIGIT(String spelled, int value) {
            this.spelled = spelled;
            this.value = value;
        }

    }


}
