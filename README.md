## ConnectFour2
AI program that plays against others in Connect Four. Uses JSON Format for input and output.

## How to Use
The program runs from the commandline, and accepts a JSON object containing the game's current state. The JSON object is structured as follows:

`{"grid":[[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0]],"height":6,"player":2,"width":7}`

The frid represents the gameb board as a column-major vector of vectors. The program will analyze the game's current state and return its chosen move in a JSON object of the form:

`{"move":2}`
