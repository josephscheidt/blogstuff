//for shorthand reference
import java.math.BigInteger;
/**
 * This class implements a function to find the answer to the following problem, posed by The Riddler on
 * fivethirtyeight.com on 3/23/2018. It also implements a function to check the calculations I made on
 * paper to come up with the answer.
 *
 * Imagine taking a number and moving its last digit to the front. For example, 1,234 would become 4,123. 
 * What is the smallest positive integer such that when you do this, the result is exactly double the original
 * number? (For bonus points, solve this one without a computer.)
 *
 * @author Joseph Scheidt
 * @version 1.0
 */
public class NumberShuffle {

    /**
     * This function checks the calculations I made by paper, seeing if the large integers I constructed
     * satisfy the requirements posed by the problem. It utilizes BigInteger, which is not strictly
     * necessary (long variables would be sufficient), but is used just for kicks.
     */
    public static void checkAnswers() {
       
       //String array for my answers
       String[] myAnswers = new String[] {"105263157894736842", "157894736842105263", "210526315789473684",
                                          "263157894736842105", "315789473684210526", "368421052631578947",
                                          "421052631578947368", "473684210526315789"};
                                          
       //Shuffle last digit to front, compare answer * 2 to shuffled number
       for(int i = 0; i < 8; i++) {
       
           //convert answer to BigInteger
           BigInteger myInt = new BigInteger(myAnswers[i]);
           //shuffle digit, convert to BigInteger
           BigInteger shuffled = new BigInteger( myAnswers[i].charAt(17) + myAnswers[i].substring(0, 17) );
           

           //compare and print results
           if( shuffled.equals( myInt.multiply( new BigInteger("2") ) ) ) {
           
                System.out.println("Answer " + i + " is CORRECT");
                
           } else {
           
                System.out.println("Answer " + i + " is FALSE");
           
           }
           
       }
       
       System.out.println();
    
    }
    
    /**
     * A method to calculate the answer to the given problem, using logic. If we
     * know the last digit of the original number, we then know the last digit of
     * it's double. ******1 * 2 = *********2, for instance. Since 2, therefore, 
     * must be the second-to-last digit of the original number, we can multiply
     * again: *******21 * 2 = *******42. Therefore the number must end in 421.
     * In this manner, we can recursively construct the lowest possible solution
     * for each digit from 1 to 9. (A number ending in 0 cannot be a solution.)
     *
     * This is a combination of a String index and multiplication problem. Since
     * the BigNumber class takes Strings as inputs, it works very well here with
     * our algorithm to find a solution for each ending digit.
     *
     * As it turns out, a number ending in one is also not a possible solution.
     * (This is due to the number construction requiring a leading zero at the
     * iteration a solution presents itself for the other digits:
     *   052,631,578,947,368,421 * 2 == 105,263,157,894,736,842)
     */
    public static void smallestShuffled() {
        
        //constant BigInteger for doubling
        BigInteger two = new BigInteger("2");
        
        //one loop for each initial digit
        for(int i = 1; i < 10; i++) {
        
            //loop to find smallest solution which ends in digit
            BigInteger currentNumber = new BigInteger(String.valueOf(i));
            String doubled = currentNumber.multiply(two).toString();
            String shuffled = currentNumber.toString();
            int digit = 1;
            
            while( (! doubled.equals(shuffled)) && digit < 100 ) {
            
                //add next digit to current number
                currentNumber = new BigInteger(doubled.substring(doubled.length() - digit, doubled.length()) + i);
                
                //construct new doubled and shuffled strings
                doubled = currentNumber.multiply(two).toString();
                shuffled = i + currentNumber.toString().substring(0, digit);
                
                //increase digit
                digit++;
            
            }
            
            if(digit == 100) {
                System.out.println("No numbers ending in " + i + " less than a googol");
            } else {
                System.out.println(currentNumber);
            }
        }
    }
    
    /**
     * The application method.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
    
        checkAnswers();
        smallestShuffled();
    
    }


}
 