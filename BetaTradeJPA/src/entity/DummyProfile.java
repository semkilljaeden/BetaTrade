package entity;

public class DummyProfile {
	double capital = 0;
	public synchronized void incrementCapt(double d) {
		capital += d;
	}
}
