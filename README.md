projekt-Scrabble
================

1. Compile and run

The following commands in order should suffice to run the program:
$ cd scrabble
$ mvn compile
$ mvn jar:jar
$ java -jar target/scrabble-1.0-SNAPSHOT.jar
Make sure you are using java 1.7 or newer.
If you experience long loading times between the lobby and the game try granting
the jvm more memory by adding, for example, -Xmx2g to the last command.

2. File formats

If you want to use a custom language or board you can provide your own files in
the following formats.

2.1. Bag

The bag file should contain one line for every tile you want in your bag. The
line should consist of a character followed by a space followed by an integer.
The character is the character for the tile to represent and the number is the
number of points the tile should be worth. Wildcards should be represented by a
star ( * ) character and be always worth 0 points.

2.2. Dictionary

The dictionary file should contain one line for every word that is supposed to
be allowed in the game. The line should contain just the allowed word.
IMPORTANT: No word can contain letters not represented by any tile in the chosen
bag.

2.3. Board

The first line of the board file should contain one integer -- the length of the
edge of the board. If you don't want to use the board as a torus add two to that
number. All the following lines should describe any special fields on the board.
They should consist of three integers -- two being the coordinates of the
special field, incremented by one if the board is supposed not to be a torus,
and the third should be an integer encoding the type of the field. The following
codes exist:
0 -- the start field. One must exist for the board to be valid.
1 -- double word bonus.
2 -- triple word bonus.
3 -- double letter bonus.
4 -- triple letter bonus.
5 -- standard field, no need to specify that as it is the default.
6 -- an invalid field. No tiles may be placed on it. Also useful for making
non-rectangular boards.
