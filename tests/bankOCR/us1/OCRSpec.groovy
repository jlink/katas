package bankOCR.us1

import spock.lang.Specification
import spock.lang.Unroll


class OCRSpec extends Specification {

    OCR ocr

    def setup() {
        ocr = new OCR()
    }

    @Unroll
    def "recognize #grid as digit #digit #comment"() {
        expect:
            ocr.recognizeDigit(grid) == digit
        where:
            grid                  | digit | comment
            ['   ', '  |', '  |'] | 1     | ''
            [' _ ', ' _|', '|_ '] | 2     | ''
            [' _', ' _|', '|_']   | 2     | 'without trailing spaces'
            ['***', '***', '***'] | -1    | '(unknown)'

    }

    def "recognize line with two digits"() {
        given:
            def lineGrid = [
                    '    _',
                    '  | _|',
                    '  ||_'
            ]
        when:
            def digits = ocr.recognizeLine(lineGrid)
        then:
            digits == [1, 2]
    }

    //todo: Lines are much shorter (e.g. many 1s at end)

}