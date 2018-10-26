# coding: utf-8

# In[106]:


import numpy as np


# In[107]:


def getArrays() :
    l=[]
    start=np.empty([3,3],dtype=int)

    print("Enter the start state")
    
    for i in range(0,3):
        for j in range(0,3):
            start[i][j]=int(raw_input("Enter next number,0 for empty space "))
    #print(start)
    l.append(start)
    
    goal=np.empty([3,3],dtype=int)

    print("Enter the goal state")
    for i in range(0,3):
        for j in range(0,3):
            goal[i][j]=int(raw_input("Enter next number,0 for empty space "))
    #print(goal)
    l.append(goal)
    return l


# In[108]:


def main() : 
    l=[]
    l=getArrays()
    #print l[0]
    #print l[1]
    
    route=[]
    closedList=[]
    hash={} #using a dictionary to get closedList - newStates
    
    while(np.array_equal(l[0],l[1])==False) :
        start=l[0]
        print start
        position=np.where(start==0)
    
        #creating all new states
        newStates=[]
        #move down
        if position[0]+1<3 :
            down=np.copy(start)
            x=position[0]+1,position[1]
            temp=down[x]
            down[x]=down[position]
            down[position]=temp
            #print "Down is :\n",down
            newStates.append(down)

        #move up
        if position[0]-1>=0 :
            up=np.copy(start)
            x=position[0]-1,position[1]
            temp=up[x]
            up[x]=up[position]
            up[position]=temp
            #print "Up is :\n",up
            newStates.append(up)

        #move right 
        if position[1]+1<3 :
            right=np.copy(start)
            x=position[0],position[1]+1
            temp=right[x]
            right[x]=right[position]
            right[position]=temp
            #print "Right is :\n",right
            newStates.append(right)

        #move left
        if position[1]-1>=0 :
            left=np.copy(start)
            x=position[0],position[1]-1
            temp=left[x]
            left[x]=left[position]
            left[position]=temp
            #print "Left is :\n",left
            newStates.append(left)

        #obtain actualStates to visit as ClosedList-OpenList
        actualStates=[]

        for i in range(0,len(closedList)) :
            hash[str(closedList[i])]=1
        hash[str(start)]=1
        #print "NewStates are : ",newStates 
        #print "ClosedStates are : ",closedList 
        if len(closedList)>0 : 
            for new in range(0,len(newStates)) :
                if(str(newStates[new]) not in hash) :
                        actualStates.append(newStates[new])
        else : 
            actualStates=np.copy(newStates)
        #print "Actual States",actualStates

        #check for g(n)
        g=[]
        for state in actualStates : 
            count=0
            for i in range(0,3):
                for j in range(0,3):
                    if start[i,j]!=state[i,j] :
                        count=count+1

            g.append(count)
        #print g

        #check for h(n) 
        h=[]
        goals=l[1]


        for state in actualStates : 
            count=0
            for i in range(0,3):
                for j in range(0,3):
                    if goals[i,j]!=state[i,j] :
                        count=count+1

            h.append(count)

        #print h

        h=np.array(h)
        g=np.array(g)
        f=h+g
        #print f

        #finding state with minimum cost
        #print np.where(f==np.min(f))
        nextState=np.where(f==np.min(f))
        l[0]=actualStates[nextState[0][0]]
        closedList.append(l[0])
        #print "Start and Goal states : ", l
        route.append(start)
    route.append(l[1])
    
    print "Tree for going from start state to goal state is : \n"
    
    for each in route :
        print each,"\n"


# In[109]:


if __name__=="__main__":
    main()


