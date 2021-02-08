package com.bhavesh.solutions;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Leetcode284 {

}

class PeekingIterator implements Iterator<Integer> {
	Integer peeked = null;
	Iterator<Integer> iterator;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator = iterator;
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if (peeked == null) {
			if (!iterator.hasNext()) {
				throw new NoSuchElementException();
			}
			peeked = iterator.next();
		}

		return peeked;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		if (peeked != null) {
			Integer toReturn = peeked;
			peeked = null;
			return toReturn;
		}

		if (!iterator.hasNext()) {
			throw new NoSuchElementException();
		}

		return iterator.next();
	}

	@Override
	public boolean hasNext() {
		return peeked != null || iterator.hasNext();
	}
}