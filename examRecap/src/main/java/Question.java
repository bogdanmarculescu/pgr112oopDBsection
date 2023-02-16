public abstract class Question {

    private String questionText;
    public abstract String displayQuestion();


    public abstract boolean verifyAnswer(String answer);

    public String getQuestionText(){
        return this.questionText;
    }

    public void setQuestionText(String qs){
        this.questionText = qs;
    }
}
