public class NBody{
	public static double readRadius(String s){
		In in = new In(s);

		int num = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String s){
		In in = new In(s);

		int num = in.readInt();
		double radius = in.readDouble();

		Planet[] planets = new Planet[num];

		for(int i = 0; i < num; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}

		return planets;
	}

	public static void main(String[] args){
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];

		Planet[] ps = NBody.readPlanets(filename);
		double rd = NBody.readRadius(filename);

		StdDraw.setScale(-rd, rd);
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");

		for(int i = 0; i < ps.length; i++){
			ps[i].draw();
		}

		StdDraw.enableDoubleBuffering();

		for(double tv = 0.0; tv <= T; tv = tv + dt){
			double[] xForce = new double[ps.length];
			double[] yForce = new double[ps.length];

			for(int j = 0; j < ps.length; j++){
				xForce[j] = ps[j].calcNetForceExertedByX(ps);
				yForce[j] = ps[j].calcNetForceExertedByY(ps);
			}

			StdDraw.clear();
		    StdDraw.picture(0,0,"images/starfield.jpg");

		    for(int i = 0; i < ps.length; i++){
		    	ps[i].update(dt, xForce[i], yForce[i]);
			    ps[i].draw();
		    }
		    StdDraw.show();
		    StdDraw.pause(10);
		}

		StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", rd);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                ps[i].yyVel, ps[i].mass, ps[i].imgFileName);  
	    }
	}
}