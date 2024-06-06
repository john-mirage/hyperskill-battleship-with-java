package battleship;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Battleship {
    private final Scanner scanner;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Player currentPlayer;
    private GameState currentGameState;
    private ShipState currentShipState;

    public Battleship() {
        this.scanner = new Scanner(System.in);
        this.firstPlayer = new Player(1);
        this.secondPlayer = new Player(2);
        this.currentPlayer = this.firstPlayer;
        this.currentGameState = GameState.BEGINNING;
        this.currentShipState = ShipState.AIRCRAFT_CARRIER;
    }

    private Coordinate askForAttackCoordinate() {
        Pattern pattern = Pattern.compile("^([A-J])([1-9]|10)$");
        while (true) {
            String input = this.scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                try {
                    return new Coordinate(matcher.group(1), matcher.group(2));
                } catch (Exception e) {
                    System.out.println("Error! Invalid coordinates");
                }
            } else {
                System.out.println("Error! Invalid coordinates");
            }
        }
    }

    private Ship askForShip() {
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", this.currentShipState.getName(), this.currentShipState.getLength());
        Pattern pattern = Pattern.compile("^([A-J])([1-9]|10) ([A-J])([1-9]|10)$");
        while (true) {
            String input = this.scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                try {
                    Coordinate a = new Coordinate(matcher.group(1), matcher.group(2));
                    Coordinate b = new Coordinate(matcher.group(3), matcher.group(4));
                    if (a.getX() == b.getX() && a.getY() != b.getY()) {
                        return new RowShip(a, b, this.currentShipState, this.currentPlayer.getBattlefield());
                    } else if (a.getY() == b.getY() && a.getX() != b.getX()) {
                        return new ColumnShip(a, b, this.currentShipState, this.currentPlayer.getBattlefield());
                    } else {
                        System.out.println("Error! Wrong ship location! Try again:");
                    }
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            } else {
                System.out.println("Error! Invalid coordinates");
            }
        }
    }

    private void switchShipState() {
        switch (this.currentShipState) {
            case AIRCRAFT_CARRIER: this.currentShipState = ShipState.BATTLESHIP; break;
            case BATTLESHIP: this.currentShipState = ShipState.SUBMARINE; break;
            case SUBMARINE: this.currentShipState = ShipState.CRUISER; break;
            case CRUISER: this.currentShipState = ShipState.DESTROYER; break;
            case DESTROYER: this.currentShipState = ShipState.AIRCRAFT_CARRIER; break;
        }
    }

    private void askForShips() {
        for (int i = 0; i < 5; i++) {
            Ship ship = this.askForShip();
            this.currentPlayer.getShips()[this.currentShipState.getIndex()] = ship;
            this.switchShipState();
            this.currentPlayer.getBattlefield().print(false);
        }
    }

    public void start() {
        while (!this.currentGameState.getName().equals("ending")) {
            switch (this.currentGameState) {
                case BEGINNING:
                    System.out.printf("Player %d, place your ships on the game field\n", this.currentPlayer.getId());
                    this.currentPlayer.getBattlefield().print(false);
                    this.askForShips();
                    this.currentPlayer = this.secondPlayer;
                    System.out.println("Press Enter and pass the move to another player");
                    this.scanner.nextLine();
                    System.out.printf("Player %d, place your ships on the game field\n", this.currentPlayer.getId());
                    this.currentPlayer.getBattlefield().print(false);
                    this.askForShips();
                    this.currentGameState = GameState.GAMING;
                    System.out.println("Press Enter and pass the move to another player");
                    this.scanner.nextLine();
                    break;
                case GAMING:
                    while (this.firstPlayer.getSunkShips() < 5 && this.secondPlayer.getSunkShips() < 5) {
                        Player opponentPlayer = this.currentPlayer;
                        this.currentPlayer = this.currentPlayer.getId() == 1 ? this.secondPlayer : this.firstPlayer;
                        opponentPlayer.getBattlefield().print(true);
                        System.out.println("-".repeat(21));
                        this.currentPlayer.getBattlefield().print(false);
                        System.out.printf("Player %d, it's your turn:\n", this.currentPlayer.getId());
                        Coordinate coordinate = this.askForAttackCoordinate();
                        String[][] battlefield = opponentPlayer.getBattlefield().getBattlefield();
                        String value = battlefield[coordinate.getX()][coordinate.getY()];
                        switch (value) {
                            case "O":
                                battlefield[coordinate.getX()][coordinate.getY()] = "X";
                                int sunkShips = 0;
                                for (Ship ship : opponentPlayer.getShips()) {
                                    if (ship.isSunk()) {
                                        sunkShips++;
                                    }
                                }
                                if (sunkShips >= 5) {
                                    System.out.println("You sank the last ship. You won. Congratulations!");
                                } else if (sunkShips > opponentPlayer.getSunkShips()) {
                                    System.out.println("You sank a ship! Specify a new target:");
                                } else {
                                    System.out.println("You hit a ship!");
                                }
                                break;
                            case "X":
                                System.out.println("You already hit this ship at this position");
                                break;
                            case "M":
                                System.out.println("You already hit and miss this position");
                                break;
                            default:
                                System.out.println("You missed!");
                        }
                        System.out.println("Press Enter and pass the move to another player");
                        this.scanner.nextLine();
                    }
                    this.currentGameState = GameState.ENDING;
                    break;
            }
        }
    }
}
