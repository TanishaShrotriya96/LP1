#Decision Tree for prediction. 

library(rpart)
library("rpart.plot")

X=read.csv("T:\\capitalbikeshare.csv")
dataset= X[,c(4,6,9)]

library(caTools)
require(caTools)

set.seed(29)
split = sample.split(dataset$MemberType, SplitRatio = 0.75)
train = subset(dataset, split == TRUE)
test = subset(dataset, split == FALSE)

#model for decision tree. using variable, "dtm"
dtm = rpart(MemberType~., train)
dtm
#plot decision tree
rpart.plot(dtm)

p <-predict(dtm, test, type = "class")
table(test[,3], p)
