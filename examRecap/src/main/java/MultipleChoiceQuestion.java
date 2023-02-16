import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question{

    ArrayList<String> options= new ArrayList<>();
    String correctOption;

    public MultipleChoiceQuestion(){
        super();
    }
    public MultipleChoiceQuestion(String qText, ArrayList<String> answers, String correctOption){
        this.setQuestionText(qText);
        this.options = answers;
        this.correctOption = correctOption;
    }

    // I am using ArrayList to allow for later increases in the number of options

    public void addOption(String option){
        this.options.add(option);
    }

    @Override
    public String displayQuestion() {

        StringBuilder answers = new StringBuilder("Question: " + this.getQuestionText() + "\n");
        for(String a : options){
            answers.append(" ").append(a).append("\n");
        }

        return answers.toString();

    }

    @Override
    public boolean verifyAnswer(String answer) {
        return correctOption.equalsIgnoreCase(answer);
    }

    public String getQuestionText(){
        return super.getQuestionText();
    }
    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {

        this.correctOption = correctOption;
    }
}
