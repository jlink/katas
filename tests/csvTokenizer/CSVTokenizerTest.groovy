package csvTokenizer

import org.junit.Test

class CSVTokenizerTest {

  CSVTokenizer tokenizer = new CSVTokenizer()

  @Test
  void oneEmptyToken() {
    assert [""] == tokenize("")
    assert [""] == tokenize(" ")
  }

  private List tokenize(String s) {
    return use(CSVTokenizer) {
      s.tokenize()
    }
  }

  @Test
  void oneToken() {
    assert ["Hello"] == tokenize("Hello")
    assert ["Hello"] == tokenize(" Hello ")
  }

  @Test
  void twoTokens() {
    assert ["a", "b"] == tokenize("a,b")
  }

  //@Test
  void quotedToken() {
    assert ["a"] == tokenize(/"a"/)
  }
}
