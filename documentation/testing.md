
## Unit test and manual testing
Project is tested both manually and automatically. Test coverage report:

![tescoverage](https://github.com/idaliisa/MineSweeper/blob/master/documentation/pictures/testcoverage.png)

[Graphical user interface](https://github.com/idaliisa/MineSweeper/tree/master/MineSweeper/src/main/java/tira/mnesweeper/ui) and [Main classes](https://github.com/idaliisa/MineSweeper/tree/master/MineSweeper/src/main/java/tira/minesweeper/main) were excluded from test coverage.



Solver has been tested [manually with graphical user interface](https://github.com/idaliisa/MineSweeper/blob/master/MineSweeper/src/main/java/tira/minesweeper/main/JavaFXMineSweeper.java). The solver works fine but it cannot always find the solution even if there would be one. Another algorithm implementnation would solve higher number of games. 

## Performance testing for the solver

[Performance testing](https://github.com/idaliisa/MineSweeper/blob/master/MineSweeper/src/main/java/tira/minesweeper/main/Main.java) was executed for easy, intermediate and expert level games:


|Level |Board size and mine count | solved (%)  | solved (%), literature reference | Execution time per 10 000 games |
|------|--------------------------|-------------|----------------------------------|---------------------------------|
|Easy  | 9X9 board with 10 mines  | 62.68       | 68.19                 | 2012 ms |
|Intermediate| 6X16 board with 40 mines   | 27.93 | 35.92                 | 6570 ms |
|Expert | 16X30 board with 99 mines | 0.34       | 0.89                  | 7912 ms |

Magnitude of solved games is the same as in the [literature](https://dash.harvard.edu/bitstream/handle/1/14398552/BECERRA-SENIORTHESIS-2015.pdf?sequence=1).



