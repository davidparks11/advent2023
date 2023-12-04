package day1

import spock.lang.Specification
import advent2023.problems.day1.Day1;

class Day1Test extends Specification {

    Day1 day;

    def setup() {
        day = new Day1();
    }

    def "part1"() {
        when:
        def output = day.part1()
        
        then:
        output == 209
    }

    def "part2"() {
        when:
        def output = day.part2()
        
        then:
        output == 281
    }
}
