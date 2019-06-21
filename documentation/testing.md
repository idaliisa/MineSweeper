
## Unit test and manual testing
Project is tested both manually and automatically. Test coverage report:

Graphical user interface and Main classes were excluded from test coverage.



Solver has been tested manually with graphical user interface. The solver works fine but it cannot always find the solution even if there would be one. Another algorithm implementnation would solve higher number of games. 

## Performance testing for the solver

Performance testing was executed for easy, intermediate and expert level games:


|Level | solved (%)  | solved (%), literature reference | Execution time per 10 000 games |
|------|-------------|-----------------------|---------------------------------|
|Easy  | 62.68       | 68.19                 | 2012 ms |
|Intermediate| 27.93 | 35.92                 | 6570 ms |
|Expert | 0.34       | 0.89                  | 7912 ms |

Easy: 9X9 board with 10 mines

Intermediate 16X16 board with 40 mines

Expert: 16X30 board with 99 mines


