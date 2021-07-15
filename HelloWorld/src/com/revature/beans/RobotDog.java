package com.revature.beans;

public class RobotDog extends Dog implements Robot{

	@Override
	public void hold() {
		Robot.super.hold();
	}

}
