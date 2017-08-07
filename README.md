# FinalProjectGnomenwald
Store all final project materials relating to the backend and GUI functions of Gnomenwald
We have a front-end GUI platform that displays Villages, Gnomes, and Roads and allows the user to interact by creating and deleting the aforementioned objects. The first type of village deletion destroys all roads connecting to the village node, whereas the second type of village deletion adjusts the map so that roads connected to the village being deleted are reconfigured to connect the villages that were formerly adjacent to the deleted village. Additionally, the GUI allows us to create randomly configured graphs, as well as connect villages based on the shortest path. This shortest path functionality only works when villages have not already had roads drawn to connect them.
We have also created search functionality using Binary Search trees to be able to sort and parse gnomes effientally 
Some challenges we faced:
  If a gnome is heading towards a village as the village is deleted then the gnome keeps heading towards the village
  We would have liked to include the Census class in our GUI, however, it caused the GUI to glitch, so we tested it solely using the console.
  Our VIP functionality is not included in the GUI.
  
