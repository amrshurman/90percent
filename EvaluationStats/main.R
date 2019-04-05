mydata <- read.csv("../data/loginData.csv")
attach(mydata)
print(mydata)

print("for TEXT21")
print("Mean for total login")
print(mean(Logins.Total[scheme=="testtextrandom"]))
print("Mean for successful login")
print(mean(Logins.success[scheme=="testtextrandom"]))
print("Mean for Failed login")
print(mean(Logins.Failure[scheme=="testtextrandom"]))

print("Median for total login")
print(median(Logins.Total[scheme=="testtextrandom"]))
print("Median for successful login")
print(median(Logins.success[scheme=="testtextrandom"]))
print("Median for Failed login")
print(median(Logins.Failure[scheme=="testtextrandom"]))

print("sd for Total login")
print(sd(Logins.Total[scheme=="testtextrandom"]))
print("sd for Successful login")
print(sd(Logins.success[scheme=="testtextrandom"]))
print("sd for Failed login")
print(sd(Logins.Failure[scheme=="testtextrandom"]))

print("Mean for successful login time")
print(mean(Logins.avgSuccess[scheme=="testtextrandom"]))
print("Mean for failed login time")
print(mean(Logins.avgFail[scheme=="testtextrandom"]))

print("Median for successful login time")
print(median(Logins.avgSuccess[scheme=="testtextrandom"]))
print("Median for failed login time")
print(median(Logins.avgFail[scheme=="testtextrandom"]))

print("sd for successful login time")
print(sd(Logins.avgSuccess[scheme=="testtextrandom"]))
print("sd for failed login time")
print(sd(Logins.avgFail[scheme=="testtextrandom"]))


hist(Logins.Total[scheme=="testtextrandom"], ylim=c(0,7),xlim=c(0,35), breaks=7, main="Histogram of text21 Total Logins", xlab = "Total logins")

hist(Logins.success[scheme=="testtextrandom"], ylim=c(0,18), xlim=c(0,25), main="Histogram of Succesful text21 Logins", xlab = "Succesful logins")

hist(Logins.Failure[scheme=="testtextrandom"], ylim=c(0,18), xlim=c(0,12), main="Histogram of Failed text21 Logins", xlab = "Failed logins")

hist(Logins.avgFail[scheme=="testtextrandom"], ylim=c(0,10), xlim=c(0,30000), main="Histogram of time for Failed text21 Logins", xlab = "time of Failed logins (ms)")

hist(Logins.avgSuccess[scheme=="testtextrandom"], ylim=c(0,8), xlim=c(0,30000), main="Histogram of time for Successful text21 Logins", xlab = "time of Successful logins (ms)")

boxplot(Logins.avgFail[scheme=="testtextrandom"], ylim=c(0,30000),main ="BoxPlot of time for text21 Failed Logins", ylab = "time of Failed logins (ms)")

boxplot(Logins.avgSuccess[scheme=="testtextrandom"],main ="BoxPlot of text21 time for Successful Logins", ylab = "time of Successful logins (ms)")


print("for IMAGE21")
print("Mean for total login")
print(mean(Logins.Total[scheme=="testpasstiles"]))
print("Mean for successful login")
print(mean(Logins.success[scheme=="testpasstiles"]))
print("Mean for Failed login")
print(mean(Logins.Failure[scheme=="testpasstiles"]))

print("Median for total login")
print(median(Logins.Total[scheme=="testpasstiles"]))
print("Median for successful login")
print(median(Logins.success[scheme=="testpasstiles"]))
print("Median for Failed login")
print(median(Logins.Failure[scheme=="testpasstiles"]))

print("sd for Total login")
print(sd(Logins.Total[scheme=="testpasstiles"]))
print("sd for Successful login")
print(sd(Logins.success[scheme=="testpasstiles"]))
print("sd for Failed login")
print(sd(Logins.Failure[scheme=="testpasstiles"]))

print("Mean for successful login time")
print(mean(Logins.avgSuccess[scheme=="testpasstiles"]))
print("Mean for failed login time")
print(mean(Logins.avgFail[scheme=="testpasstiles"]))

print("Median for successful login time")
print(median(Logins.avgSuccess[scheme=="testpasstiles"]))
print("Median for failed login time")
print(median(Logins.avgFail[scheme=="testpasstiles"]))

print("sd for successful login time")
print(sd(Logins.avgSuccess[scheme=="testpasstiles"]))
print("sd for failed login time")
print(sd(Logins.avgFail[scheme=="testpasstiles"]))


