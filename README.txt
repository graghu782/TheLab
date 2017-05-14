Gautam Raghu, Justin Kim, David Root
05/05/2017
The Lab
Introduction: 


There was once a legend about a mysterious lab that contained great secrets and many treasures... you and some others set off to find this lab. 
One day, you and your team set off into a particularly forboding jungle... and there it is. However, you are not alone. You will have to kill
the others for control of the legendary lab. This is a two dimensional, third person multiplayer shooter game. There are different game modes,
free for all and team deathmatch. There is a large 2D map which the players can move around in. The objective is to eliminate the enemy and secure
the win for your team. The game is timebased, so one and his team has to tally up as many kills as possible in the time given. The team with the 
most kills wins and takes control of the legendary lab and its secrets.


Use your skills to move around the map, killing all others before the time runs out. 


Instructions: Use WASD to move while mercilessly killing all others who stand in your way by using your mouse to aim. Click to shoot.


Features List:


Must-Have features:


(NOT DONE)Menu that includes instructions, start button, join server, create server
(DONE)Players can move around and shoot
(DONE)A background that moves with the player
(NOT DONE)Characters have hitboxes
(NOT DONE)Character animations
(NOT DONE)A multiplayer mode allowing local players on the network to play each other


Want-To-Have Features:


(NOT DONE)Multiple maps
(NOT DONE)Animations for the characters
(NOT DONE)A minimap
(NOT DONE)Accurate collision detection
(NOT DONE)Sound Effects
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
Minimap - (Want-to-have) feature for showing various objects on the map


Package: GAMEPLAY


MovingObject - Represents all moving objects.
Bullet - Extends MovingObject, represents a bullet.
Person - Extends MovingObject, represents a player.
Arm - Used by the person, extends MovingObject.
Torso - Main body of the person
Legs - (Stretch goal, used for better hitbox scanning)
Hud - shows the stats of the player(health, ammo, reloading)


Package: GUI


Main - Contains the main method/menu
GameFrame - Controls frame  
GamePanel - Controls frame


Package: NETWORKING


TBD


Responsibilities List:
* Justin: Game mechanics
* Gautam: Networking
* David: Animation/Graphics
