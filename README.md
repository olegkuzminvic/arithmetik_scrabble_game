****************************************************************************************************
<h1>Arithmetik-Scrabble </h1>
****************************************************************************************************

<b> Scrabble </b> is a board game in which letter-printed pieces are lined up to make words.
The game pieces are printed with numbers or mathematical operators. The players thus form arithmetic expressions with the aim of maximizing their mathematical value-the result of a mathematical expression. Indeed only those expressions contribute to a player's point account to which that player has contributed more tokens than the opposing player.

<h2> Playmaking: </h2>

The playing field is square and consists of 10 × 10 cells.
A maximum of one token can be placed on each cell. There are 13 different pieces: the numbers 0 to 9 and the symbols +, - and *.
We designate a word as valid if the word represents a mathematical expression in postfix notation.

<i> Some examples: </i>

• The word 2 3 * is a valid word.
• The word 2 3 is an invalid word because the operator is missing.
• The word 2 * 3 is an invalid word because the phrase is not formulated in postfix notation.

Two players compete against each other. Each player has a limited supply of tiles during the game
Program start is set.

The game ends when both players agree on the end of the game, e.g. because their stock of chips is used up, or because the remaining pieces do not allow another turn.

<h3> command line arguments </h3>
Your program expects a lot of tiles as first and second command line argument.
To put it more precisely, this is a multi-set, because the same token may occur several times in the set.
The first command-line argument describes the initial supply of pieces by player 1. The second command-line argument describes the initial supply of pieces by player 2.

Example cmd ----> 1139 +++ ** 1139 +++ ** <----

<h4> Interactive user interface </h4>

--place command

The place command takes one or more pieces from the active player's supply and places them on the board.

Input format: place 'checklist'; 'Line number'; 'column number>;' alignment '

--end command

The end command ends the current game and passes the scores and the winner to the console.

--score command

The score command displays the current score of a particular player on the console.

Input format score 'Player'

--bag command

The bag command outputs the current tile supply of a particular player to the console.
Input format: bag 'player'

--rowprint command

The rowprint command prints a particular line of the game board to the console.

Input format rowprint 'line number'

--colprint command

The colprint command outputs a specific column of the game field to the console.

Input format colprint 'column number'
 quit command

This command terminates the program.
****************************************************************************************************