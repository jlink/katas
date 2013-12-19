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

    def "wrap 3 times within long word without space"() {
        expect:
            wrapText("hallohallo", 3) == ["hal", "loh", "all", "o"]
    }

    def "wrap line at space exactly at position"() {
        expect:
            wrapText("abc a", 3) == ["abc", "a"]
            wrapText("abc abc abc", 3) == ["abc", "abc", "abc"]
    }

    def "wrap line at space before max line length"() {
        expect:
            wrapText("ab ab", 3) == ["ab", "ab"]
    }

    private wrapText(String text, int wrapAt) {
        wrapper.wrap(text, wrapAt)
    }
}
