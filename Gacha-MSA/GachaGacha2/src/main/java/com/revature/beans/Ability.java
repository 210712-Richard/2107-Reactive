package com.revature.beans;

import java.util.concurrent.Callable;

public enum Ability implements Callable<Long> {
	SCROUNGE {
		public Long call() {
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 1l;
		}
	}, FARM {
		public Long call() {
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 3l;
		}
	}, INVADE_RUSSIA_IN_THE_WINTER {
		public Long call() {
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 15l;
		}
	}, POETRY {
		public Long call() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 5l;
		}
	}, FLAKES {
		public Long call() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 20l;
		}
	}, HUNTER {
		public Long call() {
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 10l;
		}
	};
}
