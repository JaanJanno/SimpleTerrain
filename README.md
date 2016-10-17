# SimpleTerrain
Procedural generation exercise/demo for computer graphics seminar.

Can be imported as an eclipse project.

Edit the **generate(int x, int y)** method in **Generator** class in package **terrain.generator** for editing the terrain generation algorithm.

The **x** and **y** parameters show the location of the piece of terrain on an isometric grid.

Return **TerrainType.TREE**, **TerrainType.GRASS**, **TerrainType.SAND** or **TerrainType.WATER** and the type of terrain will show up in coordinates x, y.

It is possible to edit **mapWidth** and **mapHeight** variables to chenge the size of the terrain.

The **noise** object can be used for computing Simplex noise in case it is needed for the terrain algorithm.
