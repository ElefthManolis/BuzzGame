package GameLogic;

import java.util.HashMap;


public class ThermometerRound implements RoundInterface{
    private final int ROUND_NAME = 5;
    private HashMap<GamePlayer, PlayerStatus> playerData = new HashMap<>();

    class PlayerStatus {
        int rightAnswers;
        Question currentQuestion;

        void setRightAnswers(int rightAnswers) {
            this.rightAnswers = rightAnswers;
        }

        int getRightAnswers() {
            return this.rightAnswers;
        }

        void setCurrentQuestion(Question currentQuestion) {
            this.currentQuestion = currentQuestion;
        }

        Question getCurrentQuestion() {
            return this.currentQuestion;
        }
    }

    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        double scoreToAdd = 0;

        if(answerIndex == this.getCurrentQuestion(player).getRightAnswerIndex()) {
            increaseRightAnswer(player);
        }
        if(checkWin(player)){
            scoreToAdd = calculateScore();
            player.setScore(player.getScore() + scoreToAdd);
        }
    }

    @Override
    public int getRoundName() {
        return this.ROUND_NAME;
    }

    @Override
    public Question getCurrentQuestion(GamePlayer player) {
        return this.getPlayerData(player).getCurrentQuestion();
    }

    @Override
    public boolean fetchNextQuestion(GamePlayer player) {
        if(checkWin(player)) {
            return false;
        } else {
            this.getPlayerData(player).setCurrentQuestion(this.getRandomQuestion());

            return true;
        }
    }

    public int getPlayerWins(GamePlayer player) {
        return this.getPlayerData(player).getRightAnswers();
    }

    private int calculateScore() {
        return 5000;
    }

    private void  increaseRightAnswer(GamePlayer player){
        int currWins = this.getPlayerData(player).getRightAnswers();

        this.getPlayerData(player).setRightAnswers(currWins + 1);
    }

    private boolean checkWin(GamePlayer player){
        return this.getPlayerData(player).getRightAnswers() >= 5;
    }

    private void createNewPlayerData(GamePlayer player) {
        PlayerStatus playerStatus = new PlayerStatus();
        playerData.put(player, playerStatus);

        playerStatus.setRightAnswers(0);
        playerStatus.setCurrentQuestion(this.getRandomQuestion());
    }

    private PlayerStatus getPlayerData(GamePlayer player) {
        if(!this.playerData.containsKey(player)) {
            this.createNewPlayerData(player);
        }

        return this.playerData.get(player);
    }

    private Question getRandomQuestion() {
        return QuestionRepository.getInstance().getSingleRandomQuestion();
    }
}
