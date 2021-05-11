# Epidemic Simulator
This is a project that started as a small-scale terminal assignment from Base Camp (hence the folder name). 
However, I took the oportunity to learn about Java's swing library, mainly to use JFrame and JPanel.

## Usage
When running the program, the console will ask for a settings profile.
You can either choose default or custom settings.
If the user chooses default, then the simulation will run with predefined settings that I think demonstrate the project nicely.
The fun part is when you choose custom settings.
The console will ask for each setting individually, while the user provides the values.

After choosing which settings to use, the program will open a new window to display the simulation.
The user cannot interact with the window, but they can watch the virus spread.

### People States
When running the simulation, you'll notice that there are a bunch of dots on the upper majority part of the window.
Each dot represents a "person", or as I like to nickname them, "ants".
There are four states that a person can be in, indicated by their color:
- Green: Healthy
- Yellow: Infected, but can't spread the virus
- Red: Sick, meaning they can spread the virus
- Grey: Removed, either meaning they are dead/recovered

The total count of people, divided by their status, is found on the lower portion.
Just to the left of that, there is an indication of the settings profile.
Once the total amount of infected and sick people equal 0, a "Virus is Eliminated" text will appear above the settings indicator.

## Enjoy!
And remember, this is for science!
