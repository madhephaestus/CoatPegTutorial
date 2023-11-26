import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cylinder
import eu.mihosoft.vrl.v3d.Parabola

// Make a coat rack peg

double dowelDiameterYall = 12.75

double dowelDepthYall = 17.23

double dowelLengthYall = 70.52

double nubHeight = 11

double thinPointDiameter = 12.7

double thickPointDiameter = 21.83

double flangeToThinPoint = dowelLengthYall - (nubHeight/2)
 
CSG dowel = new Cylinder(dowelDiameterYall/2, dowelDepthYall).toCSG()

CSG midSection = new Cylinder(thickPointDiameter/2, // Radius at the bottom
	thinPointDiameter/2, // Radius at the top
	flangeToThinPoint, // Height
	(int)30 //resolution
	).toCSG()//convert to CSG to display
	.movez(dowelDepthYall)
	
CSG topCone = Parabola.cone(thickPointDiameter/2 + 5 , nubHeight/2)
CSG bottomCone = topCone.roty(180)

CSG nubAtEnd = topCone.union(bottomCone)
				.toZMax()
				.movez(dowelLengthYall + dowelDepthYall )
				
CSG stack = CSG.unionAll([dowel,midSection,nubAtEnd])

CSG box= stack.getBoundingBox()
				.toXMin()
				.movex(dowelDiameterYall/2 -1)
stack = stack.difference(box)
		.roty(-90)
		.toZMin()
		
stack.setName("CoatHangerPeg")
				
return [stack]


