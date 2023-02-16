import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class QuizManager {

    ArrayList<Question> allQuestions = new ArrayList<>();

    public void setAllQuestions(ArrayList<Question> questionsFromElsewhere){
        allQuestions = questionsFromElsewhere;
    }


    /*
    A simple way to generate a random subset is to use random numbers to
    select appropriate indices from the arraylist.
    Some care is needed to ensure that each question only shows up once, though.
     */
    public Quiz generateQuiz(){
        Quiz result = new Quiz();
        int[] chosen = new int[5];

        for(int i = 0; i < 5; i++) {
            int rand = (int) Math.floor(Math.random() * allQuestions.size());

            boolean found = false;
            for(int j=0; j<5; j++){
                if(chosen[j] == rand){
                    found = true;
                }
            }
            if(!found){
                chosen[i] = rand;
            }
        }


        return result;
    }

    /* One way to get a random order is to use Collections.shuffle(Collection c).
        Then an interator can be obtained, that traverses the collection in the new
        (and randomized) order.
     */
    public Quiz generateQuizSmarter(){
        Quiz result = new Quiz();


        ArrayList<Question> unused = new ArrayList<>();
        unused.addAll(allQuestions);
        //Collections.shuffle is a method that can be used on collections to randomize
        // the order of elements within.
        //NOTE: doing this on a copy of the list, to not mess up the ordering in the original.

        Collections.shuffle(unused);
        Iterator<Question> it = unused.iterator();
        boolean added = false;
        int added2 = 0;

        while(it.hasNext() && added2<5){
            if(result.addQuestion((Question) it.next()))
                added2++;
        }

        /*
        do{

            int rand = (int) Math.floor(Math.random() * unused.size());
            added = result.addQuestion(unused.get(rand));
            unused.remove(unused.get(rand));
        }
        while(added);

         */

        return result;
    }
}
