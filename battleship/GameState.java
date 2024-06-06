package battleship;

public enum GameState {
    BEGINNING("beginning"),
    GAMING("gaming"),
    ENDING("ending");

    final private String name;

    GameState(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
