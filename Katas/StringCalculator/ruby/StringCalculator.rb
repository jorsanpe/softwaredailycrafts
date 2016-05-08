
require 'minitest/autorun'

class NegativeIntegerError < RuntimeError
end

class StringCalculator
  def initialize(delimiter)
    @delimiters = /[#{delimiter},\n]/
  end
  
  def verify_negatives_in(array)
    negatives = array.select { |i| i < 0 }
    if  negatives.count > 0
      raise NegativeIntegerError, "Negative numbers are not allowed: #{negatives}"
    end
  end
  
  def to_integer_array(string)
    string.split(@delimiters).map {|i| i.to_i}
  end
  
  def add(string)
    array = to_integer_array(string)
    verify_negatives_in(array)
    return array.inject(0) { |sum, item| sum + item }
  end
end

 
class StringCalculatorTest < Minitest::Test
  def test_return_zero_when_string_is_empty
    sc = StringCalculator.new(",")
    assert_equal(0, sc.add(""))
  end
  
  def test_return_integer_value_when_string_contains_one_number
    sc = StringCalculator.new(",")
    assert_equal(5, sc.add("5"))
  end
  
  def test_return_sum_when_string_contains_two_numbers
    sc = StringCalculator.new(",")
    assert_equal(12, sc.add("5, 7"))
  end

  def test_return_sum_when_string_contains_many_numbers
    sc = StringCalculator.new(",")
    assert_equal(28, sc.add("5, 7, 16"))
  end
  
  def test_return_sum_when_string_contains_many_numbers_with_newlines
    sc = StringCalculator.new(",")
    assert_equal(28, sc.add("5\n7, 16"))
  end
  
  def test_return_sum_when_string_contains_many_numbers_with_different_delimiter
    sc = StringCalculator.new("+")
    assert_equal(28, sc.add("5 + 7 + 16"))
  end
  
  def test_return_sum_when_string_contains_many_numbers_with_negative_numbers
    sc = StringCalculator.new("+")
    result = nil
    begin
      assert_equal(28, sc.add("5+-7+-16"))
    rescue NegativeIntegerError => ex
      result = :exception_handled
    end
    assert_equal :exception_handled, result
    assert_equal "Negative numbers are not allowed: [-7, -16]", ex.message
  end
end
