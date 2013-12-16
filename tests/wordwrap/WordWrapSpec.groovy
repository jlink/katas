package wordwrap

import spock.lang.Specification

class WordWrapSpec extends Specification {

    public static final int ANY_WRAP = 1

    def wrapper

    void setup() {
        wrapper = new WordWrapper()
    }

    def "no text"() {
        expect:
            wrapText("", ANY_WRAP) == []
    }

    def "don't wrap text shorter than specified line length"() {
        expect:
            wrapText("hallo", 5) == ["hallo"]
    }

    def "wrap within word when there is no previous space"() {
        expect:
            wrapText("hallo", 3) == ["hal", "lo"]
    }

    private wrapText(String text, int wrapAt) {
        wrapper.wrap(text, wrapAt)
    }
}
