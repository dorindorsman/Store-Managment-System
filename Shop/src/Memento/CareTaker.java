/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Memento;

import java.util.Stack;

public class CareTaker {
		private Stack<MementoClass> mementoStack;

		public CareTaker() {
			this.mementoStack = new Stack<>();
		}

		public void save(MementoClass mC) {
			mementoStack.push(mC);
		}

		public MementoClass restore() {
			if(mementoStack.isEmpty())
				return null;
			return mementoStack.pop();
		}

	}


