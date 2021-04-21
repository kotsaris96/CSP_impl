package MyCSP;

import csp.Constraint;
import csp.csp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
 
public class csp1 extends Constraint<Character, Integer> {
    private List<Character> letters;
 
    public csp1(List<Character> letters) {
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
            int t = assignment.get('T');
            int o = assignment.get('O');
            int f = assignment.get('F');
            int r = assignment.get('R');
            int to =t * 6 + o;
           
            int res = f * 36 + o * 6 + r;
            return to + to == res;
        }
        return true; // no conflicts
    }

    
    
    public static void main(String[] args) {
        List<Character> letters = List.of('T', 'O', 'F', 'R');
        Map<Character, List<Integer>> possibleDigits = new HashMap<>();
        for (Character letter : letters) {
            possibleDigits.put(letter, List.of(0, 1, 2, 3, 4, 5));
        }
        // so we don't get answers starting with a 0
        possibleDigits.replace('M', List.of(1));
        csp<Character, Integer> csp = new csp<>(letters, possibleDigits);
        csp.addConstraint(new csp1(letters));
        Map<Character, Integer> solution = csp.backtrackingSearch();
        if (solution == null) {
            System.out.println("No solution found!");
        } else {
            System.out.println(solution);
        }
    }
}