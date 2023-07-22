public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p1){
		double disInX = p1.xxPos - this.xxPos;
		double disInY = p1.yyPos - this.yyPos;
		double dis = Math.sqrt(Math.pow(disInX,2) + Math.pow(disInY,2));
		return dis;
	}

	public double calcForceExertedBy(Planet p1){
		double force = (6.67 * this.mass * p1.mass) / (Math.pow(10,11) * Math.pow(this.calcDistance(p1),2));
		return force;
	}

	public double calcForceExertedByX(Planet p1){
		double forceByX = this.calcForceExertedBy(p1) * (p1.xxPos - this.xxPos) / this.calcDistance(p1);
		return forceByX;
	}

	public double calcForceExertedByY(Planet p1){
		double forceByY = this.calcForceExertedBy(p1) * (p1.yyPos - this.yyPos) / this.calcDistance(p1);
		return forceByY;
	}

	public double calcNetForceExertedByX(Planet[] p2){
		double netByX = 0.0;
		for(int i = 0; i < p2.length; i++){
			if(this.equals(p2[i])){
				continue;
			}
			netByX = netByX + this.calcForceExertedByX(p2[i]); 
		}
		return netByX;
	}

	public double calcNetForceExertedByY(Planet[] p2){
		double netByY = 0.0;
		for(int i = 0; i < p2.length; i++){
			if(this.equals(p2[i])){
				continue;
			}
			netByY = netByY + this.calcForceExertedByY(p2[i]); 
		}
		return netByY;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		double vX = this.xxVel + dt * aX;
        this.xxVel = vX;
		double vY = this.yyVel + dt * aY;
        this.yyVel = vY;
		double pX = this.xxPos + dt * vX;
        this.xxPos = pX;
		double pY = this.yyPos + dt * vY;
        this.yyPos = pY;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}












}