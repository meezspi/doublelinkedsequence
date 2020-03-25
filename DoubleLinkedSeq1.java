public class DoubleLinkedSeq implements Cloneable
	{
		private int manyNodes;
		private DoubleNode head;
		private DoubleNode tail;
		private DoubleNode cursor;
		
	public DoubleLinkedSeq( )
	{
		head = null;
		tail = null;
		cursor = null;
		manyNodes = 0;
	}

	public void addAfter(double element)
	{
	
		if (isCurrent()){
			cursor.addNodeAfter(element);
			cursor = cursor.getLink();
		}
		else{
			if (manyNodes==0){
				head = new DoubleNode(element,null);
				tail = head;
			}
			else{
	
				tail.addNodeAfter(element);
				tail = tail.getLink();
				
			}
			cursor = tail;
		}
		manyNodes++;
	}

	public void addBefore(double element)
	{
		if(manyNodes==0){
			head = new DoubleNode(element,null);
			tail = head;
			cursor = tail;
		}
		else if (!isCurrent() || cursor == head){
			head = new DoubleNode(element,head);
			cursor = head;
		        
		}
		else{
			DoubleNode precurs = head;
			DoubleNode newNode = new DoubleNode(element,cursor);
			while(precurs.getLink()!=cursor){
				precurs = precurs.getLink();
			}
			precurs.setLink(newNode);
			cursor = newNode;
		}
		manyNodes++;
	}
	   
	   public void addAll(DoubleLinkedSeq addend)
	   {
		   if(addend == null)
			   throw new NullPointerException("addend is null");
		   if (addend.size() > 0)
			   tail.setLink(addend.head);
		   	   manyNodes += addend.size();
	   }   
	   
	   public void advance( )
	   {
		   if (!isCurrent())
			   throw new IllegalStateException("no current element");
		   if (cursor == tail)
			   cursor = null;
		   else {
			   cursor = cursor.getLink();
		   }
	   }
	   
	   public DoubleLinkedSeq clone( )
	   { 
		   DoubleLinkedSeq answer;
		   try
			{
			   answer = (DoubleLinkedSeq) super.clone( );
			}
		   catch (CloneNotSupportedException e)
		   {
			   throw new RuntimeException ("This class does not implement Cloneable");
		   }
		   
		   answer.head = DoubleNode.listCopy(head);
		   
		   return answer;
		}
	   
	   public static DoubleLinkedSeq catenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2)
	   {
		   DoubleLinkedSeq answer = new DoubleLinkedSeq();
		   if (s1==null || s2 == null){
			throw new IllegalStateException("one argument is null");
		   }
		   else {
		   s1.tail.setLink(s2.head.getLink());
	           answer.head = s1.head;
		   answer.tail = s2.tail;
		   answer.cursor=null;
		   answer.manyNodes=(s1.size()+s2.size());
		   }
		
		return answer;
	   }


	   /**
	   * Accessor method to get the current element of this sequence. 
	   * <b>Precondition:</b>
	   *   <CODE>isCurrent()</CODE> returns true.
	   * @return
	   *   the current capacity of this sequence
	   * @exception IllegalStateException
	   *   Indicates that there is no current element, so 
	   *   <CODE>getCurrent</CODE> may not be called.
	   **/
	   public double getCurrent( )
	   {
		   if (!isCurrent()){
			   throw new IllegalStateException("no current element");
		   }
		   return cursor.getData();
	   }

	   public boolean isCurrent( )
	   {
		   return cursor != null;
	   }

	   public void removeCurrent( )
	   {
	     if (!isCurrent()){
	    	 throw new IllegalStateException("no current element");
	     }
	     else if (cursor == head){
	    	 head = head.getLink();
	    	 cursor = head;
	     }
	     else {
	    	 DoubleNode precurs = head;
	    	 while (precurs.getLink() != cursor){
	    		 precurs = precurs.getLink();
	    	 }
	    	 if (cursor == tail){
	    		 precurs.setLink(null);
	    		 tail = precurs;
	    		 cursor = null;
	    	 }
	    	 else {
	    		 precurs.setLink(cursor.getLink());
	    		 cursor = cursor.getLink();
	    	 }
	     }
	   manyNodes--;
	   }
	                 
	   public int size( )
	   {
		   return manyNodes;
	   }
	   

	   public void start( )
	   {
		   if (manyNodes == 0){
	    	  cursor = null;
	      }
	     cursor = head;
	   }

	public String toString() {
        String ret = "Size: " + manyNodes + "\n";
        ret += "Current Node: " + (cursor != null ? cursor.getData() : "null") + "\n";
        ret += "Nodes: [";
        Node<Double> current = head;
        while(current != null) {
            ret += current.getData();
            if(current.getLink() != null) {
                ret += ", ";
            }
            current = current.getLink();
        }
        ret += "]";
        return ret;
    }
	
	
	public void print(){
		    DoubleNode current=head;
		    System.out.println("length = " + size());
	    	    if (isCurrent()){
			System.out.println("current element = " + getCurrent());
		    }
		    else {
			System.out.println ("there is no current element");
		    }
		    System.out.print("elements: ");
		    
		    while (current!=null){
                        System.out.print(current.toString() + " ");
			current = current.getLink();
		    }
		    System.out.println("\n");
		}
	
}


	    















	           