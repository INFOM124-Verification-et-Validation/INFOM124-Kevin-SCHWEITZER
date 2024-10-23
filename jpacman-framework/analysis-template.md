# Specification-based Testing

## 1. Goal, inputs and outputs
- Goal:
  - Find nearest player, calc the shortest path to player, list of directions [north, south, east, etc.] to reach the target box starting from Clyde pos as Clyde. 
    - If no path found or Clyde already on target, return optional.empty()
    - If path found, check if distance shorter or equal to/than SHYNESS (8 boxes) 
      - If distance shorter than 8 flee in opposite direction of first box from path list
      - Else move in the direction of first box from path list
- Input domain: 
  - maps of format {"############", "#P________C#", "############"}
- Output domain:
  - an optional containing the move or empty if the current state of the game makes the AI move impossible
  - NORTH,EAST,SOUTH,WEST or Direction.isEmpty()
    
## 2. Explore the program (if needed)

## 3. Identify input and output partitions

### Input partitions

#### Individual inputs

#### Combinations of input values

### Output partitions

## 4. Identify boundaries

## 5. Select test cases
1. Distance Clyde-Player <= 8 (Horizontal)
2. Distance Clyde-Player <= 8 (Vertical)
3. Distance Clyde-Player > 8
4. Distance Clyde-Player = 0