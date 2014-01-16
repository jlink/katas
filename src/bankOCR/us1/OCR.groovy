package bankOCR.us1

import groovy.transform.CompileStatic

class OCR {

    private final static Map<Integer, List<String>> patterns = [
            1: ['   ', '  |', '  |'],
            2: [' _ ', ' _|', '|_ ']
    ]

    int recognizeDigit(List<String> digitGrid) {
        patterns.find { key, value ->
            patternMatchesDigit(value, digitGrid)
        }?.key ?: -1
    }

    @CompileStatic
    private boolean patternMatchesDigit(List<String> pattern, List<String> digit) {
        for (int lineIndex = 0; lineIndex < 3; lineIndex++) {
            if (pattern[lineIndex] != digit[lineIndex].padRight(3))
                return false
        }
        return true
    }

    @CompileStatic
    //lineGrid must contain 3 lines
    List<Integer> recognizeLine(List<String> lineGrid) {
        def digitGrids = cutIntoDigits(lineGrid)
        digitGrids.collect { List<String> digitGrid ->
            recognizeDigit(digitGrid)
        }
    }

    @CompileStatic
    private cutIntoDigits(List<String> lineGrid) {
        def digits = []
        int numberOfDigits = (lineGrid[0].size() / 3).intValue()
        if (lineGrid[0].size() % 3 != 0)
            numberOfDigits++
        for (int i = 0; i < numberOfDigits * 3; i += 3) {
            def digit = lineGrid.collect { String line ->
                return line.padRight(numberOfDigits * 3).substring(i, i + 3)
            }
            digits << digit
        }
        return digits
    }
}
