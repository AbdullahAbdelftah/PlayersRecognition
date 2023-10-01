# PlayersRecognition
Hockey Rink Player Recognition System README
============================================

Description:
------------
The Hockey Rink Player Recognition System was developed for the IIHF (International Ice Hockey Federation) to detect and raise alarms when there are too many players from the same team inside the rink. The system utilizes digital images of the rink, processes them, and identifies player positions. It then can be paired with another module to count the number of players on the rink.

Features:
---------
1. **Digital Image Processing**: Processes images taken from a digital camera in the ceiling capturing the rink view.
2. **Player Recognition**: Extracts player positions from the photos based on team colors.
3. **Bounding Box Calculation**: Determines player's exact position by the center of their bounding box.

Components:
-----------
1. **IPlayersFinder Interface**: This interface defines the method for finding players in a given photo based on a team identifier and a minimum threshold.

2. **PlayersFinder Class**: This class implements the `IPlayersFinder` interface. The main functionality includes:
   - Processing the photo to identify players' positions.
   - Determining players' bounding boxes.
   - Sorting player positions.

Usage:
------
1. **Inputs**:
   - A 2D representation of the photo (String array) where the x-th character of the y-th element represents a 2x2 square's color on the rink.
   - A team identifier (a digit from '0' to '9') representing the team's color in the photo.
   - A threshold representing the minimum area for an element to be considered a player.

2. **Outputs**:
   - An array of points (java.awt.Point[]) indicating the center position of each player's bounding box. Sorted by x-coordinate and then by y-coordinate for players with the same x-coordinate.

Example:
-------
Input:
5 7
...R...
...R...
RRRRR..
...R...
...R...
R
16

Output:
[(5,5)]


