package wordwrap

import org.gcontracts.annotations.Ensures
import org.gcontracts.annotations.Requires

class WordWrapper {

    @Requires({ maxLineLength > 0 })
    @Ensures({ result instanceof List<String> })
    def wrap(text, int maxLineLength) {
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
        def head = line[0..maxLineLength - 1]
        def rest = line[maxLineLength..-1]
        if (rest[0] == " ")
            rest -= " "
        [head, rest]
    }
}
