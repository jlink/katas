package wordwrap

import org.gcontracts.annotations.Ensures
import org.gcontracts.annotations.Requires

class WordWrapper {

    @Requires({ wrapAt > 0 })
    @Ensures({ result instanceof List<String> })
    def wrap(text, int wrapAt) {
        []
    }
}
