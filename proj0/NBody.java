  
public class NBody{
     /** 
     * Return the radius of the universe reading from the file 
     */
   public static double readRadius(String fileName) {
    In in = new In(fileName);
        in.readInt();
        double Radius = in.readDouble();

        return Radius;
    }
   /** 
     * Return the array of bodies reading from the file 
     */

      public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        int planetsNums = in.readInt();
        in.readDouble();
        Body[] planets = new Body[planetsNums];
        for (int i=0; i < planetsNums ; i++ ) {
          
          double xp = in.readDouble();
          double yp = in.readDouble();
          double xv = in.readDouble();
          double  yv = in.readDouble();
          double m = in.readDouble();
          String img = in.readString();
          planets[i]  = new Body(xp, yp, xv, yv, m, img);

          planets[i].draw();


          
        }
        return planets;

      }

  /**
 * Draw the initial universe state 
      */
      public static void main(String[] args) {
      /**
      * Collect the data from input 
      */
      double T = Double.parseDouble(args[0]);
      double dt = Double.parseDouble(args[1]);
      String filename = args[2];
      double radius = readRadius(filename);
      Body[] planets = readBodies(filename);
      int nums = readBodies(filename).length;

     
      /**
      * Drawing the Background
      */
      String backgroundFileName = "images/starfield.jpg";
      /**
      * Using the StdDraw class to display the background
      */


      /** Sets up the universe so it goes from
      * -radius, -radius up to 100, 100 
      */
      StdDraw.setScale(-radius, radius);
      /* Clears the drawing window. */
      StdDraw.clear();

      StdDraw.picture(0,0,backgroundFileName);
      /**
      * planets img must in the front of background.
      * So the method of draw must be the down of StdDraw.picture(0,0,backgroundFileName)
      */
     Body[] bodies = readBodies(filename);
     StdDraw.enableDoubleBuffering();

     double t = 0;
     while(t<T){
      double[] xForces = new double[nums];
      double[] yForces = new double[nums];
      for (int i=0; i<nums; i++) {

        
        xForces[i] = planets[i].calcForceExertedByX(planets);
        yForces[i] = planets[i].calcForceExertedByY(planets);


      }
      for(int i =0; i<nums; i++){
        planets[i].update(dt,xForces[i],yForces[i]);
      }
      StdDraw.picture(0, 0, backgroundFileName);
      for (int i =0; i<nums; i++) {
        planets[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
      t+=dt;



       
     }
      StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0  ; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                      planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
}

  }   


}  