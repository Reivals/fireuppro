Disclaimer:
- Unit tests ommited due to lack of time
- Server is written in better way than Client due to the nature of console applications
- Both applications are built and working. Some edge cases still possible
- Am aware that ClientAPI also could be generated via Swagger-gen, however I was too short in time to do it.
- 8080 port must be free in localhost
How to run:
1. In seperate terminals:
	- docker run -it --network host reivals/clientfireuppro:latest
	- docker run -it --network host reivals/clientfireuppro:latest
	- docker run -it --network host reivals/serverfireuppro:latest
2. In first terminal create game (click 2 and enter)
3. In 2nd temrminal join to game (pass the gameId which was displayed in 1st terminal).
4. Play the game, possible moves as in the instruction (A1,A2,A3,B1..) + R as refresh