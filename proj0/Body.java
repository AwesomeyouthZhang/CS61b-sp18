public class Body{
	/** 
	* double xxPos: Its current x position
	* double yyPos: Its current y position
	* double xxVel: Its current velocity in the x direction
	* double yyVel: Its current velocity in the y direction
	* double mass: Its mass
	* String imgFileName: The name of the file that corresponds to the image that depicts the body (for example, jupiter.gif)
	 */
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	
	public Body(double xP, double yP, double  xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
		}

	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;

	}
	/** 
     * Calculate the distance between two Bodys 
     */
	public double calcDistance(Body b){
		double dx = b.xxPos-this.xxPos;
		double dy = b.yyPos-this.yyPos;
		return Math.sqrt(Math.pow(dx,2) + Math.pow(dy, 2) );
	}
	/** 
     * Calculate the force between two Bodys 
     */
	public double calcForceExertedBy(Body b){
		double G = 6.67e-11;
		return (G*this.mass*b.mass)/Math.pow(calcDistance(b),2);

	}
	/** 
     * Calculate the X direction force between two Bodys 
     */
	public double calcForceExertedByX(Body b){
		double ddx = b.xxPos-this.xxPos;
		double Fx = calcForceExertedBy(b)*ddx/calcDistance(b);
		return Fx;

	}
	/** 
     * Calculate the Y direction force between two Bodys 
     */
	public double calcForceExertedByY(Body b){
		double ddy = b.yyPos-this.yyPos;
		double Fy = calcForceExertedBy(b)*ddy/calcDistance(b);
		return Fy;
	}
	/** 
	* Update the new postion and new velocity and the x net acceleration and y net acceleration
	*/
	public void update(double dt, double fx, double fy)
	{
		double ax = fx/mass;
		double ay = fy/mass;
		xxVel += dt*ax;
		yyVel += dt*ay;
		xxPos += dt*xxVel;
		yyPos += dt*yyVel;


	}
	public double calcForceExertedByX(Body[] planets){
		double Fx = 0;
		for (int i =0;i<planets.length ; i++) {
			if(this!=planets[i])
			{
				Fx += calcForceExertedByX(planets[i]); 
			}
		}
		return Fx;

	}
	public double calcForceExertedByY(Body[] planets){
		double Fy = 0;
		for (int i =0;i<planets.length ; i++) {
			if(this!=planets[i])
			{
				Fy += calcForceExertedByY(planets[i]); 
			}

		}
		return Fy;

	}
	
	/**
	* Drawing each bodies
	*/
	public  void draw(){
		/**
		* There must use realtive path*/

		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
	}





}