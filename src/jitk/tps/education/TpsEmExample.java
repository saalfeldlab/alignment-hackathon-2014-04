package jitk.tps.education;

import java.util.Random;
import jitk.spline.ThinPlateR2LogRSplineKernelTransformFloatSep;

public class TpsEmExample {
	
	public static double tol = 0.0001;
	public static Random rand = new Random( 31415926536l );
	
	
	/**
	 * A simple toy example
	 */
	public static void testJitterDeformation(){

		// extents of image space
		int nx = 6000;
		int ny = 6000;
		
		// boundary around image edge where control points are forbidden
		int bnd = 25;
		
		// range of image coordinates where 
		int mx = nx - 2*bnd;
		int my = ny - 2*bnd;    
		
		// scale of deformation in x and y
		float sx = 20f;
		float sy = 20f;
		
		int ndims = 2;			// number of spatial dimensions
		int numDefPts = 200;  	// number of deformation control points
		int N = 4 + numDefPts;  // add '4' for the pinned corners
		
		float[][] srcPts = new float[ndims][N];
		float[][] tgtPts = new float[ndims][N];
		
		/* pin down the image corners */
		srcPts[0][0] = 0;
		srcPts[1][0] = 0;
		srcPts[0][1] = 0;
		srcPts[1][1] = ny;
		srcPts[0][2] = nx;
		srcPts[1][2] = 0;
		srcPts[0][3] = nx;
		srcPts[1][3] = ny;
		
		tgtPts[0][0] = 0;
		tgtPts[1][0] = 0;
		tgtPts[0][1] = 0;
		tgtPts[1][1] = ny;
		tgtPts[0][2] = nx;
		tgtPts[1][2] = 0;
		tgtPts[0][3] = nx;
		tgtPts[1][3] = ny;
		/* * * * * * * * */
		
		/* random deformation */
		for( int i=4; i<N; i++){
			srcPts[0][i] = (float)(bnd + mx * rand.nextDouble());
			srcPts[1][i] = (float)(bnd + my * rand.nextDouble());
			
			tgtPts[0][i] = srcPts[0][i] + (float)(sx * rand.nextDouble());
			tgtPts[1][i] = srcPts[1][i] + (float)(sy * rand.nextDouble());
		}
		/* * * * * * * * */
		
		// forward and reverse transformations
		ThinPlateR2LogRSplineKernelTransformFloatSep xfmFwd  = new ThinPlateR2LogRSplineKernelTransformFloatSep(ndims, tgtPts, srcPts );
		xfmFwd.fit();
		
		float[] somePoint = new float[]{ 3000, 3000 };
		float[] somePointTransformed = xfmFwd.transformPoint(somePoint);
		System.out.println("transformed point: ");
		System.out.println(" ( " + somePoint[0] + ", " + somePoint[1] + " )");
		System.out.println("   to   ");
		System.out.println(" ( " + somePointTransformed[0] + ", " + somePointTransformed[1] + " )");
		
	}

	
	
	public static void main(String[] args){
		System.out.println("Starting");
		
		testJitterDeformation();
		
		System.out.println("Finished");
		System.exit(0);
	}

}
