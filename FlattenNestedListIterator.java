import java.util.Iterator;
import java.util.Stack;

//time complexity : O(n)
//space complexity : O(n)

/*
This code defines a NestedIterator class that iterates through a nested list of integers.
The iterator uses a stack to manage the traversal of nested lists. When the next() method
is called, it returns the next integer from the nested list, while hasNext() checks if there
are any more integers to return. The advance() method is used to traverse through the nested
lists and handle cases where the current element is a nested list, pushing the iterator of
that list onto the stack. This design ensures that
the iteration works efficiently through both single integers and nested lists of varying depth.
* */

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> myStack;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        myStack=new Stack<>();
        myStack.push(nestedList.iterator());
        advance();
    }

    private void advance(){
        nextEl=null;
        while(!myStack.isEmpty())
        {
            if(!myStack.peek().hasNext())
            {
                myStack.pop();

                nextEl=null;
            }
            else
            {
                nextEl=myStack.peek().next();
                if(nextEl.isInteger())
                {
                    break;
                }
                else
                {
                    myStack.push(nextEl.getList().iterator());
                }
            }
        }
    }

    @Override
    public Integer next() {
        Integer temp=nextEl.getInteger();
        advance();
        return temp;
    }

    @Override
    public boolean hasNext() {
        return nextEl!=null;
    }
}