hist(Logins.Total[scheme=="testpasstiles"], ylim=c(0,10),xlim=c(0,40), main="Histogram of image21 Total Logins", xlab = "Total logins")

hist(Logins.success[scheme=="testpasstiles"], ylim=c(0,8), xlim=c(0,20), main="Histogram of Succesful image21 Logins", xlab = "Succesful logins")

hist(Logins.Failure[scheme=="testpasstiles"], ylim=c(0,12), xlim=c(0,25), main="Histogram of Failed image21 Logins", xlab = "Failed logins")

hist(Logins.avgFail[scheme=="testpasstiles"], ylim=c(0,10), xlim=c(0,60000), main="Histogram of time for Failed image21 Logins", xlab = "time of Failed logins (ms)")

hist(Logins.avgSuccess[scheme=="testpasstiles"], ylim=c(0,10), xlim=c(0,50000), main="Histogram of time for Successful image21 Logins", xlab = "time of Successful logins (ms)")

boxplot(Logins.avgFail[scheme=="testpasstiles"], ylim= c(0,50000),main ="BoxPlot of time for image21 Failed Logins", ylab = "time of Failed logins (ms)")

boxplot(Logins.avgSuccess[scheme=="testpasstiles"],ylim=c(0,30000),main ="BoxPlot of image21 time for Successful Logins", ylab = "time of Successful logins (ms)")


print("for ColorAnimalPair")
print("Mean for total login")
print(mean(Logins.Total[scheme=="ColorAnimalPair"]))
print("Mean for successful login")
print(mean(Logins.success[scheme=="ColorAnimalPair"]))
print("Mean for Failed login")
print(mean(Logins.Failure[scheme=="ColorAnimalPair"]))

print("Median for total login")
print(median(Logins.Total[scheme=="ColorAnimalPair"]))
print("Median for successful login")
print(median(Logins.success[scheme=="ColorAnimalPair"]))
print("Median for Failed login")
print(median(Logins.Failure[scheme=="ColorAnimalPair"]))

print("sd for Total login")
print(sd(Logins.Total[scheme=="ColorAnimalPair"]))
print("sd for Successful login")
print(sd(Logins.success[scheme=="ColorAnimalPair"]))
print("sd for Failed login")
print(sd(Logins.Failure[scheme=="testpasstiles"]))

print("Mean for successful login time")
print(mean(Logins.avgSuccess[scheme=="ColorAnimalPair"]))
print("Mean for failed login time")
print(mean(Logins.avgFail[scheme=="ColorAnimalPair"]))

print("Median for successful login time")
print(median(Logins.avgSuccess[scheme=="ColorAnimalPair"]))
print("Median for failed login time")
print(median(Logins.avgFail[scheme=="ColorAnimalPair"]))

print("sd for successful login time")
print(sd(Logins.avgSuccess[scheme=="ColorAnimalPair"]))
print("sd for failed login time")
print(sd(Logins.avgFail[scheme=="ColorAnimalPair"]))


hist(Logins.Total[scheme=="ColorAnimalPair"], ylim=c(0,8),xlim=c(0,8), main="Histogram of ColorAnimalPair Total Logins", xlab = "Total logins")

hist(Logins.success[scheme=="ColorAnimalPair"], ylim=c(0,6), xlim=c(0,5), main="Histogram of Succesful ColorAnimalPair Logins", xlab = "Succesful logins")

hist(Logins.Failure[scheme=="ColorAnimalPair"], ylim=c(0,6), xlim=c(0,6), main="Histogram of Failed ColorAnimalPair Logins", xlab = "Failed logins")

hist(Logins.avgFail[scheme=="ColorAnimalPair"], ylim=c(0,5), xlim=c(0,60000), main="Histogram of time for Failed ColorAnimalPair Logins", xlab = "time of Failed logins (ms)")

hist(Logins.avgSuccess[scheme=="ColorAnimalPair"], ylim=c(0,6), xlim=c(0,80000), main="Histogram of time for Successful ColorAnimalPair Logins", xlab = "time of Successful logins (ms)")

boxplot(Logins.avgFail[scheme=="ColorAnimalPair"], ylim= c(0,50000),main ="BoxPlot of time for ColorAnimalPair Failed Logins", ylab = "time of Failed logins (ms)")

boxplot(Logins.avgSuccess[scheme=="ColorAnimalPair"],ylim=c(0,70000),main ="BoxPlot of ColorAnimalPair time for Successful Logins", ylab = "time of Successful logins (ms)")



