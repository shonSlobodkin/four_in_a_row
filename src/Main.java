public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        BoardModel myBoardModel = new BoardModel();
        Presenter myPresenter = new Presenter(myBoardModel);
        View myView = new View(myPresenter);
        System.out.println("Game finished");
    }
}