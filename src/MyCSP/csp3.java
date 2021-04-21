package MyCSP;


import csp.Constraint;
import csp.csp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
 
public class csp3 extends Constraint<Character, Integer> {
    private List<Character> letters;
 
    public csp3(List<Character> letters) {
        super(letters);
        this.letters = letters;
    }
 
    @Override
    public boolean satisfied(Map<Character, Integer> assignment) {
        // if there are duplicate values then it's not a solution
        if ((new HashSet<>(assignment.values())).size() < assignment.size()) {
            return false;
        }
 
        // if all variables have been assigned, check if it adds correctly
        if (assignment.size() == letters.size()) {
            int w = assignment.get('W');
            int o = assignment.get('O');
            int n = assignment.get('N');
            int t = assignment.get('T');
            int won =w * 100 +  10 * o + n  ; 
            int res = t * 100 + 10 * o + o; // 10 * o + o = 11 * o 
            return won + won == res;
        }
        return true; // no conflicts
    }

    
    
    public static void main(String[] args) {
        List<Character> letters = List.of('W', 'O', 'N', 'T');
        Map<Character, List<Integer>> possibleDigits = new HashMap<>();
        for (Character letter : letters) {
            possibleDigits.put(letter, List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        // so we don't get answers starting with a 0
        possibleDigits.replace('M', List.of(1));
        csp<Character, Integer> csp = new csp<>(letters, possibleDigits);
        csp.addConstraint(new csp3(letters));
        Map<Character, Integer> solution = csp.backtrackingSearch();
        if (solution == null) {
            System.out.println("No solution found!");
        } else {
            System.out.println(solution);
        }
    }
}