package bankOCR.us1

import spock.lang.Specification
import spock.lang.Unroll


class OCRSpec extends Specification {

    @Unroll
    def "recognize #grid as digit #digit"() {
        expect:
            def ocr = new OCR()
            ocr.recognizeDigit(grid) == digit
        where:
            grid                  | digit
            ['   ', '  |', '  |'] | 1
            [' _ ', ' _|', '|_ '] | 2

    }

}