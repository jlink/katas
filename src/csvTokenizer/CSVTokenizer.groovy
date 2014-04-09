package csvTokenizer

class CSVTokenizer {
  static tokenize(String self) {
    def tokens = []
    def current = ""
    self.each {
      switch (it)  {
        case ',':
          tokens << current.trim()
          current = ""
          break
        default:
          current += it
      }
    }
    tokens << current.trim()
  }
}
