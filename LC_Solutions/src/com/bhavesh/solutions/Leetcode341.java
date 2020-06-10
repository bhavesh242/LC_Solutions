package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Leetcode341 {

}
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class NestedIterator implements Iterator<Integer> {
    ListIterator<Integer> it;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        addVals(nestedList, list);
       it = list.listIterator();
    }
    
    private void addVals(List<NestedInteger> nestedList, ArrayList<Integer> list){
        for(NestedInteger nestedInteger : nestedList){
            if(nestedInteger.isInteger()){
                list.add(nestedInteger.getInteger());
            }
            else
            {
                addVals(nestedInteger.getList(), list);
            }
        }
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}


/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

//You should not implement it, or speculate about its implementation