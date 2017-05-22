Gautam Raghu, Justin Kim, David Root
05/05/2017
The Lab
Introduction: 


This is a free for all game in which the goal is to eliminate as many enemy players as possible. There is a large 2D map which the players can move around 
in. The objective is to eliminate the enemy and secure the win for yourself. The game is timebased, so one has to tally up as many kills as possible in the 
time given. The team with the most kills wins and takes control of the legendary lab and its secrets.

Use your skills to move around the map, killing all others before the time runs out. 

Instructions: Use WASD to move while mercilessly killing all others who stand in your way by using your mouse to aim. Click to shoot.


Features List:


Must-Have features:


(DONE)Menu that includes instructions, start button, join server, create server
(DONE)Players can move around and shoot
(DONE)A background that moves with the player
(DONE)Characters have hitboxes
(DONE)Character animations
(DONE)A multiplayer mode allowing local players on the network to play each other


Want-To-Have Features:


(NOT DONE)Multiple maps
(NOT DONE)Animations for the characters
(NOT DONE)A minimap
(NOT DONE)Accurate collision detection
(DONE)Sound Effects
(NOT DONE)Power-ups


Stretch features:


(NOT DONE)Multiple fighter classes that the players can choose
(NOT DONE)Players can play against an AI
(NOT DONE)Different gamemodes (Such as capture the flag or king of the hill)
(NOT DONE)Customizable controls
(NOT DONE)Player stats
(NOT DONE)A leaderboard of all of the users
(NOT DONE)Changeable landscape (bullet holes)
(NOT DONE)Movement mechanics (jump pads, speed boosts, power ups)
 

Class list:


Package: MAP

Obstacle - Obstacles on the map
Map - Represents the map that will be used

Package: GAMEPLAY

Bullet - represents a bullet.
Player - represents a player.
Border - Creates a border around the playable map.
Hud - shows the stats of the player(health, ammo, reloading)


Package: GUI

Main - Contains the main method/menu
DrawingSurface - Represents the frame on which the game is displayed
ClientDrawing - Extends DrawingSurface, represents the game panel for what client users can see
ServerDrawing - Extends DrawingSurface, represents the game panel for what the server user can see
Sound - Provides the sound effects of the game
MenuPanel - The Panel that contains the menu
OptionPanel - contains a way to go back to the menu from the game.
ServerCreationPanel - The panel that allows a user to start a server.
ServerJoinPanel - The panel that allows a user to connect to a server

Responsibilities List:
* Justin: Game mechanics/Networking
* Gautam: Networking
* David: Animation/Graphics
