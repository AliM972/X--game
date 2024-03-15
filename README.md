# How to Play the X+Game

The X+Game is an engaging board game played between a human player and the computer. Here's how to enjoy the game and aim for victory:

## Objective

To win, a player must place tiles on the board to form either an X-shape or a +shape. The length of the winning shape, determined by the number of tiles it contains, is predefined before the game starts.

## Board Setup

- The game is played on a square board with an equal number of rows and columns (n x n).
- Players take turns placing their tiles on the board.

## Players and Turns

- The human player starts the game and uses blue tiles.
- The computer uses red tiles.
- Players alternate turns, placing one tile on the board in an attempt to create the winning configuration.

## Winning Configurations

- **X-shape**: A valid X-shape includes a center tile with four tiles along the diagonals in each direction from the center.
- **+shape**: A valid +shape has a center tile with four tiles directly above, below, to the right, and to the left of the center.

The game ends when one player forms an X-shape or +shape of the required length or when the board is filled, resulting in a draw if no player achieves the winning condition.

## Scoring System

- **Human Wins (0)**: A play leading to a human victory.
- **Undecided (1)**: A play that doesn't immediately result in a win or draw.
- **Draw (2)**: A play resulting in a draw, with no spaces left and no winner.
- **Computer Wins (3)**: A play that ensures a victory for the computer.

## Strategy and Play

Each turn, the computer evaluates all possible moves, assigning scores based on the potential outcome (win, draw, undecided). The game employs a strategy that considers the current configuration of the board and the best possible outcome for each move, aiming for a win or forcing a draw.

## Libraries and Languages Used

- **Java**: The entire game is written in Java, leveraging its object-oriented features for modular and maintainable code.
- **AWT and Swing**: Java's Abstract Window Toolkit (AWT) and Swing are used for creating the graphical user interface (GUI) of the game.
- **Java's Standard Library**: Utilized for data structures (like LinkedList for chaining in the hash table) and I/O operations.

## Running the Game

To run the game, you need Java installed on your system. Use the command line to compile and execute the game. The main entry point is the `Play` class, which requires three command-line arguments:

1. `board-size`: The size of the game board (NxN).
2. `shape-length`: The length of the "X" shape or "+ shape" required to win the game.
3. `depth`: The maximum depth of the game tree to be explored for determining the computer's move.

Compile the Java files first using:

```shell
javac *.java
```

Then, run the game with:
```shell
java Play <board-size> <shape-length> <depth>
```

For example:
```shell
java Play 5 4 3
```

If using an IDE to run:
1. Download the files and open them in your IDE
2. Pass in the required arguments in run configurations: <board-size> <shape-length> <depth>
3. Run Play.java
