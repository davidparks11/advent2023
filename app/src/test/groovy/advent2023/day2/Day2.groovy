package day1

import spock.lang.Specification
import advent2023.problems.day2.Day2;

class Day2Test extends Specification {

    Day2 day;

    def setup() {
        day = new Day2();
    }

    def "part1"() {
        when:
        def output = day.part1()
        
        then:
        output == 8
    }

    def "part2"() {
        when:
        def output = day.part2()
        
        then:
        output == 2286
    }
}
