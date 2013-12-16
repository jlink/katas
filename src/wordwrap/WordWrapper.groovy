package wordwrap

import org.gcontracts.annotations.Ensures
import org.gcontracts.annotations.Requires

class WordWrapper {

    @Requires({ maxLineLength > 0 })
    @Ensures({ result instanceof List<String> })
    def wrap(text, int maxLineLength) {
        if (text.isEmpty())
            return []
        if (text.size() <= maxLineLength)
            return [text]
        return [text[0..maxLineLength - 1], text[maxLineLength, -1]]
    }
}
