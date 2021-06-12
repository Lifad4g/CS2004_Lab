import java.util.ArrayList;

public class CanonSolution {
	private static double maxAngle = 55.0;
	private static double maxVel = 1650;
	private static double minAngle = 25.0;
	private static double minVel = 1500.0;
	private static double rAngle = CS2004.UR(25.0, 55.0);
	private static double rVel = CS2004.UR(1500.0, 1650.0);

	public static void main(String[] args) {
		RMHC(5, 5);
	}

	public static double rAngle() {
		return rAngle;
	}

	public static double rVel() {
		return rVel;
	}

	public static void ex1() {
		double r = Cannon.GetMaxRange(40.0, 1575.0);
		System.out.println(r);
		ArrayList<Double> xt = Cannon.GetX();
		ArrayList<Double> yt = Cannon.GetY();
		System.out.println(xt.size());
		System.out.println(yt.size());
	}

	public static void MaxRange() {
		double max = Cannon.GetMaxRange(maxAngle, maxVel);
		System.out.println("Max Range: " + max);
	}

	public static void MinRange() {
		double min = Cannon.GetMaxRange(minAngle, minVel);
		System.out.println("Min Range: " + min);
	}

	public static double SpecRange(double theta, double vzero, int TargetRange) {
		double range = Cannon.GetMaxRange(theta, vzero);
		double fitness = Math.abs(range - TargetRange);
		return fitness;
		// 75,000 = 37/1523.91
		// 95,000 = 43/1592.84
		// 65,000 = 30/1605.164
	}

	public static double SmallChange(double angle, double mvel) {
		if (CS2004.UI(0, 1) == 0) {
			double smallAngle = CS2004.UR(-1, 1);
			double NrAngle = angle + smallAngle;
			if (NrAngle > maxAngle) {
				NrAngle = maxAngle;
			}
			return NrAngle;
		} else {
			double smallVel = CS2004.UR(-2.5, 2.5);
			double NrVel = mvel + smallVel;
			if (NrVel > maxVel) {
				NrVel = maxVel;
			}
			return NrVel;
		}
	}

	public static void RMHC(int iter, int target) {
		// Representation & Random Starting Point
		double angle = rAngle();
		double mvel = rVel();

		// Fitness Function
		double fitness = SpecRange(angle, mvel, target);
		// Iteration
		for (int i = 0; i < iter; i++) {
			// Small Change Operator
			double paramChange = SmallChange(angle, mvel);
			double newFitness = SpecRange(angle, mvel, target);
			if (target == 1) {
				if (newFitness >= fitness) {
					fitness = newFitness;
					System.out.println("Iteration: " + i);
					System.out.println("Fitness: " + fitness);
					System.out.println("Angle: " + angle);
					System.out.println("Muzzle velocity: " + mvel);
					System.out.println();
				}
			} else {
				fitness = newFitness;
				System.out.println("Iteration: " + i);
				System.out.println("Fitness: " + fitness);
				System.out.println("Angle: " + angle);
				System.out.println("Muzzle velocity: " + mvel);
				System.out.println();
			}
			if (paramChange <= maxAngle) {
				angle = paramChange;
			} else if (paramChange >= minVel) {
				mvel = paramChange;
			}
		}
	}
}
