package wordwrap

import org.gcontracts.annotations.Ensures
import org.gcontracts.annotations.Requires

class WordWrapper {


    public static final String SPLIT_CHAR = ' '

    @Requires({ maxLineLength > 0 })
    @Ensures({ result instanceof List<String> })
    def wrap(String text, int maxLineLength) {
        def lines = []
        wrapText(text, maxLineLength, lines)
        lines
    }

    private wrapText(rest, maxLineLength, lines) {
        if (rest.isEmpty())
            return
        if (rest.size() <= maxLineLength) {
            lines << rest
            return
        }
        def (newLine, newRest) = nextSplit(rest, maxLineLength)
        lines << newLine
        wrapText(newRest, maxLineLength, lines)
    }

    private nextSplit(String line, int maxLineLength) {
        int splitPosition = findSplitPosition(line, maxLineLength)
        def head = line[0..splitPosition - 1]
        def rest = line[splitPosition..-1]
        if (rest[0] == SPLIT_CHAR)
            rest -= SPLIT_CHAR
        [head, rest]
    }

    private findSplitPosition(String line, int maxLineLength) {
        def searchSegment = line[0..maxLineLength]
        def indexOfSpace = searchSegment.lastIndexOf(SPLIT_CHAR)
        if (indexOfSpace >= 0 && indexOfSpace <= maxLineLength)
            return indexOfSpace
        else
            return maxLineLength
    }
}
