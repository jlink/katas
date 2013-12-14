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


    private wrapText(String text, int wrapAt) {
        wrapper.wrap(text, wrapAt)
    }
}
