## Project structure
* tira.datastructures
  * CustomArrayList
  * CustomHashSet[1,2]
* tira.util
  * Random[3]
* tira.minesweeper.logic
  * Board
  * Field
  * Coordinate
  * State
* tira.minesweeper.solver
  * Solver
* tira.minesweeper.measurement
  * Performance
* tira.mnesweeper.ui
  * SceneController
* tira.minesweeper.main
  * JavaFXMineSweeper
    * GUI that simulates the solver
    * Executable jar
  * Main
    * Performance test results

## Datastructures
Array, custom ArrayList and custom HashSet

## Algorithms
Solving minesweeper is NP-complete problem. Solver implemeted in this project uses Double Set Single Point algorithm [4]. It utilises two hashSets. The first one keeps record of fields (i.e. squares) that it will open. These are mainly mine free safe fields, but if the set is empty, solver makes a quess and may hit the mine. This algorithm uses random selection when it makes a quess. The second hashset stores questionable squares (i.e. unknown squares that are at the border of opened squares).

This project uses also algorithm to generate pseudo random. It uses milliSeconds as a seed and generates pseudorandom from the last two numbers of the seed.

## Complexity of solver
In the worst scenario the time complexity of the solver would be O(n * m * (m + k)), where n is the maximum number of quesses (i.e. board/grid size), m is the size of safe/question fields set and k is row number.

## Improvements
* Significantly higher number of games could be solved with another algorithm based on constraint satisfaction problem.
* Simpler datastructures (Array) would have been applicable as well and propably more effifient. Array would have been more efficient to access than hashSet. It would not have been necessary to resize the array when items would have been removed from or added to the Array but rather to set null or the wanted status to the square.
* Could have refactored the code and implement more methods for datastructures.
* Architecture could be improved (e.g. changing public methods to private, injection of solver to the game etc.).
* Could have designed the project better. Now the design document does not apply.

### Sources
1. [Custom hashSet implementation in Java](https://www.google.fi/amp/s/blog.miyozinc.com/algorithms/custom-hashset-implementation-in-java/amp/)
2. [How to implement a simple hashSet in java](http://robertovormittag.net/how-to-implement-a-simple-hashset-in-java/)
3. [Random](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)
4. [Algorithmic approaches to playing minesweeper](https://dash.harvard.edu/bitstream/handle/1/14398552/BECERRA-SENIORTHESIS-2015.pdf?sequence=1)
