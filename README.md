# Diggy's Adventure Camp Optimizer

## Game description
In Diggy's Adventure, player can place equipment in their camp. There are two types of equipment:
* **Regeneration equipment** - Increases the rate at which player generates energy. Energy is generated continously: "60 energy per hour" means 1 energy is generated every minute, not 60 energy at once every full hour.
* **Capacity equipment** - Increases the upper limit of energy that player can hold onto at once. Energy generation is stopped upon reaching this value.

Total regeneration of all equipment is summed up into one value. Same goes for capacity.

Every piece of equipment also has its size, measured with width and height.
* Player's camp has limited number of equipment slots, and each equipment takes up number of those slots equal to its **width**.
* Every camp slot has its height, and accepts only equipment of that same **height** or shorter.
  * Stacking two short equips on top of each other in one tall slot is not allowed.
* Active equipment in camp can be freely replaced. Inactive equips take no space, but do nothing.

Diggy's Adventure is a game from "browser idle" genre:
* Player logs into the game at least once every day and interacts with gameplay loops using stored energy. More energy is required for harder stages.
* Using resources acquired through gameplay, player can expand their camp and buy new equipment.
* When player runs out of energy, they're meant to leave the game and return a few hours later or tomorrow, once energy replenishes.

## Problem description
The ability to precisely tweak regeneration and storage values via replacing active equipment allows the player to create a camp that fits their own individual method of gameplay.
Active players who log in frequently can prioritize high regeneration, while players with infrequent logins can prioritize high capacity.

Ideally, a player should determine the approximate number of hours they are going to be away from the game, and set up a camp where `capacity == regen_per_hour * hours`.
This ensures maximum energy waiting for them next time. However, there are two issues.
1. Obtaining the exact ratio is often impossible due to specific, unchangeable values of equipment.
2. Due to different widths and heights of pieces of equipment, it's not trivial to determine the set of equipment of optimal value - problem known as knapsack problem.
   1. Example one: Last two slots can be filled with a 2-width regen piece, two 1-width regen pieces, 2-width storage piece, two 1-width storage pieces, or one 1-width regen and storage pieces each. Each of those five combinations increase different values, and depending on rest of the setup thus far, each combination might be efficient in different circumstances.
   2. Example two: If there's not enough slots for all equipment of a particular height, in one scenario it might be optimal to prioritize tall regen equipment, and compensate it with using more short storage equipment. In another scenario, the opposite might hold true.

## Solution
TODO
(Following app will determine the optimal setup for chosen number of AFK hours, and list of camp slots + acquired equipment supplied by user in an CSV file.)