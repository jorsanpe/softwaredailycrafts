require 'minitest/autorun'

def twenty_first_to_word(digit)
  return ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
          "ten", "eleven", "twelve", "thirteen", "fourteen", "fiveteen", "sixteen", "seventeen", "eightteen", "nineteen"][digit]
end

def double_digit_to_word(double_digit)
  return ["", "", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"][double_digit]
end

module NumbersInWords
  def to_words(number)
    words = ""
    if number > 1000
      words += to_words(number / 1000) + " thousand "
      number = number % 1000
    end
    if number > 100
      words += twenty_first_to_word(number / 100) + " hundred and "
      number = number % 100
    end
    if number > 20
      words += double_digit_to_word(number / 10)
      number = number % 10
      if number > 0
        words += " "
      else
        return words
      end
    end
    return words + twenty_first_to_word(number)
  end
end

class NumbersInWordsTest < Minitest::Test
  include NumbersInWords
  def test_return_value_for_single_digit
    words = to_words(0)
    assert_equal "zero", words
  end

  def test_return_value_for_another_single_digit
    words = to_words(7)
    assert_equal "seven", words
  end
  
  def test_returns_digits_between_ten_and_twenty
    words = to_words(17)
    assert_equal "seventeen", words
  end

  def test_returns_double_digits_larger_than_twenty
    words = to_words(25)
    assert_equal "twenty five", words
  end

  def test_returns_double_digits_larger_than_thirty
    words = to_words(35)
    assert_equal "thirty five", words
  end

  def test_returns_double_digits_larger_than_seventy
    words = to_words(76)
    assert_equal "seventy six", words
  end

  def test_returns_double_digits_larger_than_one_hundred
    words = to_words(103)
    assert_equal "one hundred and three", words
  end

  def test_returns_double_digits_larger_than_three_hundred
    words = to_words(347)
    assert_equal "three hundred and fourty seven", words
  end

  def test_returns_double_digits_larger_than_one_thousand
    words = to_words(1347)
    assert_equal "one thousand three hundred and fourty seven", words
  end

  def test_returns_double_digits_larger_than_ten_thousand
    words = to_words(13470)
    assert_equal "thirteen thousand four hundred and seventy", words
  end

  def test_returns_double_digits_larger_than_a_hundred_thousand
    words = to_words(563491)
    assert_equal "five hundred and sixty three thousand four hundred and ninety one", words
  end
end

