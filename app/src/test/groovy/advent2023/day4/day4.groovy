package day3

import spock.lang.Specification
import advent2023.problems.day4.Day4;

class Day4Test extends Specification {

   Day4 day;

    def setup() {
        day = new Day4();
    }

    def "part1"() {
        when:
        def output = day.part1()
        
        then:
        output == 13
    }

    def "part2"() {
        when:
        def output = day.part2()
        
        then:
        output == 30
    }
}
