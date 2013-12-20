package wordwrap

import groovy.transform.CompileStatic
import org.gcontracts.annotations.Requires

class WordWrapper {

    public static final String SPLIT_CHAR = ' '
    public static final String NEWLINE = '\n'

    @CompileStatic
    @Requires({ maxLineLength > 0 && text != null })
    String wrapToString(String text, int maxLineLength) {
        wrap(text, maxLineLength).join(NEWLINE)
    }

    @CompileStatic
    @Requires({ maxLineLength > 0 && text != null })
    List<String> wrap(String text, int maxLineLength) {
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
