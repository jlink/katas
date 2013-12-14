package wordwrap

import spock.lang.Specification

class WordWrapSpec extends Specification {

    def wrapper

    void setup() {
        wrapper = new WordWrapper()
    }

    def "no text"() {
        when:
        def text = ""
        then:
        wrapText(text) == []
    }

    def wrapText(String text, int wrapAt = 1) {
        wrapper.wrap(text, wrapAt)
    }
}
