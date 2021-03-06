public class QueueArray {

    public int[] arr;

    public int total, first, next, max_elements;
    public boolean produceriswaiting,consumeriswaiting;

    public QueueArray()
    {
        arr = new int[100];
        total=0;
        first=0;
        next=0;
        max_elements=2;
        produceriswaiting=false;
        consumeriswaiting=false;
    }
    
    public boolean isEmpty()
    {	
    	if (total==0)
    		return true;
    	else
    		return false;
    }

	 public boolean isFull()
    {	
    	if (total==max_elements)
    		return true;
    	else
    		return false;
    }

    public boolean enqueue(int ele)
    {
    	if (isFull())
    	{
    		return false;
    	}
    	else
    	{
        arr[next] = ele;
        next=(next+1)%max_elements;
        total++;
        return true;
     }
    }

    public int dequeue()
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int ele = arr[first];
        arr[first] = -1;
        first=(first+1)%max_elements;
        total--;
        return ele;
    }

}