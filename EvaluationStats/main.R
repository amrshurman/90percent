mydata <- read.csv("../data/loginData.csv")
attach(mydata)
print(mydata)

print(mean(Logins.Total))
print(mean(Logins.success))
print(mean(Logins.Failure))

print(median(Logins.Total))
print(median(Logins.success))
print(median(Logins.Failure))

print(sd(Logins.Total))
print(sd(Logins.success))
print(sd(Logins.Failure))

print(mean(Logins.avgSuccess))
print(mean(Logins.avgFail))

print(median(Logins.avgSuccess))
print(median(Logins.avgFail))

print(sd(Logins.avgSuccess))
print(sd(Logins.avgFail))

hist(Logins.Total, ylim=c(0,20),breaks=7, main="Histogram of Total Logins", xlab = "Total logins")

hist(Logins.success, ylim=c(0,25), xlim=c(0,25), main="Histogram of Succesful Logins", xlab = "Succesful logins")

hist(Logins.Failure, ylim=c(0,25), xlim=c(0,25), main="Histogram of Failed Logins", xlab = "Failed logins")

hist(Logins.avgFail, ylim=c(0,25), xlim=c(0,60000), main="Histogram of time for Failed Logins", xlab = "time of Failed logins (ms)")

hist(Logins.avgSuccess, ylim=c(0,25), xlim=c(0,50000), main="Histogram of time for Successful Logins", xlab = "time of Successful logins (ms)")

boxplot(Logins.avgFail, main ="BoxPlot of time for Failed Logins", ylab = "time of Failed logins (ms)")

boxplot(Logins.avgSuccess,main ="BoxPlot of time for Successful Logins", ylab = "time of Successful logins (ms)")

