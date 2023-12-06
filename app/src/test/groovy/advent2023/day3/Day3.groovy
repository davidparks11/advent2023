package day3

import spock.lang.Specification
import advent2023.problems.day3.Day3;

class Day3Test extends Specification {

   Day3 day;

    def setup() {
        day = new Day3();
    }

    def "part1"() {
        when:
        def output = day.part1()
        
        then:
        output == 4361
    }

    def "part2"() {
        when:
        def output = day.part2()
        
        then:
        output == null
    }
}
