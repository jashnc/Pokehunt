README
POKEHUNT - JASHN CHHAYA/HANYAO ZHANG

-open app.java
-run applet

press enter to start.

grass is where pokemon are, money is made from killing them and selling hides to store, 's' to activate store interface(while next to storekeeper)
different guns with different damage modifiers, pokemon get progressively tougher as you kill more.


//Description of classes

app.java - 
main class, draws everything, initializes many images

Player.java -
player object, location, guns, etc.

InitItems.java - 
initializes items

initPokemon - 
initializes pokemon

Item.java - 
Item object, name, cost

Guns.java - 
Gun object, damage, name, cost

Pokemon.java - 
pokemon object, damage, name, health, image

Shop.java -
draws shop interface, adds items to shop, buy item method

BattleInit.java
initializes new battle, random probability of getting into a battle, random pokemon chosen, damage methods 